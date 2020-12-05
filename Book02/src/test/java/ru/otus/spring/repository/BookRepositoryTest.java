package ru.otus.spring.repository;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookAuthor;
import ru.otus.spring.domain.BookGenre;
import ru.otus.spring.domain.Genre;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тесты для BookRepository")
@SpringBootTest
class BookRepositoryTest {

    private static final long ZERO_COUNT = 0;
    private static final long COUNT = 1L;
    private static final long BOOK_ID = 1L;
    private static final long NEW_BOOK_ID = 2L;
    private static final long AUTHOR_ID = 1L;
    private static final long GENRE_ID = 1L;
    private static final String CUR_BOOK_NAME = "Элвис Пресли. Последний поезд в Мемфис";
    private static final String NEW_BOOK_NAME = "Careless Love: The Unmaking of Elvis Presley";

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookAuthorRepository bookAuthorRepository;

    @Autowired
    private BookGenreRepository bookGenreRepository;

    @Transactional
    @Test
    void findByIdAllInfo() {
        var actualBook = bookRepository.findById(BOOK_ID).get();
        var expectedAuthor = authorRepository.findById(AUTHOR_ID);
        var expectedGenre = genreRepository.findById(GENRE_ID);

        var actualAuthors = actualBook.getAuthors();
        BookAuthor actualBookAuthor= (BookAuthor) actualAuthors.stream().findFirst().get();
        Hibernate.initialize(authorRepository.findById(actualBookAuthor.getAuthor().getId()));
        Author actualAuthor = authorRepository.findById(actualBookAuthor.getAuthor().getId());

        var actualGenres = actualBook.getGenres();
        BookGenre actualBookGenre = (BookGenre) actualGenres.stream().findFirst().get();
        Hibernate.initialize(genreRepository.findById(actualBookGenre.getAuthor().getId()));
        Genre actualGenre = genreRepository.findById(actualBookGenre.getAuthor().getId());

        assertThat(actualBook).isNotNull();
        assertThat(actualBook.getName()).isEqualTo(CUR_BOOK_NAME);
        assertThat(actualAuthor.getFullName()).isEqualTo(expectedAuthor.getFullName());
        assertThat(actualGenre.getName()).isEqualTo(expectedGenre.getName());
    }

    @Test
    void testFindAll() {
        Book book = new Book(CUR_BOOK_NAME);
        book.setId(BOOK_ID);
        Set<Genre> genre = new HashSet();
        genre.add(genreRepository.findById(GENRE_ID));
        List<Book> expectedBook = new ArrayList<>();
        expectedBook.add(book);

        var actualBooks = bookRepository.findAll();
        assertThat(actualBooks).isNotNull();
        assertThat(actualBooks.stream().findFirst().toString()).isEqualTo(expectedBook.stream().findFirst().toString());
    }

    @Test
    void testCount() {
        var actualCount =  bookRepository.count();
        assertThat(actualCount).isNotNull();
        assertThat(actualCount).isEqualTo(COUNT);
    }

    @Transactional
    @Test
    void testDeleteById() {
        var book = bookRepository.findById(BOOK_ID);
        bookAuthorRepository.deleteByBook(book.get());
        bookGenreRepository.deleteByBook(book.get());
        bookRepository.deleteById(BOOK_ID);

        var actualCount = bookRepository.count();
        assertThat(actualCount).isEqualTo(ZERO_COUNT);
    }

    @Transactional
    @Test
    void testSave() {
        Book book= new Book(NEW_BOOK_NAME);
        var actualBook = bookRepository.save(book);

        Hibernate.initialize(authorRepository.findById(AUTHOR_ID));
        BookAuthor actualBookAuthor = new BookAuthor(actualBook, authorRepository.findById(AUTHOR_ID));
        bookAuthorRepository.save(actualBookAuthor);

        Hibernate.initialize(genreRepository.findById(GENRE_ID));
        BookGenre actualBookGenre = new BookGenre(actualBook, genreRepository.findById(GENRE_ID));
        bookGenreRepository.save(actualBookGenre);
        var expectedBook = bookRepository.findById(NEW_BOOK_ID).get();

        assertThat(actualBook).isNotNull();
        assertThat(actualBook.getName()).isEqualTo(expectedBook.getName());

    }
}
