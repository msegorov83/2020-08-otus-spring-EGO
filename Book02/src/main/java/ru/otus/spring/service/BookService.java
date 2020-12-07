package ru.otus.spring.service;

import java.util.ArrayList;
import java.util.List;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookAuthorRepository;
import ru.otus.spring.repository.BookRepository;


public class BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private BookAuthorRepository bookAuthorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, BookAuthorRepository bookAuthorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookAuthorRepository = bookAuthorRepository;
    }


    public List<Book> findAllBookByAuthor(String name) {

        Author author = authorRepository.findByFullName(name);
        var bookAuthorList = bookAuthorRepository.findByAuthor(author);
        List<Long> books = new ArrayList<>();
        bookAuthorList.stream().filter(bookAuthor -> books.add(bookAuthor.getBook().getId())).toArray();

        return bookRepository.findAllById(books);
    }

}
