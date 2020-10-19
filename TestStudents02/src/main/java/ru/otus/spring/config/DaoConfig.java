package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.*;

@Configuration
public class DaoConfig {

    @Bean
    public QuestionDAO questionDAO() {
        return new QuestionDAOImpl();
    }

    @Bean
    public StudentDAO studentDAO() {
        return  new StudentDAOImpl();
    }

}
