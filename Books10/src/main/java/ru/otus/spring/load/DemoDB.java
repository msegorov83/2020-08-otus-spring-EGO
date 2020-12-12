package ru.otus.spring.load;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repostory.AuthorRepository;
import ru.otus.spring.repostory.BookRepository;
import ru.otus.spring.repostory.GenreRepository;

@Component
public class DemoDB implements CommandLineRunner {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void run(String... args) throws Exception {
        authorRepository.save(new Author("Питер Гуральник"));
        authorRepository.save(new Author("Егоров Михаил"));

        genreRepository.save(new Genre("Биография"));
        genreRepository.save(new Genre("Другие"));

        var idA= authorRepository.findById(1L);
        var idG = genreRepository.findById(1L);

        Book book = new Book("Элвис Пресли. Последний поезд в Мемфис", Collections.singletonList(idA), Collections.singletonList(idG));
        bookRepository.save(book);

    }

}
