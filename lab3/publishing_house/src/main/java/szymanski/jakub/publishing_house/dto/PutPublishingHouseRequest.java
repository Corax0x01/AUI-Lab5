package szymanski.jakub.publishing_house.dto;

import lombok.*;
import szymanski.jakub.publishing_house.entity.PublishingHouse;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PutPublishingHouseRequest {
    private String address;
    private String phoneNumber;
    private String email;

    public static BiFunction<PublishingHouse, PutPublishingHouseRequest, PublishingHouse> dtoToEntityUpdater(){
        return (publishingHouse, request) -> {
            if(request.getAddress() != null){
                publishingHouse.setAddress(request.getAddress());
            }
            if(request.getPhoneNumber() != null){
                publishingHouse.setPhoneNumber(request.getPhoneNumber());
            }
            if(request.getEmail() != null){
                publishingHouse.setEmail(request.getEmail());
            }
            return publishingHouse;
        };
    }
}
