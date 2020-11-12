package ru.otus.spring.repository;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreJdbc {

    List<Genre> findAll();
    Genre findById(long id);
    void save(Genre genre);
    void deleteById(long id);
    int count();
}
