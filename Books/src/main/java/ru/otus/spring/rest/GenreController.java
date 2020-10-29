package ru.otus.spring.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repostory.GenreRepository;

import java.util.List;

@Controller
public class GenreController {
    private GenreRepository genreRepository;

    public GenreController (GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping( "/genres")
    public String genres(Model model) {
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        return "genres";
    }

    @PostMapping( "/genres/delete")
    public String deleteGenre(@RequestParam("id") long id, Model model) {
        genreRepository.deleteById(id);
        return "redirect:";
    }

    @GetMapping("/genres/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Genre genre = genreRepository.findById(id);
        model.addAttribute("genre", genre);
        return "editGenre";
    }

    @GetMapping( "/genres/add")
    public String addAuthor(Model model) {
        List<Genre> genres = genreRepository.findAll();
        Genre genre = new Genre();
        model.addAttribute("genre", genre);
        return "editGenre";
    }


    @PostMapping("/genres/edit")
    public String addGenre(
            Genre genre,
            Model model
    ) {
        Genre saved = genreRepository.save(genre);
        model.addAttribute(saved);
        return "redirect:";
    }
}
