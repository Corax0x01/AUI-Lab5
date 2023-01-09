package szymanski.jakub.book.dto;

import lombok.*;
import szymanski.jakub.book.entities.Book;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetBooksResponse {

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class Book{
        private Long ISBN;
        private String title;
    }

    @Singular
    private List<Book> books;

    public static Function<Collection<szymanski.jakub.book.entities.Book>, GetBooksResponse> entityToDtoMapper(){
        return books -> {
            GetBooksResponseBuilder response = GetBooksResponse.builder();
            books.stream()
                    .map(book -> Book.builder()
                            .ISBN(book.getISBN())
                            .title(book.getTitle())
                            .build())
                    .forEach(response::book);
            return response.build();
        };
    }
}
