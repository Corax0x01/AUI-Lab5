package szymanski.jakub.publishing_house.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import szymanski.jakub.publishing_house.entity.PublishingHouse;
import szymanski.jakub.publishing_house.event.repository.PublishingHouseEventRepository;
import szymanski.jakub.publishing_house.repository.PublishingHouseRepository;

import java.util.List;
import java.util.Optional;


@Service
public class PublishingHouseService {

    private PublishingHouseRepository repository;
    private PublishingHouseEventRepository eventRepository;

    @Autowired
    public PublishingHouseService(PublishingHouseEventRepository eventRepository, PublishingHouseRepository repository){
        this.eventRepository = eventRepository;
        this.repository = repository;
    }

    public Optional<PublishingHouse> find(String name){
        return repository.findById(name);
    }

    public List<PublishingHouse> findAll(){
        return repository.findAll();
    }

    @Transactional
    public void create(PublishingHouse publishingHouse){
        repository.save(publishingHouse);
        eventRepository.create(publishingHouse);
    }

    @Transactional
    public void update(PublishingHouse publishingHouse){
        repository.save(publishingHouse);
    }

    @Transactional
    public void delete(PublishingHouse publishingHouse){
        repository.delete(publishingHouse);
        eventRepository.delete(publishingHouse);
    }
}
