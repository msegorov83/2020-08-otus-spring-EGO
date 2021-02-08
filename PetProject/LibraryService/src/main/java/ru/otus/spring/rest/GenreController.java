package ru.otus.spring.rest;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.repostory.GenreRepository;
import ru.otus.spring.rest.dto.GenreDto;

@RestController
public class GenreController {

    private final GenreRepository genreRepository;

    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @RequestMapping("/api/genres")
    public List<GenreDto> getAllBooks() {
        return genreRepository.findAll().stream().map(GenreDto::toDo).collect(Collectors.toList());
    }

    @RequestMapping("/api/genres/delete")
    public void deleteGenre(@RequestParam("id") long id, Model model) {
        genreRepository.deleteById(id);
    }
}
