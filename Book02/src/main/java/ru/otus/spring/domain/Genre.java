package ru.otus.spring.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name =  "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Genre() {
    }

    @Override
    public String toString() {
        return "Genre{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }

    public Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
