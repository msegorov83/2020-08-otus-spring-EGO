package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class BookAuthorMongo {

    @Id
    private String id;

    private String bookId;
    private String authorId;

}
