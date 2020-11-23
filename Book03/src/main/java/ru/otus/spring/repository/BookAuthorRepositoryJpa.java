package ru.otus.spring.repository;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.BookAuthor;

import java.util.List;

public interface BookAuthorRepositoryJpa {
    BookAuthor save (BookAuthor bookAuthor);
    List<BookAuthor> findByAuthorId (Author author);
    void deleteByBookId(long bookId);
}
