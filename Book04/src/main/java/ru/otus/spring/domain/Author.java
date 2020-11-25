package ru.otus.spring.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Author {

    @Id

    private String id;
    private String fullName;

    public Author(String fullName) { this.fullName = fullName; }
}

