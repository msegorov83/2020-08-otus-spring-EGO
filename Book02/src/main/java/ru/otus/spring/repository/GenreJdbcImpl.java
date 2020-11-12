package ru.otus.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenreJdbcImpl implements GenreJdbc {

    private final JdbcOperations op;

    @Override
    public List<Genre> findAll() {
        return op.query("select g.id, g.name from genres g", new GenreRowMapper());
    }

    @Override
    public Genre findById(long id) {
        List<Genre> genres = op.query("select g.id, g.name from genres g where g.id = "+ id, new GenreRowMapper());
        return  genres.stream().findFirst().get();
    }

    @Override
    public void save(Genre genre) {
        op.update("insert into genres (name) values (?);", genre.getName());
    }

    @Override
    public void deleteById(long id) {
        op.update("delete from genres g where g.id = ?", id);
    }

    @Override
    public int count() {
        return op.query("select count(g.id) from genres g", new GenreCountRowMapper()).stream().findFirst().get();
    }

    private class GenreRowMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int i) throws SQLException {
            var genre =  new Genre(rs.getString(2));
            genre.setId(rs.getLong(1));
            return  genre;
        }
    }

    private class GenreCountRowMapper implements RowMapper<Integer> {
        @Override
        public Integer mapRow(ResultSet rs, int i) throws SQLException {
            return rs.getInt(1) ;
        }
    }
}
