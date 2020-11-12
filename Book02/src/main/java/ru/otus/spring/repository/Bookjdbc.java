package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.domain.Book;

import java.util.List;

public interface Bookjdbc {

    List<Book> findAll();
    Book findByIdAllinfo(long id);
    List<Book> findAllBookByAuthor(String name);
    void save(Book book);
    void deleteById(long id);
    int count();
}
