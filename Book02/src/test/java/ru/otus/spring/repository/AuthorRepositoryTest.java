package ru.otus.spring.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.domain.Author;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тесты для AuthorRepository")
@SpringBootTest
@Transactional
class AuthorRepositoryTest {

    private static final long AUTHOR_ID = 2L;
    private static final String AUTHOR_NAME = "Егоров Михаил";
    @Autowired
    private AuthorRepository authorRepository;


    @BeforeEach
    public void setUp() {
        authorRepository.save(new Author(AUTHOR_NAME));
    }

    @Test
    void testFindById() {
        var actualAuthror = authorRepository.findById(AUTHOR_ID);
        assertThat(actualAuthror).isNotNull();
        assertThat(actualAuthror.getFullName()).isEqualTo(AUTHOR_NAME);
    }


}