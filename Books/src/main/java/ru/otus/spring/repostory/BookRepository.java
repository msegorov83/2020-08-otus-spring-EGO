package ru.otus.spring.repostory;


import org.springframework.data.repository.PagingAndSortingRepository;
import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    List<Book> findAll();
    Book findById(long id);
}
