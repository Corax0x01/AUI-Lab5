package szymanski.jakub.publishing_house.dto;

import lombok.*;
import szymanski.jakub.publishing_house.entity.PublishingHouse;

import java.util.function.Function;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetPublishingHouseResponse {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    public static Function<PublishingHouse, GetPublishingHouseResponse> entityToDtoMapper(){
        return publishingHouse -> GetPublishingHouseResponse.builder()
                .name(publishingHouse.getName())
                .address(publishingHouse.getAddress())
                .phoneNumber(publishingHouse.getPhoneNumber())
                .email(publishingHouse.getEmail())
                .build();
    }
}
