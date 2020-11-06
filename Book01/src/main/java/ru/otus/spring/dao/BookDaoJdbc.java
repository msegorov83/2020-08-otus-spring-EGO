package ru.otus.spring.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class BookDaoJdbc implements BookDao {

    private final AuthorDaoJdbc authorDaoJdbc;
    private final GenreDaoJdbc genreDaoJdbc;
    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbc(JdbcOperations jdbc, NamedParameterJdbcOperations namedParameterJdbcOperations, AuthorDaoJdbc authorDaoJdb, GenreDaoJdbc genreDaoJdbc) {
        this.jdbc = jdbc;
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.authorDaoJdbc = authorDaoJdb;
        this.genreDaoJdbc = genreDaoJdbc;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from books", Integer.class);
    }

    @Override
    public void insert(Book book) {
        jdbc.update("insert into books (id, `name`) values (?, ?)", book.getId(), book.getName());
        AuthorRef author = book.getAuthors().stream().findFirst().orElse(null);
        GenreRef genre = book.getGenres().stream().findFirst().orElse(null);

        jdbc.update("insert into books_authors (book_id, author_id) values (?, ?)", book.getId(), author.getAuthorId());
        jdbc.update("insert into books_genres (book_id, genre_id) values (?, ?)", book.getId(), genre.getGenreId());
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select * from books " +
                        "inner join books_authors on books.id = books_authors.book_id " +
                        "inner join books_genres on books.id = books_genres.book_id " +
                        " where books.id = :id", params, new BookDaoJdbc.BoookMapper(authorDaoJdbc, genreDaoJdbc)
        );
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select * from books " +
                "inner join books_authors on books.id = books_authors.book_id " +
                        "inner join books_genres on books.id = books_genres.book_id ",
                        new BookDaoJdbc.BoookMapper(authorDaoJdbc, genreDaoJdbc)
        );
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update("delete from books where id=:id", params);
        namedParameterJdbcOperations.update("delete from books_authors where book_id=:id", params);
        namedParameterJdbcOperations.update("delete from books_genres where book_id=:id", params);

    }


    private static class BoookMapper implements RowMapper<Book> {
        private AuthorDaoJdbc authorDaoJdbc;
        private GenreDaoJdbc genreDaoJdbc;

        public BoookMapper (AuthorDaoJdbc authorDaoJdbc, GenreDaoJdbc genreDaoJdbc) {
            this.authorDaoJdbc = authorDaoJdbc;
            this.genreDaoJdbc = genreDaoJdbc;
        }

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            long authorId = resultSet.getLong("author_id");
            long genresId = resultSet.getLong("genre_id");
            Book book = new Book();
            book.setId(id);
            book.setName(name);
            book.addAuthor(authorDaoJdbc.getById(authorId));
            book.addGenres(genreDaoJdbc.getById(genresId));
            return book;
        }
    }


}
