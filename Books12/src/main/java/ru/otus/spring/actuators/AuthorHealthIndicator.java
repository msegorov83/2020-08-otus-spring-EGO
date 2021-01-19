package ru.otus.spring.actuators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.otus.spring.repostory.AuthorRepository;

@Component
public class AuthorHealthIndicator implements HealthIndicator {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Health health() {
        long count = authorRepository.count();
        return Health.up().withDetail("message", "Number of records in Author = " + count).build();
    }
}