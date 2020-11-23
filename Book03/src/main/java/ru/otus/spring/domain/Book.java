package ru.otus.spring.domain;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private Set<BookAuthor> authors = new HashSet<>();

    @BatchSize(size = 5)
    @ManyToMany(targetEntity = Genre.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "book_genre", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genre = new HashSet();

    public Book() { }
    public Book(long id, String name) { this.id = id; this.name = name;}

    public Book(String name, Set author, Set genre) {
        this.name = name;
        this.authors = author;
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

    public Set getAuthors() {
       return this.authors;
    }

    public Genre getGenre() {
      return genre.stream().findFirst().orElse(null);
    }

    public void setAuthors(Set authors) {
        this.authors = authors;
    }

    public void setGenre(Set genre) {
        this.genre = genre;
    }


}