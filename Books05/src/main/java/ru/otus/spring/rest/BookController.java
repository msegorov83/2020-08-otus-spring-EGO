package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repository.BookRepository;


@RestController
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController (BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/api/books")
    public Flux<Book> getAllBooks () {
        return bookRepository.findAll();
    }

    @PostMapping( "/api/delete")
    public void deleteBook(@RequestParam("id") String id, Model model) {
        bookRepository.deleteById(id).subscribe();
    }

}
