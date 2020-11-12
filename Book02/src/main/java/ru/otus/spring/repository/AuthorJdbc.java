package ru.otus.spring.repository;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorJdbc {

    List<Author> findAll();
    Author findById(long id);
    void save(Author author);
    void deleteById(long id);
    int count();

}
