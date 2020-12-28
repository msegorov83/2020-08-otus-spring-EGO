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
public class BookMongo {

    @Id
    private String id;
    private String name;

    @DBRef
    private List<BookAuthorMongo> authorMongos;

    public BookMongo(String name, List<BookAuthorMongo> authorMongos) {
        this.name = name;
        this.authorMongos = authorMongos;

    }

    public BookMongo(String name) { this.name = name; }
}
