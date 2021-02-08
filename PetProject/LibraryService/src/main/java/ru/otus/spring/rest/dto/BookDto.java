package ru.otus.spring.rest.dto;

import ru.otus.spring.domain.Book;

@SuppressWarnings("all")
public class BookDto {

    private long id = -1;
    private String name;
    private String author;
    private String genre;

    public BookDto (Long id, String name, String author, String genre) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public static BookDto toDo(Book book) { return new BookDto(book.getId(), book.getName(), book.getAuthor(), book.getGenre()); }
}
