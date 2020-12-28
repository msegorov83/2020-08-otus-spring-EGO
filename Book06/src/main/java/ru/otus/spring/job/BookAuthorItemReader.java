package ru.otus.spring.job;

import java.util.Iterator;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.domain.BookAuthor;
import ru.otus.spring.repository.BookAuthorRepository;


public class BookAuthorItemReader implements ItemReader<BookAuthor> {

    @Autowired
    private BookAuthorRepository bookAuthorRepository;

    private Iterator<BookAuthor> bookAuthorIterator;

    @BeforeStep
    public void before(StepExecution stepExecution) {
        bookAuthorIterator = bookAuthorRepository.findAll().iterator();
    }

    @Override
    public BookAuthor read() {
        if (bookAuthorIterator != null && bookAuthorIterator.hasNext()) {
            return bookAuthorIterator.next();
        } else {
            return null;
        }
    }
}
