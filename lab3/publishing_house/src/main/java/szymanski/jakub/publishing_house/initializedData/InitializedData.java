package szymanski.jakub.publishing_house.initializedData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import szymanski.jakub.publishing_house.entity.PublishingHouse;
import szymanski.jakub.publishing_house.service.PublishingHouseService;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {

    private final PublishingHouseService publishingHouseService;

    @Autowired
    public InitializedData(PublishingHouseService publishingHouseService) {
        this.publishingHouseService = publishingHouseService;
    }

    @PostConstruct
    private void init() {
        PublishingHouse pwn = PublishingHouse.builder()
                .name("PWN")
                .address("ul. Gottlieba Daimlera 2, 02-460 Warszawa")
                .phoneNumber("22 695 43 21")
                .email("recepcja@pwn.pl")
                .build();

        PublishingHouse super_nowa = PublishingHouse.builder()
                .name("SUPERNOWA")
                .address("ul. Nowowiejska 10, 00-653 Warszawa")
                .phoneNumber("22 825-61-12")
                .email("redakcja@supernowa.pl")
                .build();

        publishingHouseService.create(pwn);
        publishingHouseService.create(super_nowa);
    }
}
