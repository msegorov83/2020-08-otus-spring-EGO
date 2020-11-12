package ru.otus.spring.repository;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreRepositoryJpa {
    Genre findById(long id);
    List<Genre> findAll();
    Long count();
    void deleteById(long id);
    Genre save(Genre genre);
}
