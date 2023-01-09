package szymanski.jakub.publishing_house.dto;


import lombok.*;
import szymanski.jakub.publishing_house.entity.PublishingHouse;

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
public class GetPublishingHousesResponse {

    @Singular
    private List<String> publishingHouses;

    public static Function<Collection<PublishingHouse>, GetPublishingHousesResponse> entityToDtoMapper(){
        return publishingHouses -> {
            GetPublishingHousesResponseBuilder response = GetPublishingHousesResponse.builder();
            publishingHouses.stream()
                    .map(PublishingHouse::getName)
                    .forEach(response::publishingHouse);
            return response.build();
        };
    }

}
