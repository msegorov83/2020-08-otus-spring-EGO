package ru.otus.spring.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.repostory.BookRepository;
import ru.otus.spring.rest.dto.BookDto;
import ru.otus.spring.services.SleepService;

@RestController
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    private SleepService sleepService;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @HystrixCommand(commandKey = "getBooksKey", fallbackMethod = "buildFallbackBooks")
    @GetMapping("/api/books")
    public List<BookDto> getAllBooks() {
        sleepService.sleepRandom();
        return bookRepository.findAll().stream().map(BookDto::toDo).collect(Collectors.toList());
    }

    public List<BookDto> buildFallbackBooks() {

        BookDto book = new BookDto(0L, "N/A", "N/A", "N/A");
        List<BookDto> books = new ArrayList<>();
        books.add(book);

        return books;
    }

    @PostMapping( "/api/delete")
    public void deleteBook(@RequestParam("id") long id, Model model) {
        bookRepository.deleteById(id);
    }
}
