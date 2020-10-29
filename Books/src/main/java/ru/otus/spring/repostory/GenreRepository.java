package ru.otus.spring.repostory;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreRepository extends PagingAndSortingRepository<Genre, Long> {
    Genre save(Genre genre);
    Genre findById(long id);
    List<Genre> findAll();
    void deleteById(long id);

}
