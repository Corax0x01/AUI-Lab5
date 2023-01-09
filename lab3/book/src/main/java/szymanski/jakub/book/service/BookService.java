package szymanski.jakub.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import szymanski.jakub.book.PublishingHouse.PublishingHouse;
import szymanski.jakub.book.PublishingHouse.PublishingHouseRepository;
import szymanski.jakub.book.entities.Book;
import szymanski.jakub.book.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;
    private PublishingHouseRepository publishingHouseRepository;

    @Autowired
    public BookService(BookRepository bookRepository, PublishingHouseRepository publishingHouseRepository){
        this.bookRepository = bookRepository;
        this.publishingHouseRepository = publishingHouseRepository;
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public List<Book> findAll(PublishingHouse publishingHouse){
        return bookRepository.findAllByPublishingHouse(publishingHouse);
    }

    public Optional<Book> find(Long isbn){
        return bookRepository.findById(isbn);
    }

    public Optional<Book> find(PublishingHouse publishingHouse, Long isbn){
        return bookRepository.findByISBNAndPublishingHouse(isbn, publishingHouse);
    }

    public Optional<Book> find(String name, Long id){
        Optional<PublishingHouse> publishingHouse = publishingHouseRepository.findById(name);
        if(publishingHouse.isPresent()){
            return bookRepository.findByISBNAndPublishingHouse(id, publishingHouse.get());
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public void update(Book book){
        bookRepository.save(book);
    }

    @Transactional
    public void delete(Long ISBN) {
        bookRepository.deleteById(ISBN);
    }

}
