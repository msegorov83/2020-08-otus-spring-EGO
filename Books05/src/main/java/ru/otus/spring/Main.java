package ru.otus.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.GenreRepository;

import java.time.Duration;
import java.util.Collections;


@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@SpringBootApplication
public class Main {



    public static void main(String[] args) throws Exception {

        SpringApplication application = new SpringApplication(Main.class);
        application.setWebApplicationType(WebApplicationType.REACTIVE);

        ApplicationContext context = SpringApplication.run(Main.class);
    }

    @Bean
    public CommandLineRunner demo(AuthorRepository authorRepository, GenreRepository genreRepository, BookRepository bookRepository) throws InterruptedException {
        var piter = new Author("Питер Гуральник");
        var egorov = new Author("Егоров Михаил");

        genreRepository.save(new Genre("Биография")).subscribe(genre -> {  System.out.println(genre.getName()); });
        genreRepository.save(new Genre("Другие")).subscribe(genre -> System.out.println(genre.getName()));


        authorRepository.saveAll(Flux.just(piter, egorov)).subscribe();
        Book book = new Book("Элвис Пресли. Последний поезд в Мемфис");

       // Author curAuthor = new Author();
      //  authorRepository.findByFullName("Питер Гуральник").subscribe(author -> {curAuthor.setId(author.getId()); curAuthor.setFullName(author.getFullName());});
   //     Genre curGenre = new Genre();.delayElements( Duration.ofSeconds( 1 ) );
      //  genreRepository.findByName("Биография").subscribe(genre -> {curGenre.setId(genre.getId()); curGenre.setName(genre.getName());});

// book.setAuthor(Collections.singletonList(curAuthor));
     //   book.setGenre(Collections.singletonList(curGenre));
       // bookRepository.save(book).subscribe();
   /// bookRepository.save(book).delayElement(Duration.ofSeconds( 5) );
        return (args) -> { };
    }


}
