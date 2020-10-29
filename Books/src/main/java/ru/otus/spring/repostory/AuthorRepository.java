package ru.otus.spring.repostory;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.otus.spring.domain.Author;


import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {
    List<Author> findAll();
    Author findById(long id);
}
