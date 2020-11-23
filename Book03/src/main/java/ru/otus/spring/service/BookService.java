package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repository.AuthorRepositoryJpaImpl;
import ru.otus.spring.repository.BookAuthorRepositoryJpaImpl;
import ru.otus.spring.repository.BookRepositoryJpaImpl;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    private BookRepositoryJpaImpl bookRepositoryJpa;
    private AuthorRepositoryJpaImpl authorRepositoryJpa;
    private BookAuthorRepositoryJpaImpl bookAuthorRepositoryJpa;

    public BookService (BookAuthorRepositoryJpaImpl bookAuthorRepositoryJpa, BookRepositoryJpaImpl bookRepositoryJpa, AuthorRepositoryJpaImpl authorRepositoryJpa ) {
        this.bookRepositoryJpa = bookRepositoryJpa;
        this.authorRepositoryJpa = authorRepositoryJpa;
        this.bookAuthorRepositoryJpa = bookAuthorRepositoryJpa;
    }

    public List<Book> findAllBookByAuthor(String name){
        Author author = authorRepositoryJpa.findByName(name);
        var bookAuthorList= bookAuthorRepositoryJpa.findByAuthorId(author);

        List<Long> books = new ArrayList<>();
        bookAuthorList.stream().filter(bookAuthor -> books.add(bookAuthor.getBook().getId()) ).toArray();

        return bookRepositoryJpa.findByIds(books);
    }
}

