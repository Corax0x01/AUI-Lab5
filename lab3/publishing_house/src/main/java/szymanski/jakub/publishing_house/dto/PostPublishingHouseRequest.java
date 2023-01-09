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
public class PostPublishingHouseRequest {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    public static Function<PostPublishingHouseRequest, PublishingHouse> dtoToEntityMapper(){
        return request -> PublishingHouse.builder()
                .name(request.getName())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .build();
    }
}
