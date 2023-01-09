package szymanski.jakub.publishing_house.event.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import szymanski.jakub.publishing_house.entity.PublishingHouse;
import szymanski.jakub.publishing_house.event.dto.PostPublishingHouseRequest;

@Repository
public class PublishingHouseEventRepository {
    private RestTemplate restTemplate;

    @Autowired
    public PublishingHouseEventRepository(@Value("${lab.books.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(PublishingHouse publishingHouse) {
        restTemplate.delete("/publishing_houses/{name}", publishingHouse.getName());
    }

    public void create(PublishingHouse publishingHouse) {
        restTemplate.postForLocation("/publishing_houses", PostPublishingHouseRequest.entityToDtoMapper().apply(publishingHouse));
    }
}
