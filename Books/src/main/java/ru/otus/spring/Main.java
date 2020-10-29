package ru.otus.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repostory.AuthorRepository;
import ru.otus.spring.repostory.BookRepository;
import ru.otus.spring.repostory.GenreRepository;

import javax.annotation.PostConstruct;
import java.util.Collections;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @PostConstruct
    public void init() {

        authorRepository.save(new Author("Питер Гуральник"));
        authorRepository.save(new Author("Егоров Михаил"));

        genreRepository.save(new Genre("Биография"));
        genreRepository.save(new Genre("Другие"));

        var idG = genreRepository.findById(1L);
        var idA=  authorRepository.findById(1L);
        Book book = new Book("Элвис Пресли. Последний поезд в Мемфис", Collections.singletonList(idA), Collections.singletonList(idG) );
        bookRepository.save(book);

    }
}
