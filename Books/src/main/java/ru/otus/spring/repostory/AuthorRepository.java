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
    Author save(Author author);
    Author findById(long id);
    List<Author> findAll();
    void deleteById(long id);

    /*
    Optional<Author> findById(long id);
    List<Author> findByAll();
    List<Author> findByFullName(String fullName);
    void updateNameById(long id, String fullName);
    void deleteById(long id);
    */


}
