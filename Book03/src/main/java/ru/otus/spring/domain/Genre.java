package ru.otus.spring.domain;

import javax.persistence.*;

@Entity
@Table(name =  "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String gName;

    public Genre () {}

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + gName + '\'' +
                '}';
    }

    public Genre(String name) {this.gName = name;}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }
}
