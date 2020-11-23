package ru.otus.spring.domain;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name", nullable = false, unique = true)
    private String fullName;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private Set<BookAuthor> books = new HashSet<>();

    public Author(String fullName) { this.fullName = fullName; }
    public Author() {}

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + fullName + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set getBooks() {
        return this.books;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}

