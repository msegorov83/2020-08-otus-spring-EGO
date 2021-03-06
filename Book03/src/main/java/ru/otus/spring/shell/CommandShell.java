package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookAuthor;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.AuthorRepositoryJpaImpl;
import ru.otus.spring.repository.BookAuthorRepositoryJpaImpl;
import ru.otus.spring.repository.BookRepositoryJpaImpl;
import ru.otus.spring.repository.GenreRepositoryJpaImpl;
import ru.otus.spring.service.BookService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private BookRepositoryJpaImpl bookRepositoryJpa;
    private AuthorRepositoryJpaImpl authorRepositoryJpa;
    private GenreRepositoryJpaImpl genreRepositoryJpa;
    private BookService bookService;
    private BookAuthorRepositoryJpaImpl bookAuthorRepositoryJpaImpl;

    public CommandShell( BookAuthorRepositoryJpaImpl bookAuthorRepositoryJpaImpl, BookRepositoryJpaImpl bookRepositoryJpa, AuthorRepositoryJpaImpl authorRepositoryJpa, GenreRepositoryJpaImpl genreRepositoryJpa, BookService bookService) {
        this.bookRepositoryJpa = bookRepositoryJpa;
        this.authorRepositoryJpa = authorRepositoryJpa;
        this.genreRepositoryJpa = genreRepositoryJpa;
        this.bookService = bookService;
        this.bookAuthorRepositoryJpaImpl = bookAuthorRepositoryJpaImpl;
    }

    @ShellMethod(value = "Get authors", key = {"authors"})
    public String getAuthors(){
        System.out.println( authorRepositoryJpa.findAll());
        return "authors";
    }

    @ShellMethod(value = "Demo mode", key ={"demo"})
    public String demoMode() {
        authorRepositoryJpa.save(new Author("Питер Гуральник"));
        genreRepositoryJpa.save(new Genre("Биография"));
        genreRepositoryJpa.save(new Genre("Другие"));
        Book book = new Book("Элвис Пресли. Последний поезд в Мемфис");

        Set<Genre> genre = new HashSet();
        genre.add(genreRepositoryJpa.findById(1L));
        book.setGenre(genre);

        var newBook = bookRepositoryJpa.save(book);
        BookAuthor bookAuthor = new BookAuthor(newBook,authorRepositoryJpa.findById(1L));
        bookAuthorRepositoryJpaImpl.save(bookAuthor);

        return "demo";
    }

    @ShellMethod(value = "Add author (full_name)", key = {"adda"})
    public String addAuthor( @ShellOption String fullName ) {
        authorRepositoryJpa.save(new Author(fullName));
        return "added " + fullName;
    }

    @ShellMethod(value = "Get author (id)", key = {"author"})
    public String getAuthor(@ShellOption long id ) {
        Author author = authorRepositoryJpa.findById(id);
        System.out.println("Author: " + author.getFullName());
        return "author " + id;
    }

    @ShellMethod(value = "Get count authors", key = {"counta"})
    public String countAuthors(){
        System.out.println("All authors count " + authorRepositoryJpa.count());
        return "counta";
    }

    @ShellMethod(value = "Delete author (id)", key = {"dela"})
    public String delAuthor(@ShellOption long id) {
        authorRepositoryJpa.deleteById(id);
        return "deleted " + id;
    }

    @ShellMethod(value = "Get genres", key = {"genres"})
    public String getGenres(){
        System.out.println(genreRepositoryJpa.findAll());
        return "genres";
    }

    @ShellMethod(value = "Add genre (name)", key = {"addg"})
    public String addGenre(@ShellOption String name ) {
        genreRepositoryJpa.save(new Genre(name));
        return "added " + name;
    }

    @ShellMethod(value = "Get genre (id)", key = {"genre"})
    public String getGenre(@ShellOption long id ) {
        Genre genre = genreRepositoryJpa.findById(id);
        System.out.println("Genre: " + genre.getgName());
        return "genre " + id;
    }

    @ShellMethod(value = "Delete genre (id)", key = {"delg"})
    public String delGenre(@ShellOption long id) {
        genreRepositoryJpa.deleteById(id);
        return "deleted " + id;
    }

    @ShellMethod(value = "Get count genres", key = {"countg"})
    public String countGenres(){
        System.out.println("All genres count " + genreRepositoryJpa.count());
        return "countg";
    }

    @ShellMethod(value = "Get books", key = {"books"})
    public String getBooks(){
        System.out.println(bookRepositoryJpa.findAll());
        return "books";
    }

    @ShellMethod(value = "Get book (id)", key = {"book"})
    public String getBook(@ShellOption long id) {
        Book book = bookRepositoryJpa.findByIdAllInfo(id);
        var authors = book.getAuthors();

        BookAuthor bookAuthor= (BookAuthor) authors.stream().findFirst().get();
        Author author = authorRepositoryJpa.findById(  bookAuthor.getAuthor().getId() );

        System.out.println("Book: " + book.getName() + " Author: " + author.getFullName() + " Genre: " + book.getGenre().getgName());
        return "book " + id;
    }

    @ShellMethod(value = "Get count books", key = {"countb"})
    public String countBooks(){
        System.out.println("All books count " + bookRepositoryJpa.count());
        return "countb";
    }

    @ShellMethod(value = "Add book (name, author_id, genre_id )", key = {"addb"})
    public String addBook(@ShellOption String name, @ShellOption long authorId, @ShellOption long genreId ) {
        Book book = new Book(name);

        Set<Genre> genre = new HashSet();
        genre.add(genreRepositoryJpa.findById(genreId));
        book.setGenre(genre);

        var newBook = bookRepositoryJpa.save(book);
        BookAuthor bookAuthor = new BookAuthor( newBook, authorRepositoryJpa.findById(authorId) );
        bookAuthorRepositoryJpaImpl.save(bookAuthor);

        return "added " + name;
    }

    @ShellMethod(value = "Delete book (id)", key = {"delb"})
    public String delBook(@ShellOption long id) {
        bookAuthorRepositoryJpaImpl.deleteByBookId(id);
        bookRepositoryJpa.deleteById(id);
        return "deleted " + id;
    }

    @ShellMethod(value = "Get book (name)", key = {"booksa"})
    public String getBooksAuthor(@ShellOption String name) {
        List<Book> books = bookService.findAllBookByAuthor(name);
        System.out.println("Book: " + books);

        return "books " + name;
    }

}
