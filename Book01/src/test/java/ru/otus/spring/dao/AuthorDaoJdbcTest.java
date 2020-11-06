package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тесты для DAO Author")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {


    @Autowired
    private  AuthorDaoJdbc authorDaoJdbc;

    @Test
    void testCount() {
        final int expectedCount = 1;
        int actualCount =  authorDaoJdbc.count();
        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @Test
    void testInsert() {
        Author expecteAuthor = new Author(2, "Егоров Михаил");
        authorDaoJdbc.insert(expecteAuthor);
        Author actualAuthor = authorDaoJdbc.getById(2);
        assertThat(actualAuthor).isEqualTo(expecteAuthor);
    }

    @Test
    void testGetById() {
        Author expecteAuthor = new Author(1, "Питер Гуральник");
        Author actualAuthor = authorDaoJdbc.getById(1);
        assertThat(actualAuthor).isEqualTo(expecteAuthor);
    }

    @Test
    void testDeleteById() {
        final int expectedCount = 0;
        authorDaoJdbc.deleteById(1);
        int actualCounnt =  authorDaoJdbc.count();
        assertThat(actualCounnt).isEqualTo(expectedCount);
    }

}