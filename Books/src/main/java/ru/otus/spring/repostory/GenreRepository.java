package ru.otus.spring.repostory;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreRepository extends PagingAndSortingRepository<Genre, Long> {
    List<Genre> findAll();
    Genre findById(long id);
}
