package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Book {
    @Id
    private String id;
    private String name;

    @DBRef
    private List<Author> author;

    @DBRef
    private List<Genre> genre;

    public Book(String name, List<Author>author, List<Genre> genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public Book(String name) { this.name = name; }

}