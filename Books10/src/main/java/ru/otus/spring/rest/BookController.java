package ru.otus.spring.rest;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.repostory.BookRepository;
import ru.otus.spring.rest.dto.BookDto;

@RestController
public class BookController {
    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/api/books")
    public List<BookDto> getAllBooks () {
        return bookRepository.findAll().stream().map(BookDto::toDo).collect(Collectors.toList());
    }

    @PostMapping( "/api/delete")
    public void deleteBook(@RequestParam("id") long id, Model model) {
        bookRepository.deleteById(id);
    }
}
