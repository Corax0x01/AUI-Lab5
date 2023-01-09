package szymanski.jakub.publishing_house.event.dto;

import lombok.*;
import szymanski.jakub.publishing_house.entity.PublishingHouse;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostPublishingHouseRequest {
    private String name;

    public static Function<PublishingHouse, PostPublishingHouseRequest> entityToDtoMapper() {
        return publishingHouse -> PostPublishingHouseRequest.builder()
                .name(publishingHouse.getName())
                .build();
    }
}
