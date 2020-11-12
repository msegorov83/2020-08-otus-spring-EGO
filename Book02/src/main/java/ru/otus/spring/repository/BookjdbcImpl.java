package ru.otus.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


@Repository
@RequiredArgsConstructor
public class BookjdbcImpl implements Bookjdbc {

    @Autowired
    private AuthorJdbc authorJdbc;
    private final JdbcOperations op;

    @Override
    public List<Book> findAll() {
        return op.query("select b.id, b.name from books b", new BookRowMapper());
    }

    @Override
    public Book findByIdAllinfo(long id) {

        var book = op.query("select b.id, b.name, a.id, a.full_name, g.id, g.name " +
                        "FROM books b " +
                        "left join book_author ba on ba.book_id = b.id " +
                        "left join authors a on ba.author_id = a.id " +
                        "left join book_genre bg on bg.book_id = b.id " +
                        "left join genres g on bg.genre_id = g.id " +
                        "where b.id = " + id + " group by b.id",
                new BookResultSetExtractor());

        return book;
    }

    @Override
    public List<Book> findAllBookByAuthor(String name) {
        return op.query("SELECT b.id, b.name FROM books b " +
                "left join book_author ba on ba.book_id = b.id " +
                "left join authors a on ba.author_id = a.id " +
                "WHERE a.full_name = '" +name+"'", new BookRowMapper());
    }

    @Override
    public void save(Book book) {
        op.update("insert into books (name) values (?);", book.getName());
        Book newBook = op.query("select b.id, b.name from books b where  b.name = '"+book.getName()+"'", new BookRowMapper()).stream().findFirst().get();
        op.update("insert into book_author (book_id, author_id) values (?, ?);", newBook.getId(), book.getAuthor().getId());
        op.update("insert into book_genre (book_id, genre_id) values (?, ?);", newBook.getId(),  book.getGenre().getId());
    }

    @Override
    public void deleteById(long id) {
        op.update("delete from books b where b.id = ?", id);
    }

    @Override
    public int count() {
        return op.query("select count(g.id) from genres g", new BookCountRowMapper()).stream().findFirst().get();
    }

    private class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int i) throws SQLException {
            var book =  new Book(rs.getString(2));
            book.setId(rs.getLong(1));
            return  book;
        }
    }

    private class BookCountRowMapper implements RowMapper<Integer> {
        @Override
        public Integer mapRow(ResultSet rs, int i) throws SQLException {
            return rs.getInt(1) ;
        }
    }

    public class BookResultSetExtractor implements ResultSetExtractor<Book> {

        @Override
        public Book extractData(ResultSet rs) throws SQLException,
                DataAccessException {
            rs.first();
            long id = rs.getLong(1);
            Book book= new Book(rs.getString(2));
            book.setId(id);

            Author author = new Author(rs.getString(4));
            author.setId(rs.getLong(3));
            Set<Author> authors = new HashSet();
            authors.add(author);

            Genre genre = new Genre(rs.getString(6));
            genre.setId(rs.getLong(5));
            Set<Genre> genres = new HashSet<>();
            genres.add(genre);

            book.setAuthor(authors);
            book.setGenre(genres);

            return book;
        }
    }

}
