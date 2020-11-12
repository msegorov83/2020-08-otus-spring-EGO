package ru.otus.spring.domain;

import javax.persistence.*;

@Entity
@Table(name =  "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Genre() {}

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", gName='" + name + '\'' +
                '}';
    }

    public Genre(String name) {this.name = name;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
