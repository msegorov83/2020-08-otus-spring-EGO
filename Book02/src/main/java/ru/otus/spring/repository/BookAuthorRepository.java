package ru.otus.spring.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookAuthor;


public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long> {

    List<BookAuthor> findByAuthor(Author author);

    @Transactional
    void deleteByBook(Book book);
}
