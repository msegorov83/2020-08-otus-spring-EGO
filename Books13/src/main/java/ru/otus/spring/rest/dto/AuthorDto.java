package ru.otus.spring.rest.dto;

import ru.otus.spring.domain.Author;

@SuppressWarnings("all")
public class AuthorDto {
    private long id;
    private String fullName;

    public AuthorDto(long id,String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public static AuthorDto toDo(Author author) { return new AuthorDto(author.getId(), author.getFullName()); }
}
