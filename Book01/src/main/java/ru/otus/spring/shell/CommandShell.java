package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.*;

/*Commands for example
authors
adda 2 "Егоров Михаил"
author 2
counta
dela 2

genres
addg 3 "Фантастика"
genre 3
countg
delg 3

books
book 1
addb 2 "Careless Love: The Unmaking of Elvis Presley" 1 1
countb
delb 2
 */

@ShellComponent
public class CommandShell {

    private AuthorDao authorDao;
    private GenreDao genreDao;
    private BookDao bookDao;

    public CommandShell(AuthorDao authorDao, GenreDao genreDao, BookDao bookDao) {
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.bookDao = bookDao;
    }

    @ShellMethod(value = "Get authors", key = {"authors"})
    public String getAuthors(){
        System.out.println(authorDao.getAll());
        return "authors";
    }

    @ShellMethod(value = "Add author (id, full_name)", key = {"adda"})
    public String addAuthor(@ShellOption long id, @ShellOption String fullName ) {
        authorDao.insert(new Author(id, fullName));
        return "added " + fullName;
    }

    @ShellMethod(value = "Get author (id)", key = {"author"})
    public String getAuthor(@ShellOption long id ) {
        Author author = authorDao.getById(id);
        System.out.println("Author: " + author.getFullName());
        return "author " + id;
    }

    @ShellMethod(value = "Get count authors", key = {"counta"})
    public String countAuthors(){
        System.out.println("All authors count " + authorDao.count());
        return "counta";
    }

    @ShellMethod(value = "Delete author (id)", key = {"dela"})
    public String delAuthor(@ShellOption long id) {
        authorDao.deleteById(id);
        return "deleted " + id;
    }

    @ShellMethod(value = "Get genres", key = {"genres"})
    public String getGenres(){
        System.out.println(genreDao.getAll());
        return "genres";
    }

    @ShellMethod(value = "Add genre (id, name)", key = {"addg"})
    public String addGenre(@ShellOption long id, @ShellOption String name ) {
        genreDao.insert(new Genre(id, name));
        return "added " + name;
    }

    @ShellMethod(value = "Get genre (id)", key = {"genre"})
    public String getGenre(@ShellOption long id ) {
        Genre genre = genreDao.getById(id);
        System.out.println("Genre: " + genre.getGName());
        return "genre " + id;
    }

    @ShellMethod(value = "Delete genre (id)", key = {"delg"})
    public String delGenre(@ShellOption long id) {
        genreDao.deleteById(id);
        return "deleted " + id;
    }

    @ShellMethod(value = "Get count genres", key = {"countg"})
    public String countGenres(){
        System.out.println("All genres count " + genreDao.count());
        return "countg";
    }

    @ShellMethod(value = "Get books", key = {"books"})
    public String getBooks(){
        System.out.println(bookDao.getAll());
        return "books";
    }

    @ShellMethod(value = "Get book (id)", key = {"book"})
    public String getBook(@ShellOption long id) {
        Book book = bookDao.getById(id);

        AuthorRef authorRef = book.getAuthors().stream().findFirst().orElse(null);
        Author author = authorDao.getById(authorRef.getAuthorId());

        GenreRef genreRef = book.getGenres().stream().findFirst().orElse(null);
        Genre genre = genreDao.getById(genreRef.getGenreId());

        System.out.println("Book: " + book.getName() + " Author: " + author.getFullName() + " Genre: " + genre.getGName() );
        return "book " + id;
    }

    @ShellMethod(value = "Get count books", key = {"countb"})
    public String countBooks(){
        System.out.println("All books count " + bookDao.count());
        return "countb";
    }

    @ShellMethod(value = "Add book (id, name, author_id, genre_id )", key = {"addb"})
    public String addBook(@ShellOption long id, @ShellOption String name, @ShellOption long authorId, @ShellOption long genreId ) {
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.addAuthor(authorDao.getById(authorId));
        book.addGenres(genreDao.getById(genreId));
        bookDao.insert(book);
        return "added " + name;
    }

    @ShellMethod(value = "Delete book (id)", key = {"delb"})
    public String delBook(@ShellOption long id) {
        bookDao.deleteById(id);
        return "deleted " + id;
    }

}
