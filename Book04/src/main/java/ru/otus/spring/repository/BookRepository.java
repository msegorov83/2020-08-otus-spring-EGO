package ru.otus.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.domain.Book;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, String> {
    Optional<Book> findByName(String name);
}
