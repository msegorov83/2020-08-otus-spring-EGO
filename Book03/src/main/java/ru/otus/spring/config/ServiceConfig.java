package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repository.AuthorRepositoryJpaImpl;
import ru.otus.spring.repository.BookAuthorRepositoryJpaImpl;
import ru.otus.spring.repository.BookRepositoryJpaImpl;
import ru.otus.spring.service.BookService;

@Configuration
public class ServiceConfig {

    @Bean
    public BookService bookService(BookAuthorRepositoryJpaImpl bookAuthorRepositoryJpa, BookRepositoryJpaImpl bookRepositoryJpa, AuthorRepositoryJpaImpl authorRepositoryJpa) {
        return new BookService(bookAuthorRepositoryJpa,bookRepositoryJpa, authorRepositoryJpa);
    }

}
