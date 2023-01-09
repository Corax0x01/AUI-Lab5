package szymanski.jakub.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import szymanski.jakub.book.PublishingHouse.PublishingHouse;
import szymanski.jakub.book.PublishingHouse.PublishingHouseService;
import szymanski.jakub.book.dto.GetBookResponse;
import szymanski.jakub.book.dto.GetBooksResponse;
import szymanski.jakub.book.dto.PostBookRequest;
import szymanski.jakub.book.dto.PutBookRequest;
import szymanski.jakub.book.entities.Book;
import szymanski.jakub.book.service.BookService;

import java.util.Optional;

@RestController
@RequestMapping("api/publishing_houses/{name}/books")
public class PublishingHouseBookController {
    private BookService bookService;
    private PublishingHouseService publishingHouseService;


    @Autowired
    public PublishingHouseBookController(BookService bookService, PublishingHouseService publishingHouseService){
        this.bookService = bookService;
        this.publishingHouseService = publishingHouseService;
    }

    @GetMapping
    public ResponseEntity<GetBooksResponse> getBooks(@PathVariable("name") String name){
        Optional<PublishingHouse> publishingHouse = publishingHouseService.find(name);
        return publishingHouse
                .map(value -> ResponseEntity.ok(GetBooksResponse.entityToDtoMapper().apply(bookService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{isbn}")
    public ResponseEntity<GetBookResponse> getBook(@PathVariable("name") String name, @PathVariable("isbn") long isbn){
        return bookService.find(name, isbn)
                .map(value -> ResponseEntity.ok(GetBookResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postBook(@PathVariable("name") String publishingHouseName,
                                         @RequestBody PostBookRequest request,
                                         UriComponentsBuilder builder){
        Optional<PublishingHouse> publishingHouse = publishingHouseService.find(publishingHouseName);
        if (publishingHouse.isPresent()){
            Book book = PostBookRequest
                    .dtoToEntityMapper(name -> publishingHouseService.find(name).orElseThrow(), publishingHouse::get)
                    .apply(request);
            book = bookService.create(book);
            return ResponseEntity.created(builder.pathSegment("api", "publishingHouses", "{name}", "books", "{isbn}")
                    .buildAndExpand(publishingHouse.get().getName(), book.getISBN()).toUri()).build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable("name") String name, @PathVariable("isbn") long isbn){
        Optional<Book> book = bookService.find(name, isbn);
        if (book.isPresent()){
            bookService.delete(book.get().getISBN());
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{isbn}")
    public ResponseEntity<Void> putBook(@PathVariable("name") String name,
                                        @PathVariable("isbn") long isbn,
                                        @RequestBody PutBookRequest request){
        Optional<Book> book = bookService.find(name, isbn);
        if (book.isPresent()){
            PutBookRequest.dtoToEntityUpdater().apply(book.get(), request);
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
