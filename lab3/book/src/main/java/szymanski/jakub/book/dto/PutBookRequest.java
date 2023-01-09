package szymanski.jakub.book.dto;


import lombok.*;
import szymanski.jakub.book.entities.Book;
import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PutBookRequest {
    private String title;
    private double price;

    public static BiFunction<Book, PutBookRequest, Book> dtoToEntityUpdater(){
        return (book, request) -> {
            if(request.getTitle() != null){
                book.setTitle(request.getTitle());
            }
            if(request.getPrice() != 0.0){
                book.setPrice(request.getPrice());
            }
            return book;
        };
    }
}
