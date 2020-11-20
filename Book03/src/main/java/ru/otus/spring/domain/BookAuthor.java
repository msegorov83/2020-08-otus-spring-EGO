package ru.otus.spring.domain;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Immutable
@Table(name = "book_author")
public class BookAuthor {

    @Embeddable
    public static class Id implements Serializable {

        @Column(name = "book_id")
        private Long bookId;

        @Column(name = "author_id")
        private Long authorId;

        @Override
        public int hashCode() {
            return bookId.hashCode() + authorId.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj != null && obj instanceof Id) {
                Id other = (Id) obj;
                return this.authorId.equals(other.authorId) && this.bookId.equals(other.bookId);
            }
            return false;
        }

    }

    @EmbeddedId
    private Id id = new Id();

    @ManyToOne
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private Author author;

    public BookAuthor() {}

    public BookAuthor(Book book, Author author) {
        this.book = book;
        this.author = author;

        this.id.bookId = book.getId();
        this.id.authorId = author.getId();

        book.getAuthors().add(this);
        author.getBooks().add(this);
    }

    public Author getAuthor() {
        return author;
    }

    public Book getBook() {
        return book;
    }
}
