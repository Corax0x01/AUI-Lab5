package szymanski.jakub.book.initializedData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import szymanski.jakub.book.PublishingHouse.PublishingHouse;
import szymanski.jakub.book.PublishingHouse.PublishingHouseService;
import szymanski.jakub.book.entities.Book;
import szymanski.jakub.book.service.BookService;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {

    private final BookService bookService;
    private final PublishingHouseService publishingHouseService;

    @Autowired
    public InitializedData(BookService bookService, PublishingHouseService publishingHouseService) {
        this.bookService = bookService;
        this.publishingHouseService = publishingHouseService;
    }

    @PostConstruct
    private void init() {
        PublishingHouse pwn = PublishingHouse.builder()
                .name("PWN")
                .build();

        PublishingHouse super_nowa = PublishingHouse.builder()
                .name("SUPERNOWA")
                .build();

        publishingHouseService.create(pwn);
        publishingHouseService.create(super_nowa);


        Book ostatnie_zyczenie = Book.builder()
                .title("Ostatnie życzenie")
                .author("Andrzej Sapkowski")
                .publishingHouse(super_nowa)
                .price(39.99)
                .build();

        Book miecz_przeznaczenia = Book.builder()
                .title("Miecz przeznaczenia")
                .author("Andrzej Sapkowski")
                .publishingHouse(super_nowa)
                .price(39.99)
                .build();

        Book krew_elfow = Book.builder()
                .title("Krew elfów")
                .author("Andrzej Sapkowski")
                .publishingHouse(super_nowa)
                .price(39.99)
                .build();

        Book czas_pogardy = Book.builder()
                .title("Czas pogardy")
                .author("Andrzej Sapkowski")
                .publishingHouse(super_nowa)
                .price(39.99)
                .build();

        Book slownik = Book.builder()
                .title("Słownik poprawnej polszczyzny PWN")
                .author("Witold Doroszewski")
                .publishingHouse(pwn)
                .price(29.99)
                .build();

        bookService.create(ostatnie_zyczenie);
        bookService.create(miecz_przeznaczenia);
        bookService.create(krew_elfow);
        bookService.create(czas_pogardy);
        bookService.create(slownik);
    }
}
