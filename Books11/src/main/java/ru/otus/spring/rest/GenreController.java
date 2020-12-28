package ru.otus.spring.rest;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.repostory.GenreRepository;
import ru.otus.spring.rest.dto.GenreDto;

@RestController
public class GenreController {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping("/api/genres")
    public List<GenreDto> getAllBooks() {
        return genreRepository.findAll().stream().map(GenreDto::toDo).collect(Collectors.toList());
    }

    @PostMapping("/api/genres/delete")
    public void deleteGenre(@RequestParam("id") long id, Model model) {
        genreRepository.deleteById(id);
    }

}
