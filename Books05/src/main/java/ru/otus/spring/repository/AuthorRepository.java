package ru.otus.spring.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {


    Flux<Author> findAll();

    Mono<Author> findById(String id);

    Mono<Author> save(Mono<Author> authorMono);

    Mono<Author> findByFullName(String fullName);


}
