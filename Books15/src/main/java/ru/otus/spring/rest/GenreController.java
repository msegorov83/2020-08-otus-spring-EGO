package ru.otus.spring.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.repostory.GenreRepository;
import ru.otus.spring.rest.dto.GenreDto;
import ru.otus.spring.services.SleepService;

@RestController
public class GenreController {

    private GenreRepository genreRepository;

    @Autowired
    private SleepService sleepService;

    @Autowired
    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @HystrixCommand( commandKey = "getGenresKey", fallbackMethod = "buildFallbackGenres")
    @GetMapping("/api/genres")
    public List<GenreDto> getAllGenres() {
        sleepService.sleepRandom();
        return genreRepository.findAll().stream().map(GenreDto::toDo).collect(Collectors.toList());
    }

    public List<GenreDto> buildFallbackGenres() {
        GenreDto genre = new GenreDto(0L, "N/A");
        List<GenreDto> genres = new ArrayList<>();
        genres.add(genre);
        return genres;
    }


    @PostMapping("/api/genres/delete")
    public void deleteGenre(@RequestParam("id") long id, Model model) {
        genreRepository.deleteById(id);
         }
}
