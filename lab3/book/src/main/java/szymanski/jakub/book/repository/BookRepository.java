package szymanski.jakub.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import szymanski.jakub.book.PublishingHouse.PublishingHouse;
import szymanski.jakub.book.entities.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByPublishingHouse(PublishingHouse publishingHouse);

    Optional<Book> findByISBNAndPublishingHouse(Long isbn, PublishingHouse publishingHouse);

}
