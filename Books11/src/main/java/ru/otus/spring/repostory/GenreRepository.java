package ru.otus.spring.repostory;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.otus.spring.domain.Genre;

public interface GenreRepository extends PagingAndSortingRepository<Genre, Long> {

    Genre findById(long id);

    List<Genre> findAll();

}
