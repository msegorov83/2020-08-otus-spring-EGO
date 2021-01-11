package ru.otus.spring.page;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repostory.AuthorRepository;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Controller
public class AuthorPageController {
    @Autowired
    private final AuthorRepository authorRepository;

    public AuthorPageController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/authors")
    public String authors(Model model) {
        return "authors";
    }

    @GetMapping("/authors/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Author author = authorRepository.findById(id);
        model.addAttribute("author", author);
        return "editAuthor";
    }

    @GetMapping("/authors/add")
    public String addAuthor(Model model) {
        List<Author> authors = authorRepository.findAll();
        Author author = new Author();
        model.addAttribute("author", author);
        return "editAuthor";
    }

    @PostMapping("/authors/edit")
    public String addAuthor(
            Author author,
            Model model
    ) {
        Author saved = authorRepository.save(author);
        model.addAttribute(saved);
        return "redirect:/authors";
    }

}
