package szymanski.jakub.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import szymanski.jakub.book.PublishingHouse.PublishingHouseService;
import szymanski.jakub.book.dto.GetBookResponse;
import szymanski.jakub.book.dto.GetBooksResponse;
import szymanski.jakub.book.dto.PostBookRequest;
import szymanski.jakub.book.dto.PutBookRequest;
import szymanski.jakub.book.entities.Book;
import szymanski.jakub.book.service.BookService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/books")
public class BookController {
    private BookService bookService;
    private PublishingHouseService publishingHouseService;

    @Autowired
    public BookController(BookService bookService, PublishingHouseService publishingHouseService){
        this.bookService = bookService;
        this.publishingHouseService = publishingHouseService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<GetBooksResponse> getBooks(){
        return ResponseEntity
                .ok(GetBooksResponse.entityToDtoMapper().apply(bookService.findAll()));
    }

    @GetMapping("{isbn}")
    public ResponseEntity<GetBookResponse> getBook(@PathVariable("isbn") long isbn){
            return bookService.find(isbn)
                    .map(value -> ResponseEntity.ok(GetBookResponse.entityToDtoMapper().apply(value)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postBook(@RequestBody PostBookRequest request, UriComponentsBuilder builder){
        Book book = PostBookRequest
                .dtoToEntityMapper(name -> publishingHouseService.find(name).orElseThrow(), () -> null)
                .apply(request);
        book = bookService.create(book);
        return ResponseEntity.created(builder.pathSegment("api", "books", "{isbn}")
                .buildAndExpand(book.getISBN()).toUri()).build();
    }

    @PutMapping("{isbn}")
    public ResponseEntity<Void> putBook(@PathVariable("isbn") long isbn, @RequestBody PutBookRequest request){
        Optional<Book> book = bookService.find(isbn);
        if(book.isPresent()){
            PutBookRequest.dtoToEntityUpdater().apply(book.get(), request);
            bookService.update(book.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable("isbn") long isbn){
        Optional<Book> book = bookService.find(isbn);
        if(book.isPresent()){
            bookService.delete(book.get().getISBN());
            return ResponseEntity.accepted().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }

}
