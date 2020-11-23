package ru.otus.spring.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Author save(Author author);
}
