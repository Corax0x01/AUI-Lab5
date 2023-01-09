package szymanski.jakub.publishing_house.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import szymanski.jakub.publishing_house.dto.*;
import szymanski.jakub.publishing_house.entity.PublishingHouse;
import szymanski.jakub.publishing_house.service.PublishingHouseService;

import java.util.Optional;

@RestController
@RequestMapping("api/publishing_houses")
public class PublishingHouseController {

    private PublishingHouseService publishingHouseService;

    @Autowired
    public PublishingHouseController(PublishingHouseService publishingHouseService) {
        this.publishingHouseService = publishingHouseService;
    }

    @GetMapping
    public ResponseEntity<GetPublishingHousesResponse> getPublishingHouses(){
        return ResponseEntity.ok(GetPublishingHousesResponse.entityToDtoMapper().apply(publishingHouseService.findAll()));
    }

    @GetMapping("{name}")
    public ResponseEntity<GetPublishingHouseResponse> getPublishingHouse(@PathVariable("name") String name){
        return publishingHouseService.find(name)
                .map(value -> ResponseEntity.ok(GetPublishingHouseResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postPublishingHouse(@RequestBody PostPublishingHouseRequest request,
                                                        UriComponentsBuilder builder) {
        PublishingHouse publishingHouse = PostPublishingHouseRequest
                .dtoToEntityMapper()
                .apply(request);
        publishingHouseService.create(publishingHouse);
        return ResponseEntity.created(builder.pathSegment("api", "publishing_houses", "{name}")
                .buildAndExpand(publishingHouse.getName()).toUri()).build();
    }

    @PutMapping("{name}")
    public ResponseEntity<Void> putPublishingHouse(@PathVariable("name") String name,
                                                       @RequestBody PutPublishingHouseRequest request) {
        Optional<PublishingHouse> publishingHouse = publishingHouseService.find(name);
        if(publishingHouse.isPresent()){
            PutPublishingHouseRequest.dtoToEntityUpdater()
                    .apply(publishingHouse.get(), request);
            publishingHouseService.update(publishingHouse.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
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
