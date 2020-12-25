package ru.otus.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.AuthorMongo;
import ru.otus.spring.domain.BookAuthorMongo;

public interface BookAuthorMongoRepository extends MongoRepository<BookAuthorMongo, String> {
    String findIdByBookIdAndAuthorId(String bookId, String authorId);
}
