package ru.otus.spring.repostory;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    Author findById(long id);

    List<Author> findAll();

}
