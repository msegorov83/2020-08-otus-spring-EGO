package ru.otus.spring.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.BatchSize;

@Entity
@Table(name =  "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @BatchSize(size = 5)
    @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
    private Set<BookGenre> books = new HashSet<>();

    public Genre() {
    }

    @Override
    public String toString() {
        return "Genre{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }

    public long getId() {
        return id;
    }

    public Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<BookGenre> getBooks() {
        return this.books;
    }

}
