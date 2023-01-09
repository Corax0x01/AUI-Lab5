package szymanski.jakub.book.PublishingHouse;

import lombok.*;
import lombok.experimental.SuperBuilder;
import szymanski.jakub.book.entities.Book;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "publishing_houses")
public class PublishingHouse implements Serializable {
    @Id
    private String name;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "publishingHouse")
    @ToString.Exclude
    private List<Book> books;
}
