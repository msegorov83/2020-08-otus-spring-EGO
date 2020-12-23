package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.GenreRepository;

@RestController
public class GenreController {
    private GenreRepository genreRepository;

    @Autowired
    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping("/api/genres")
    public Flux<Genre> getAllGenres(Model model) {
        return genreRepository.findAll();
    }

    @PostMapping( "/api/genres/delete")
    public void deleteGenre(@RequestParam("id") String id, Model model) {
        genreRepository.deleteById(id).subscribe();
    }

}
