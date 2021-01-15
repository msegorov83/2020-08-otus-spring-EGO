package ru.otus.spring.actuators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.otus.spring.repostory.BookRepository;

@Component
public class BookHealthIndicator implements HealthIndicator  {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Health health() {
        long count  = bookRepository.count();

        return Health.up().withDetail("message", "Number of records in Book = " + count).build();
    }
}
