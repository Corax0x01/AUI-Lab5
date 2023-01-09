package szymanski.jakub.book.dto;

import lombok.*;
import szymanski.jakub.book.PublishingHouse.PublishingHouse;
import szymanski.jakub.book.entities.Book;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PostBookRequest {
    String title;
    String author;
    double price;
    String publishingHouse;

    public static Function<PostBookRequest, Book> dtoToEntityMapper(
            Function<String, PublishingHouse> publishingHouseFunction,
            Supplier<PublishingHouse> publishingHouseSupplier) {
        return request -> Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .price(request.getPrice())
                .publishingHouse(publishingHouseFunction.apply(request.getPublishingHouse()))
                .build();
        }
}
