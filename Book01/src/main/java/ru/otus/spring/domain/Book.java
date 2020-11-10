package ru.otus.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Data
public class Book {
    private long id;
    private String name;

    Set<AuthorRef> authors = new HashSet<>();
    Set<GenreRef> genres = new HashSet<>();

    public void addGenres(Genre genre) {
        genres.add(createGenreRef(genre));
    }

    private GenreRef createGenreRef(Genre genre) {

        Assert.notNull(genre, "Author must not be null");
        Assert.notNull(genre.getId(), "Author id, must not be null");
        GenreRef genreRef = new GenreRef(genre.getId());
        return genreRef;
    }

    public void addAuthor(Author author) {
        authors.add(createAuthorRef(author));
    }

    private AuthorRef createAuthorRef(Author author) {

        Assert.notNull(author, "Author must not be null");
        Assert.notNull(author.getId(), "Author id, must not be null");
        AuthorRef authorRef = new AuthorRef(author.getId());
        return authorRef;

    }
}
