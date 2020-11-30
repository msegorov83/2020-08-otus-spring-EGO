package ru.otus.spring.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
public class BookGenre {

    @Embeddable
    public static class Id implements Serializable {

        @Column(name = "book_id")
        private Long bookId;

        @Column(name = "genre_id")
        private Long genreId;

        @Override
        public int hashCode() {
            return bookId.hashCode() + genreId.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof BookGenre.Id) {
                BookGenre.Id other = (BookGenre.Id) obj;
                return this.genreId.equals(other.genreId) && this.bookId.equals(other.bookId);
            }
            return false;
        }

    }

    @EmbeddedId
    private final BookGenre.Id id = new BookGenre.Id();

    @ManyToOne
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "genre_id", insertable = false, updatable = false)
    private Genre genre;

    public BookGenre() {
    }

    public BookGenre(Book book, Genre genre) {
        this.book = book;
        this.genre = genre;

        this.id.bookId = book.getId();
        this.id.genreId = genre.getId();

        book.getGenres().add(this);
        genre.getBooks().add(this);
    }

    public Genre getAuthor() {
        return genre;
    }

    public Book getBook() {
        return book;
    }
}
