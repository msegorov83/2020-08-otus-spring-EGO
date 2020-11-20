package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.*;


import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Тесты для BookRepositoryJpa")
@SpringBootTest
class BookRepositoryJpaImplTest {
    private static final long ZERO_COUNT = 0;
    private static final long COUNT = 1L;
    private static final long BOOK_ID = 1L;
    private static final long NEW_BOOK_ID = 2L;
    private static final long AUTHOR_ID = 1L;
    private static final long GENRE_ID = 1L;
    private static final String CUR_BOOK_NAME = "Элвис Пресли. Последний поезд в Мемфис";
    private static final String NEW_BOOK_NAME = "Careless Love: The Unmaking of Elvis Presley";

    @Autowired
    private BookRepositoryJpaImpl bookRepositoryJpa;

    @Autowired
    private AuthorRepositoryJpaImpl authorRepositoryJpa;

    @Autowired
    private GenreRepositoryJpaImpl genreRepositoryJpa;

    @Test
    void testFindByIdAllInfo() {
        var actualBook = bookRepositoryJpa.findByIdAllInfo(BOOK_ID);
        var expectedAuthor = authorRepositoryJpa.findById(AUTHOR_ID);
        var expectedGenre = genreRepositoryJpa.findById(GENRE_ID);

        assertThat(actualBook).isNotNull();
        assertThat(actualBook.getName()).isEqualTo(CUR_BOOK_NAME);
        assertThat(actualBook.getAuthor().getFullName()).isEqualTo(expectedAuthor.getFullName());
        assertThat(actualBook.getGenre().getgName()).isEqualTo(expectedGenre.getgName());
    }

    @Test
    void testFindAll() {
        Book book = new Book(CUR_BOOK_NAME);
        book.setId(BOOK_ID);
        Set<Genre> genre = new HashSet();
        genre.add(genreRepositoryJpa.findById(GENRE_ID));
        Set<Author> author = new HashSet();
        author.add(authorRepositoryJpa.findById(AUTHOR_ID));
        book.setGenre(genre);
        book.setAuthor(author);
        List<Book> expectedBook = new ArrayList<>();
        expectedBook.add(book);

        var actualBooks = bookRepositoryJpa.findAll();
        assertThat(actualBooks).isNotNull();
        assertThat(actualBooks.stream().findFirst().toString()).isEqualTo(expectedBook.stream().findFirst().toString());
    }


    @Test
    void testCount() {
        var actualCount =  bookRepositoryJpa.count();
        assertThat(actualCount).isNotNull();
        assertThat(actualCount).isEqualTo(COUNT);
    }

    @Transactional
    @Test
    void testDeleteById() {
        bookRepositoryJpa.deleteById(BOOK_ID);
        var actualCount = bookRepositoryJpa.count();
        assertThat(actualCount).isEqualTo(ZERO_COUNT);
    }

    @Transactional
    @Test
    void testSave() {
        Book book= new Book(NEW_BOOK_NAME);
        Set<Genre> genre = new HashSet();
        genre.add(genreRepositoryJpa.findById(GENRE_ID));
        Set<Author> author = new HashSet();
        author.add(authorRepositoryJpa.findById(AUTHOR_ID));
        book.setGenre(genre);
        book.setAuthor(author);

        var actualBook = bookRepositoryJpa.save(book);
        var expectedBook = bookRepositoryJpa.findByIdAllInfo(NEW_BOOK_ID);

        assertThat(actualBook).isNotNull();
        assertThat(actualBook.getName()).isEqualTo(expectedBook.getName());
    }



}