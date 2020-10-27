package ru.otus.spring.domain;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;


    @BatchSize( size = 5)
    @ManyToMany(targetEntity = Author.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> author;

    @BatchSize(size = 5)
    @ManyToMany(targetEntity = Genre.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "book_genre", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genre;

    public Book() { }
    public Book(long id, String name) { this.id = id; this.name = name;}

    public Book(String name, List<Author> author, List<Genre> genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    public Book(String name) { this.name = name; }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author.stream().findFirst().map(Author::getFullName).toString();
    }

    public String getGenre() {
        return genre.stream().findFirst().map(Genre::getgName).toString();

    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }


}
