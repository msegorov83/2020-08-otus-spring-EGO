package ru.otus.spring.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {

    Flux<Genre> findAll();

    Mono<Genre> findById(String id);

    Mono<Genre> save(Mono<Genre> genreMono);

    Mono<Genre> findByName(String name);
}
