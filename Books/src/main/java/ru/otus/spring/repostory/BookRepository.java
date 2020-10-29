package ru.otus.spring.repostory;


import org.springframework.data.repository.PagingAndSortingRepository;
import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    Book save(Book book);
    List<Book> findAll();
    void deleteById(long id);
    Book findById(long id);

}
