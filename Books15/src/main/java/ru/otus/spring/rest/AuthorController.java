package ru.otus.spring.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.repostory.AuthorRepository;
import ru.otus.spring.rest.dto.AuthorDto;
import ru.otus.spring.services.SleepService;

@RestController
public class AuthorController {

    private final AuthorRepository authorRepository;

    @Autowired
    private SleepService sleepService;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository =  authorRepository;
    }

    @HystrixCommand(commandKey = "getAuthorsKey", fallbackMethod = "buildFallbackAuthors")
    @GetMapping("/api/authors")
    public List<AuthorDto> getAllBooks() {
        sleepService.sleepRandom();
        return authorRepository.findAll().stream().map(AuthorDto::toDo).collect(Collectors.toList());
    }

    public List<AuthorDto> buildFallbackAuthors() {
        AuthorDto author = new AuthorDto(0L, "N/A");
        List<AuthorDto> authors = new ArrayList<>();
        authors.add(author);
        return authors;
    }

    @RequestMapping("/api/authors/delete")
    public void deleteAuthor(@RequestParam("id") String id, Model model) {
        authorRepository.deleteById(Long.valueOf(id));
    }


}
