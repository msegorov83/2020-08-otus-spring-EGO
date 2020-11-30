package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

     @Query("SELECT b FROM Book b join fetch b.genre g  WHERE b.id = :id")
     Book findByIdAllInfo(@Param("id") long id);

}
