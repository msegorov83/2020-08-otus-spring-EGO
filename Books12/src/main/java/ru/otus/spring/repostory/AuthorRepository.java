package ru.otus.spring.repostory;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.otus.spring.domain.Author;

@RepositoryRestResource(path = "author")
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    Author findById(long id);

    List<Author> findAll();

}
