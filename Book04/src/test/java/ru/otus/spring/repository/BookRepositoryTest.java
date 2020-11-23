package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тесты для BookRepository")
@SpringBootTest
class BookRepositoryTest {
    private static final long COUNT = 0;
    private static final String NEW_BOOK_NAME = "Элвис Пресли. Последний поезд в Мемфис";
    private static final String NEW_GENRE = "Биография";
    private static final String NEW_AUTHOR = "Питер Гуральник";
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void testCount() {
        var actualCount =  bookRepository.count();
        assertThat(actualCount).isNotNull();
        assertThat(actualCount).isEqualTo(COUNT);
    }


    @Test
    void testSave() {
        Book book= new Book(NEW_BOOK_NAME);
        Genre genre = genreRepository.save(new Genre(NEW_GENRE ));
        Author author = authorRepository.save(new Author(NEW_AUTHOR));

        Set<Genre> genres = new HashSet();
        genres.add(genre);

        Set<Author> authors = new HashSet();
        authors.add(author);

        book.setGenre(genres);
        book.setAuthor(authors);

        var actualBook = bookRepository.save(book);
        var expectedBook = bookRepository.findById(actualBook.getId());

        assertThat(actualBook).isNotNull();
        assertThat(actualBook.getName()).isEqualTo(expectedBook.get().getName());
    }

    @Test
    void testDeleteById() {
        Book actualBook = bookRepository.findByName(NEW_BOOK_NAME).get();

        bookRepository.deleteById(actualBook.getId());

        var actualCount = bookRepository.count();
        assertThat(actualCount).isEqualTo(COUNT);
    }


}