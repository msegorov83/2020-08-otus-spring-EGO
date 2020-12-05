package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookAuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.service.BookService;

@Configuration
public class ServiceConfig {

    @Bean
    public BookService bookService(BookRepository bookRepository, AuthorRepository authorRepository, BookAuthorRepository bookAuthorRepository) {
        return new BookService(bookRepository, authorRepository, bookAuthorRepository);
    }
}
