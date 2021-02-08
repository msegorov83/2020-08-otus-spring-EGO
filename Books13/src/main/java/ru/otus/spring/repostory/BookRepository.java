package ru.otus.spring.repostory;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    List<Book> findAll();

    void deleteById(long id);

    Book findById(long id);

}
