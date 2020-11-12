package ru.otus.spring.repository;

import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorJdbcImpl implements AuthorJdbc {

    private final JdbcOperations op;

    @Override
    public Author findById(long id) {
        List<Author> authors = op.query("select a.id, a.full_name from authors a where a.id = "+ id, new AuthorRowMapper());
        return  authors.stream().findFirst().get();
    }

    @Override
    public  List<Author> findAll() {
        return op.query("select a.id, a.full_name from authors a", new AuthorRowMapper());
    }

    @Override
    public void save(Author author) {
        op.update("insert into authors (full_name) values (?);", author.getFullName());
    }

    @Override
    public void deleteById(long id) {
        op.update("delete from authors a where a.id = ?", id);
    }

    @Override
    public int count() {
        return op.query("select count(a.id) from authors a", new AuthorCountRowMapper()).stream().findFirst().get();
    }

    private class AuthorRowMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int i) throws SQLException {
            var author =  new Author(rs.getString(2));
            author.setId(rs.getLong(1));
            return  author;
        }
    }

    private class AuthorCountRowMapper implements RowMapper<Integer> {
        @Override
        public Integer mapRow(ResultSet rs, int i) throws SQLException {
            return rs.getInt(1) ;
        }
    }
}
