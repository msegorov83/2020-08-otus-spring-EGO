package ru.otus.spring.job;

import java.util.Iterator;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repository.BookRepository;


public class BookItemReader implements ItemReader<Book> {

    @Autowired
    private BookRepository bookRepository;

    private Iterator<Book> boksIterator;

    @BeforeStep
    public void before(StepExecution stepExecution) {
        boksIterator = bookRepository.findAll().iterator();
    }

    @Override
    public Book read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (boksIterator != null && boksIterator.hasNext()) {
            return boksIterator.next();
        } else {
            return null;
        }
    }
}
