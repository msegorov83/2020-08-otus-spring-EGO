package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.*;

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

    private AuthorJdbcImpl authorJdbc;
    private GenreJdbcImpl genreJdbc;
    private Bookjdbc bookjdbc;

    public CommandShell(AuthorJdbcImpl authorJdbc, GenreJdbcImpl genreJdbc, Bookjdbc bookjdbc) {

        this.authorJdbc = authorJdbc;
        this.genreJdbc = genreJdbc;
        this.bookjdbc = bookjdbc;

    }

    @ShellMethod(value = "Get authors", key = {"authors"})
    public String getAuthors(){
        System.out.println( authorJdbc.findAll());
        //System.out.println( authorRepository.findAll());
        return "authors";
    }

    @ShellMethod(value = "Demo mode", key ={"demo"})
    public String demoMode() {
        authorJdbc.save(new Author("Питер Гуральник"));
        genreJdbc.save(new Genre("Биография"));
        genreJdbc.save(new Genre("Другие"));
        Book book= new Book("Элвис Пресли. Последний поезд в Мемфис");
        Set<Genre> genre = new HashSet();
        genre.add(genreJdbc.findById(1L));
        Set<Author> author = new HashSet();
        author.add(authorJdbc.findById(1L));
        book.setGenre(genre);
        book.setAuthor(author);

        bookjdbc.save(book);

        return "demo";
    }

    @ShellMethod(value = "Add author (full_name)", key = {"adda"})
    public String addAuthor( @ShellOption String fullName ) {
        authorJdbc.save(new Author(fullName));
        //authorRepository.save(new Author(fullName));
        return "added " + fullName;
    }

    @ShellMethod(value = "Get author (id)", key = {"author"})
    public String getAuthor(@ShellOption long id ) {
        Author author = authorJdbc.findById(id);
      //  Author author = authorRepository.findById(id);
        System.out.println("Author: " + author.getFullName());
        return "author " + id;
    }

    @ShellMethod(value = "Get count authors", key = {"counta"})
    public String countAuthors(){
        System.out.println("All authors count " + authorJdbc.count());
        //System.out.println("All authors count " + authorRepository.count());
        return "counta";
    }

    @ShellMethod(value = "Delete author (id)", key = {"dela"})
    public String delAuthor(@ShellOption long id) {
        authorJdbc.deleteById(id);
     //   authorRepository.deleteById(id);
        return "deleted " + id;
    }

    @ShellMethod(value = "Get genres", key = {"genres"})
    public String getGenres(){
        System.out.println(genreJdbc.findAll());
        //System.out.println(genreRepository.findAll());
        return "genres";
    }

    @ShellMethod(value = "Add genre (name)", key = {"addg"})
    public String addGenre(@ShellOption String name ) {
        genreJdbc.save(new Genre(name));
        //genreRepository.save(new Genre(name));
        return "added " + name;
    }

    @ShellMethod(value = "Get genre (id)", key = {"genre"})
    public String getGenre(@ShellOption long id ) {
        Genre genre = genreJdbc.findById(id);
        //Genre genre = genreRepository.findById(id);
        System.out.println("Genre: " + genre.getName());
        return "genre " + id;
    }

    @ShellMethod(value = "Delete genre (id)", key = {"delg"})
    public String delGenre(@ShellOption long id) {
       // genreRepository.deleteById(id);
        genreJdbc.deleteById(id);
        return "deleted " + id;
    }

    @ShellMethod(value = "Get count genres", key = {"countg"})
    public String countGenres(){
        System.out.println("All genres count " + genreJdbc.count());
        //System.out.println("All genres count " + genreRepository.count());
        return "countg";
    }

    @ShellMethod(value = "Get books", key = {"books"})
    public String getBooks(){
        System.out.println(bookjdbc.findAll());
        //System.out.println(bookRepository.findAll());
        return "books";
    }

    @ShellMethod(value = "Get book (id)", key = {"book"})
    public String getBook(@ShellOption long id) {
        Book book = bookjdbc.findByIdAllinfo(id);
        System.out.println("Book: " + book.getName() + " Author: " + book.getAuthor().getFullName() + " Genre: " + book.getGenre().getName());
      //  Book book = bookRepository.findByIdAllinfo(id);
       // System.out.println("Book: " + book.getName() + " Author: " + book.getAuthor().getFullName() + " Genre: " + book.getGenre().getName());

        return "book " + id;
    }

    @ShellMethod(value = "Get count books", key = {"countb"})
    public String countBooks(){
        System.out.println("All books count " + bookjdbc.count());
       // System.out.println("All books count " + bookRepository.count());
        return "countb";
    }

    @ShellMethod(value = "Add book (name, author_id, genre_id )", key = {"addb"})
    public String addBook(@ShellOption String name, @ShellOption long authorId, @ShellOption long genreId ) {
        Book book = new Book(name);

        Set<Genre> genre = new HashSet();
        genre.add(genreJdbc.findById(genreId));
       // genre.add(genreRepository.findById(genreId));
        Set<Author> author = new HashSet();
        author.add(authorJdbc.findById(authorId));
       // author.add(authorRepository.findById(authorId));
        book.setGenre(genre);
        book.setAuthor(author);

       // bookRepository.save(book);
        bookjdbc.save(book);
        return "added " + name;
    }

    @ShellMethod(value = "Delete book (id)", key = {"delb"})
    public String delBook(@ShellOption long id) {
        bookjdbc.deleteById(id);
        //bookRepository.deleteById(id);
        return "deleted " + id;
    }

    @ShellMethod(value = "Get book (name)", key = {"booksa"})
    public String getBooksAuthor(@ShellOption String name) {
        List<Book> books = bookjdbc.findAllBookByAuthor(name);
        System.out.println("Book: " + books);

        return "books " + name;
    }

}
