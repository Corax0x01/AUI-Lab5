package szymanski.jakub.book.PublishingHouse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/publishing_houses")
public class PublishingHouseController {

    private PublishingHouseService publishingHouseService;

    @Autowired
    public PublishingHouseController(PublishingHouseService publishingHouseService) {
        this.publishingHouseService = publishingHouseService;
    }

    @PostMapping("")
    public ResponseEntity<Void> postPublishingHouse(@RequestBody PostPublishingHouseRequest request,
                                                        UriComponentsBuilder builder) {
        PublishingHouse publishingHouse = PostPublishingHouseRequest
                .dtoToEntityMapper()
                .apply(request);
        publishingHouseService.create(publishingHouse);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "publishing_houses", "{name}")
                        .buildAndExpand(publishingHouse.getName()).toUri())
                .build();
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deletePublishingHouse(@PathVariable("name") String name){
        Optional<PublishingHouse> publishingHouse = publishingHouseService.find(name);
        if(publishingHouse.isPresent()){
            publishingHouseService.delete(publishingHouse.get());
            return ResponseEntity.accepted().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
