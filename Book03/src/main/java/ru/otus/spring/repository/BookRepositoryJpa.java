package ru.otus.spring.repository;


import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookRepositoryJpa {
    List<Book> findByIds (List<Long> ids);
    List<Book> findAll();
    void deleteById(long id);
    Book findByIdAllInfo(long id);
    Book save(Book book);
    Long count();

}
