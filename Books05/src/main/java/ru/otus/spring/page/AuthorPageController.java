package ru.otus.spring.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repository.AuthorRepository;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Controller
public class AuthorPageController {
    @Autowired
    ReactiveMongoTemplate template;

    private final AuthorRepository authorRepository;

    public AuthorPageController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping( "/authors")
    public String authors(Model model) {
        return "authors";
    }

    @GetMapping("/authors/edit")
    public String editPage(@RequestParam("id") String id, Model model) {
        Author currAuthor = new Author();
        authorRepository.findById(id).subscribe(author -> {
            currAuthor.setId(author.getId());
            currAuthor.setFullName(author.getFullName());
        });

        model.addAttribute ("author",currAuthor);
        return "editAuthor";
    }

    @PostMapping("/authors/edit")
    public String addAuthor(
            Author author,
            Model model
    ) {
        //var saved=authorRepository.save(author).subscribe();
        template.save(authorRepository.save(author).subscribe());
        //model.addAttribute(saved);
        return "redirect:/authors" ;
    }

    @GetMapping( "/authors/add")
    public String addAuthor(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);
        return "editAuthor";
    }


}
