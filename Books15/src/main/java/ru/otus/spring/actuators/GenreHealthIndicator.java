package ru.otus.spring.actuators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.otus.spring.repostory.GenreRepository;

@Component
public class GenreHealthIndicator implements HealthIndicator {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Health health() {
        long count = genreRepository.count();
        return Health.up().withDetail("message", "Number of records in Genre = " + count).build();
    }
}
