package szymanski.jakub.book.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import szymanski.jakub.book.PublishingHouse.PublishingHouse;

import javax.persistence.*;
import java.io.Serializable;


@AllArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long ISBN;

    private String title;
    private String author;
    private double price;

    @ManyToOne
    @JoinColumn(name = "publishingHouse")
    private PublishingHouse publishingHouse;
}
