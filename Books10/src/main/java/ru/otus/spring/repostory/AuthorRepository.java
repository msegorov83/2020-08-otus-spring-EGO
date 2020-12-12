package ru.otus.spring.repostory;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.otus.spring.domain.Author;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    Author findById(long id);

    List<Author> findAll();

}
