package ru.otus.spring.job;

import java.util.Iterator;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repository.AuthorRepository;


public class AuthorItemReader implements  ItemReader<Author> {

    @Autowired
    private AuthorRepository authorRepository;

    private Iterator<Author> authorsIterator;

    @BeforeStep
    public void before(StepExecution stepExecution) {
        authorsIterator = authorRepository.findAll().iterator();
    }

    @Override
    public Author read() {
        if (authorsIterator != null && authorsIterator.hasNext()) {
            return authorsIterator.next();
        } else {
            return null;
        }
    }
}
