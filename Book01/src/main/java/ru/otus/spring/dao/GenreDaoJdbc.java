package ru.otus.spring.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class GenreDaoJdbc implements GenreDao{
    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreDaoJdbc(JdbcOperations jdbcOperations, NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = jdbcOperations;
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from genres", Integer.class);
    }

    @Override
    public void insert(Genre genre) {

        Map<String, Object> params = new HashMap<>();
        params.put("id", genre.getId());
        params.put("name", genre.getGName());
        namedParameterJdbcOperations.update("insert into genres (id, `name`) values (:id, :name)", params);
    }

    @Override
    public Genre getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select * from genres where id = :id", params, new GenreMapper()
        );
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select * from genres", new GenreMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update("delete from genres where id=:id", params);
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
