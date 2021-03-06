package ru.otus.spring.page;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repostory.AuthorRepository;
import ru.otus.spring.repostory.BookRepository;
import ru.otus.spring.repostory.GenreRepository;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Controller
@Log4j2
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
    public String books(Model model) { //, Principal principal
        //OAuth2Authentication authentication = (OAuth2Authentication) principal;
        ///Map<String, Object> user = (Map<String, Object>) authentication.getUserAuthentication().getDetails();
        //System.out.println("-------------------------------- user " + user);
     //   model.addAttribute("user", user);

        return "books";
    }

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

    @PostMapping("/edit")
    public String addBook(
            Book book,
            Model model
    ) {
        Book saved = bookRepository.save(book);
        model.addAttribute(saved);
        return "redirect:";
    }

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
