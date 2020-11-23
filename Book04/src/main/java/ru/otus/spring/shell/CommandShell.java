package ru.otus.spring.shell;


import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.GenreRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/*Commands for example

demo - тестовые данные для БД

authors
adda "Егоров Михаил"
author <id автора>
counta
dela <id автора>

genres
addg "Фантастика"
genre <id жанра>
countg
delg <id жанра>

books
book <id книги>
addb "Careless Love: The Unmaking of Elvis Presley" <id автора> <id жанра>
countb
delb <id книги>

 */

@ShellComponent
public class CommandShell {

    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;
    private BookRepository bookRepository;

    public CommandShell(AuthorRepository authorRepository, GenreRepository genreRepository, BookRepository bookRepository ) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
    }

    @ShellMethod(value = "Demo mode", key ={"demo"})
    public String demoMode() {
        Author author1 = authorRepository.save(new Author("Питер Гуральник"));
        Genre genretmp1 = genreRepository.save(new Genre("Биография"));
        Genre genretmp2 = genreRepository.save(new Genre("Другие"));

        Book book= new Book("Элвис Пресли. Последний поезд в Мемфис");

        Set<Genre> genre = new HashSet();
        genre.add(genreRepository.findById(genretmp1.getId()).get());
        Set<Author> author = new HashSet();
        author.add(authorRepository.findById(author1.getId()).get());
        book.setGenre(genre);
        book.setAuthor(author);

        bookRepository.save(book);
        return "demo";
    }

    @ShellMethod(value = "Get count authors", key = {"counta"})
    public String countAuthors(){
        System.out.println("All authors count " + authorRepository.count());
        return "counta";
    }

    @ShellMethod(value = "Add author (full_name)", key = {"adda"})
    public String addAuthor( @ShellOption String fullName ) {
        authorRepository.save(new Author(fullName));
        return "added " + fullName;
    }

    @ShellMethod(value = "Get authors", key = {"authors"})
    public String getAuthors(){
        System.out.println( authorRepository.findAll());
        return "authors";
    }

    @ShellMethod(value = "Get author (id)", key = {"author"})
    public String getAuthor(@ShellOption String id ) {
        Optional<Author> author = authorRepository.findById(id);
        System.out.println("Author: " + author.get().getFullName());
        return "author " + id;
    }


    @ShellMethod(value = "Delete author (id)", key = {"dela"})
    public String delAuthor(@ShellOption String id) {
        authorRepository.deleteById(id);
        return "deleted " + id;
    }

    @ShellMethod(value = "Get genres", key = {"genres"})
    public String getGenres(){
        System.out.println(genreRepository.findAll());
        return "genres";
    }

    @ShellMethod(value = "Add genre (name)", key = {"addg"})
    public String addGenre(@ShellOption String name ) {
        genreRepository.save(new Genre(name));
        return "added " + name;
    }

    @ShellMethod(value = "Get genre (id)", key = {"genre"})
    public String getGenre(@ShellOption String id ) {
        Optional<Genre> genre = genreRepository.findById(id);
        System.out.println("Genre: " + genre.get().getGName());
        return "genre " + id;
    }

    @ShellMethod(value = "Delete genre (id)", key = {"delg"})
    public String delGenre(@ShellOption String id) {
        genreRepository.deleteById(id);
        return "deleted " + id;
    }

    @ShellMethod(value = "Get count genres", key = {"countg"})
    public String countGenres(){
        System.out.println("All genres count " + genreRepository.count());
        return "countg";
    }

    @ShellMethod(value = "Get books", key = {"books"})
    public String getBooks(){
        System.out.println(bookRepository.findAll());
        return "books";
    }

    @ShellMethod(value = "Get count books", key = {"countb"})
    public String countBooks(){
        System.out.println("All books count " + bookRepository.count());
        return "countb";
    }

    @ShellMethod(value = "Delete book (id)", key = {"delb"})
    public String delBook(@ShellOption String id) {
        bookRepository.deleteById(id);
        return "deleted " + id;
    }

    @ShellMethod(value = "Add book (name, author_id, genre_id )", key = {"addb"})
    public String addBook(@ShellOption String name, @ShellOption String authorId, @ShellOption String genreId ) {

        Book book = new Book(name);

        Set<Genre> genre = new HashSet();
        genre.add(genreRepository.findById(genreId).get());
        Set<Author> author = new HashSet();
        author.add(authorRepository.findById(authorId).get());
        book.setGenre(genre);
        book.setAuthor(author);

        bookRepository.save(book);
        return "added " + name;
    }

    @ShellMethod(value = "Get book (id)", key = {"book"})
    public String getBook(@ShellOption String id) {
        Book book = bookRepository.findById(id).get();

        Genre genre = book.getGenre().stream().findFirst().orElse(null);
        Author author = book.getAuthor().stream().findFirst().orElse(null);

        System.out.println("Book: " + book.getName() + " Author: " + author.getFullName() + " Genre: " + genre.getGName() );
        return "book " + id;
    }


}
