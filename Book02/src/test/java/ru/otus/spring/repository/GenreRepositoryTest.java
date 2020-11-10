package ru.otus.spring.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.domain.Genre;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты для GenreRepository")
@SpringBootTest
@Transactional
class GenreRepositoryTest {

    private static final long GENRE_ID = 2L;
    private static final String GENRE_NAME = "Фантастика";

    @Autowired
    private GenreRepository genreRepository;

    @BeforeEach
    void setUp() {
        genreRepository.save(new Genre(GENRE_NAME));
    }

    @Test
    void findById() {
        var actualGenre = genreRepository.findById(GENRE_ID);
        assertThat(actualGenre).isNotNull();
        assertThat(actualGenre.getgName()).isEqualTo(GENRE_NAME);
    }
}