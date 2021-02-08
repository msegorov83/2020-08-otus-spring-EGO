package ru.otus.spring.rest;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.repostory.AuthorRepository;
import ru.otus.spring.rest.dto.AuthorDto;

@RestController
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository =  authorRepository;
    }

    @RequestMapping("/api/authors")
    public List<AuthorDto> getAllBooks() {
        return authorRepository.findAll().stream().map(AuthorDto::toDo).collect(Collectors.toList());
    }

    @RequestMapping("/api/authors/delete")
    public void deleteAuthor(@RequestParam("id") String id, Model model) {
        authorRepository.deleteById(Long.valueOf(id));
    }


}
