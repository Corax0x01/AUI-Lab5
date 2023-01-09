package szymanski.jakub.publishing_house.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

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

    private String address;
    private String phoneNumber;
    private String email;
}
