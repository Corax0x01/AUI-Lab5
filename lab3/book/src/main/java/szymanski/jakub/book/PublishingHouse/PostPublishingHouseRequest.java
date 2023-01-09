package szymanski.jakub.book.PublishingHouse;

import lombok.*;

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

    public static Function<PostPublishingHouseRequest, PublishingHouse> dtoToEntityMapper() {
        return request -> PublishingHouse.builder()
                .name(request.getName())
                .build();
    }
}
