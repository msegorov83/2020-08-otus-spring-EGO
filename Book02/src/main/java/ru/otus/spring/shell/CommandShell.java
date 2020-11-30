package ru.otus.spring.shell;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookAuthor;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookAuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.GenreRepository;


/*Commands for example

demo - тестовые данные для БД

authors
adda "Егоров Михаил"
author 2
counta
dela 2

genres
addg "Фантастика"
genre 3
countg
delg 3

books
book 1
addb "Careless Love: The Unmaking of Elvis Presley" 1 1
booksa "Питер Гуральник"
countb
delb 2

 */

@ShellComponent
public class CommandShell {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final BookAuthorRepository bookAuthorRepository;

    public CommandShell(AuthorRepository authorRepository, GenreRepository genreRepository, BookRepository bookRepository, BookAuthorRepository bookAuthorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.bookAuthorRepository = bookAuthorRepository;
    }

    @ShellMethod(value = "Get authors", key = {"authors"})
    public String getAuthors() {
        out.println(authorRepository.findAll());
        return "authors";
    }

    @ShellMethod(value = "Demo mode", key = {"demo"})
    public String demoMode() {
        authorRepository.save(new Author("Питер Гуральник"));
        genreRepository.save(new Genre("Биография"));
        genreRepository.save(new Genre("Другие"));
        var book = new Book("Элвис Пресли. Последний поезд в Мемфис");
        Set<Genre> genre = new HashSet<>();
        genre.add(genreRepository.findById(1L));
        book.setGenre(genre);

        var newBook = bookRepository.save(book);
        BookAuthor bookAuthor = new BookAuthor(newBook,authorRepository.findById(1L));
        bookAuthorRepository.save(bookAuthor);
        return "demo";
    }

    @ShellMethod(value = "Add author (full_name)", key = {"adda"})
    public String addAuthor(@ShellOption String fullName) {
        authorRepository.save(new Author(fullName));
        return "added " + fullName;
    }

    @ShellMethod(value = "Get author (id)", key = {"author"})
    public String getAuthor(@ShellOption long id) {
        Author author = authorRepository.findById(id);
        out.println("Author: " + author.getFullName());
        return "author " + id;
    }

    @ShellMethod(value = "Get count authors", key = {"counta"})
    public String countAuthors() {
        out.println("All authors count " + authorRepository.count());
        return "counta";
    }

    @ShellMethod(value = "Delete author (id)", key = {"dela"})
    public String delAuthor(@ShellOption long id) {
        authorRepository.deleteById(id);
        return "deleted " + id;
    }

    @ShellMethod(value = "Get genres", key = {"genres"})
    public String getGenres() {
        out.println(genreRepository.findAll());
        return "genres";
    }

    @ShellMethod(value = "Add genre (name)", key = {"addg"})
    public String addGenre(@ShellOption String name) {
        genreRepository.save(new Genre(name));
        return "added " + name;
    }

    @ShellMethod(value = "Get genre (id)", key = {"genre"})
    public String getGenre(@ShellOption long id) {
        Genre genre = genreRepository.findById(id);
        out.println("Genre: " + genre.getName());
        return "genre " + id;
    }

    @ShellMethod(value = "Delete genre (id)", key = {"delg"})
    public String delGenre(@ShellOption long id) {
        genreRepository.deleteById(id);
        return "deleted " + id;
    }

    @ShellMethod(value = "Get count genres", key = {"countg"})
    public String countGenres() {
        out.println("All genres count " + genreRepository.count());
        return "countg";
    }

    @ShellMethod(value = "Get books", key = {"books"})
    public String getBooks() {
        out.println(bookRepository.findAll());
        return "books";
    }

    @ShellMethod(value = "Get book (id)", key = {"book"})
    public String getBook(@ShellOption long id) {
        Book book = bookRepository.findByIdAllInfo(id);
        var authors = book.getAuthors();
        BookAuthor bookAuthor = (BookAuthor) authors.stream().findFirst().get();
        Author author = authorRepository.findById(bookAuthor.getAuthor().getId());
        out.println("Book: " + book.getName() + " Author: " + author.getFullName() + " Genre: " + book.getGenre().getName());

        return "book " + id;
    }

    @ShellMethod(value = "Get count books", key = {"countb"})
    public String countBooks() {
        out.println("All books count " + bookRepository.count());
        return "countb";
    }

    @ShellMethod(value = "Add book (name, author_id, genre_id )", key = {"addb"})
    public String addBook(@ShellOption String name, @ShellOption long authorId, @ShellOption long genreId) {
        Book book = new Book(name);

        Set<Genre> genre = new HashSet<>();
        genre.add(genreRepository.findById(genreId));
        book.setGenre(genre);

        var newBook = bookRepository.save(book);
        BookAuthor bookAuthor = new BookAuthor(newBook, authorRepository.findById(authorId));
        bookAuthorRepository.save(bookAuthor);

        return "added " + name;
    }

    @ShellMethod(value = "Delete book (id)", key = {"delb"})
    public String delBook(@ShellOption long id) {
        var book = bookRepository.findById(id);
        bookAuthorRepository.deleteByBook(book.get());
        bookRepository.deleteById(id);
        return "deleted " + id;
    }


    @ShellMethod(value = "Get book (name)", key = {"booksa"})
    public String getBooksAuthor(@ShellOption String name) {
        Author author = authorRepository.findByFullName(name);
        var bookAuthorList = bookAuthorRepository.findByAuthor(author);
        List<Long> books = new ArrayList<>();
        bookAuthorList.stream().filter(bookAuthor -> books.add(bookAuthor.getBook().getId())).toArray();

        out.println("Book: " + bookRepository.findAllById(books));
        return "books " + name;
    }

}
