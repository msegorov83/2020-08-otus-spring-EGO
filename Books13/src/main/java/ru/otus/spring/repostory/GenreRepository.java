package ru.otus.spring.repostory;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;

@Repository
public interface GenreRepository extends PagingAndSortingRepository<Genre, Long> {

    Genre findById(long id);

    List<Genre> findAll();

}
