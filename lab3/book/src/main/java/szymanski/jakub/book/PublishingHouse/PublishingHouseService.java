package szymanski.jakub.book.PublishingHouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class PublishingHouseService {

    private PublishingHouseRepository repository;

    @Autowired
    public PublishingHouseService(PublishingHouseRepository repository){
        this.repository = repository;
    }

    public Optional<PublishingHouse> find(String name){
        return repository.findById(name);
    }

    @Transactional
    public void create(PublishingHouse publishingHouse){
        repository.save(publishingHouse);
    }

    @Transactional
    public void delete(PublishingHouse publishingHouse){
        repository.delete(publishingHouse);
    }
}
