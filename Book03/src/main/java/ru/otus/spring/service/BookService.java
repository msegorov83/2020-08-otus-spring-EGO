package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repository.AuthorRepositoryJpaImpl;
import ru.otus.spring.repository.BookRepositoryJpaImpl;
import java.util.List;

public class BookService {
    private BookRepositoryJpaImpl bookRepositoryJpa;
    private AuthorRepositoryJpaImpl authorRepositoryJpa;

    public BookService (BookRepositoryJpaImpl bookRepositoryJpa, AuthorRepositoryJpaImpl authorRepositoryJpa ) {
        this.bookRepositoryJpa = bookRepositoryJpa;
        this.authorRepositoryJpa = authorRepositoryJpa;
    }

    public List<Book> findAllBookByAuthor(String name){
        Author author = authorRepositoryJpa.findByName(name);
        return  bookRepositoryJpa.finbByAuthor(author);


    }
}

