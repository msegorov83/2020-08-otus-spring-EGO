package ru.otus.spring.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Book {
    @Id
    private String id;
    private String name;

    @DBRef
    private Set<Author> author = new HashSet();

    @DBRef
    private Set<Genre> genre = new HashSet();

    public Book(String name, Set author, Set genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public Book(String name) { this.name = name; }

}