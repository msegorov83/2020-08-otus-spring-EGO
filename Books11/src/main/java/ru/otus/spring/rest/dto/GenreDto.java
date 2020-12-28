package ru.otus.spring.rest.dto;

import ru.otus.spring.domain.Genre;

@SuppressWarnings("all")
public class GenreDto {
    private long id;
    private String gName;

    public GenreDto(long id, String gName) {
        this.id = id;
        this.gName = gName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public long getId() {
        return id;
    }

    public String getgName() {
        return gName;
    }

    public static GenreDto toDo(Genre genre) { return new GenreDto(genre.getId(), genre.getName()); }
}

