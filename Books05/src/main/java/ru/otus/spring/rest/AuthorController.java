package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repository.AuthorRepository;


@RestController
public class AuthorController {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) { this.authorRepository =  authorRepository; }

    @GetMapping("/api/authors")
    public Flux<Author> getAllBooks () {
        return authorRepository.findAll();
    }

    @RequestMapping("/api/authors/delete")
    public void deleteAuthor(@RequestParam("id") String id, Model model) {
        authorRepository.deleteById(id).subscribe();
    }

}
