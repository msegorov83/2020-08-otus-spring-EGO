package ru.otus.spring.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class AuthorMongo {

    @Id
    private String id;
    private String fullName;

    @DBRef
    private List<BookAuthorMongo> books;

    public AuthorMongo(String fullName, List<BookAuthorMongo> books) {
        this.fullName = fullName;
        this.books = books;
    }

}
