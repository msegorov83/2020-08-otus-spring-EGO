package ru.otus.spring.repository;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;

import java.util.List;

public interface AuthorRepositoryJpa {
    Author save(Author author);
    Author findById(long id);
    List<Author> findAll();
    void deleteById(long id);
    Long count();
    Author findByName(String name);

}
