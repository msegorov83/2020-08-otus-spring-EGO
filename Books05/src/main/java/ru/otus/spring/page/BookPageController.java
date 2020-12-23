package ru.otus.spring.page;

import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.GenreRepository;

import java.util.*;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Controller
public class BookPageController {

    private final BookRepository bookRepository;

    private final GenreRepository genreRepository;

    private final AuthorRepository authorRepository;

    public BookPageController(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping( "/")
    public String books(Model model) {
        return "books";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") String  id, Model model) {

        Book curBook = new Book();

        bookRepository.findById(id).subscribe(book -> {
            curBook.setId(book.getId());
            curBook.setName(book.getName());
            curBook.setGenre(book.getGenre());
            curBook.setAuthor(book.getAuthor());
        });

        List<Author>listAuthor = new ArrayList<>();
        List<Genre> listGenre = new ArrayList<>();

        authorRepository.findAll().subscribe(authors -> {
            listAuthor.add(authors) ;
        });

        genreRepository.findAll().subscribe(genres -> {
            listGenre.add(genres) ;
        });

        model.addAttribute("book", curBook);
        model.addAttribute("genres", listGenre);
        model.addAttribute("authors", listAuthor);

        return "editBook";
    }

    @GetMapping( "/add")
    public String addBook(Model model) {

        List<Author>listAuthor = new ArrayList<>();
        List<Genre> listGenre = new ArrayList<>();

        authorRepository.findAll().subscribe(authors -> {
           listAuthor.add(authors) ;
        });

        genreRepository.findAll().subscribe(genres -> {
            listGenre.add(genres) ;
        });

        Book book = new Book();

        model.addAttribute("messageEdit", "Add");
        model.addAttribute("book", book);
        model.addAttribute("genres", listGenre);
        model.addAttribute("authors", listAuthor);

        return "editBook";
    }

    @PostMapping("/edit")
    public String addBook(
            @ModelAttribute Book book,
            @ModelAttribute("author") String authorId,
            @ModelAttribute("genre") String genreId,
            Model model
    ) {
        new MongoTemplate(new SimpleMongoClientDatabaseFactory(MongoClients.create(), "book"));

        Genre curGenre = new Genre();
        genreRepository.findById(genreId).subscribe(genre -> { curGenre.setId(genre.getId()); curGenre.setName(genre.getName()); });

        Author curAuthor = new Author();
        authorRepository.findById(authorId).subscribe(author -> { curAuthor.setId(author.getId()); curAuthor.setFullName(author.getFullName()); });

        Book buffBook = new Book(book.getName());
        buffBook.setId(book.getId());
        book.setAuthor(Collections.singletonList(curAuthor));
        book.setGenre(Collections.singletonList(curGenre));

        var saved = bookRepository.save(book).subscribe();
        model.addAttribute(saved);

        return "redirect:";
     }
}
