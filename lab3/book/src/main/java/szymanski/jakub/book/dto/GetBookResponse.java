package szymanski.jakub.book.dto;

import lombok.*;
import szymanski.jakub.book.entities.Book;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetBookResponse {
    private Long ISBN;
    private String title;
    private String author;
    private double price;
    private String publishingHouse;

    public static Function<Book, GetBookResponse> entityToDtoMapper(){
        return book -> GetBookResponse.builder()
                .ISBN(book.getISBN())
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .publishingHouse(book.getPublishingHouse().getName())
                .build();
    }
}
