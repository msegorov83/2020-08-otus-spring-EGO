package ru.otus.spring.repostory;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.otus.spring.domain.Author;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    List<Author> findAll();

    Author findById(long id);

}
