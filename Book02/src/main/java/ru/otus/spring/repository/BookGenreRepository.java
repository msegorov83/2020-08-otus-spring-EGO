package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookGenre;

public interface BookGenreRepository extends JpaRepository<BookGenre, Long> {

    @Transactional
    void deleteByBook(Book book);

}
