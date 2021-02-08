package ru.otus.spring.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String s);
}
