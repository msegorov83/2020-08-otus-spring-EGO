package ru.otus.spring.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты для BookRepository")
@SpringBootTest
@Transactional
class BookRepositoryTest {
    private static final long BOOK_ID = 1L;
    private static final long AUTHOR_ID = 1L;
    private static final String AUTHOR_NAME = "Питер Гуральник";
    private static final long GENRE_ID = 1L;
    private static final String GENRE_NAME = "Биография";
    private static final String BOOK_NAME = "Элвис Пресли. Последний поезд в Мемфис";

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {

        authorRepository.save(new Author(AUTHOR_NAME));
        genreRepository.save(new Genre(GENRE_NAME));
        Book book= new Book(BOOK_NAME);
        Set<Genre> genre = new HashSet();
        genre.add(genreRepository.findById(GENRE_ID));
        Set<Author> author = new HashSet();
        author.add(authorRepository.findById(AUTHOR_ID));
        book.setGenre(genre);
        book.setAuthor(author);

        bookRepository.save(book);
    }

    @Test
    void testFindByIdAllinfo() {
        var actualBook = bookRepository.findByIdAllinfo(BOOK_ID);
        var expectedAuthor = authorRepository.findById(AUTHOR_ID);
        var expectedGenre = genreRepository.findById(GENRE_ID);

        assertThat(actualBook).isNotNull();
        assertThat(actualBook.getName()).isEqualTo(BOOK_NAME);
        assertThat(actualBook.getAuthor()).isEqualTo(expectedAuthor);
        assertThat(actualBook.getGenre()).isEqualTo(expectedGenre);


    }
}