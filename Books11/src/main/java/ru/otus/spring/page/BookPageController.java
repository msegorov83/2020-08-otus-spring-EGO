package ru.otus.spring.page;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repostory.AuthorRepository;
import ru.otus.spring.repostory.BookRepository;
import ru.otus.spring.repostory.GenreRepository;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Controller
public class BookPageController {

    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final GenreRepository genreRepository;
    @Autowired
    private final AuthorRepository authorRepository;

    @Autowired
    public BookPageController(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping("/")
    public String books(Model model) {
        return "books";
    }

  //  @Secured("ADMIN")
    @GetMapping("/add")
    public String addBook(Model model) {
        List<Genre> genres = genreRepository.findAll();
        List<Author> authors = authorRepository.findAll();
        Book book = new Book();

        model.addAttribute("messageEdit", "Add");
        model.addAttribute("book", book);
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);

        return "editBook";
    }

   // @Secured("ADMIN")
    @PostMapping("/edit")
    public String addBook(
            Book book,
            Model model
    ) {
        Book saved = bookRepository.save(book);
        model.addAttribute(saved);
        return "redirect:";
    }

   // @Secured("ADMIN")
    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long  id, Model model) {

        Book book = bookRepository.findById(id);
        List<Genre> genres = genreRepository.findAll();
        List<Author> authors = authorRepository.findAll();

        model.addAttribute("book", book);
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);

        return "editBook";
    }

}
