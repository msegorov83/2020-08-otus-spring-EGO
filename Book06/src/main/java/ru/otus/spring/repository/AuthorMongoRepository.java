package ru.otus.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.AuthorMongo;

public interface AuthorMongoRepository extends MongoRepository<AuthorMongo, String> {
    AuthorMongo save(AuthorMongo authorMongo);
}
