package ru.otus.spring.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.GenreRepository;

@Controller
public class GenrePageController {

    private GenreRepository genreRepository;

    public GenrePageController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping( "/genres")
    public String genres(Model model) {
        return "genres";
    }

    @GetMapping("/genres/edit")
    public String editPage(@RequestParam("id") String id, Model model) {

        Genre currGenre = new Genre();
        genreRepository.findById(id).subscribe(genre -> {
            currGenre.setId(genre.getId());
            currGenre.setName(genre.getName());
        });

        model.addAttribute("genre", currGenre);
        return "editGenre";
    }

    @GetMapping( "/genres/add")
    public String addAuthor(Model model) {
        Genre genre = new Genre();
        model.addAttribute("genre", genre);
        return "editGenre";
    }

    @PostMapping("/genres/edit")
    public String addGenre(
            Genre genre,
            Model model
    ) {
        var saved = genreRepository.save(genre).subscribe();
        model.addAttribute(saved);
        return "redirect:/genres";
    }
}
