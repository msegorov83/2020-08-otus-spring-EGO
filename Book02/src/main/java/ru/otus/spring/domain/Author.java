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
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name", nullable = false, unique = true)
    private String fullName;

    @BatchSize(size = 5)
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Set<BookAuthor> books = new HashSet<>();

    public Author(String fullName) {
        this.fullName = fullName;
    }

    public Author() {

    }

    @Override
    public String toString() {
        return "Author{"
                + "id=" + id
                + ", name='" + fullName + '\''
                + '}';
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Set<BookAuthor> getBooks() {
        return this.books;
    }

}
