package ru.otus.spring.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.QuestionDAO;
import ru.otus.spring.dao.StudentDAO;
import ru.otus.spring.service.*;

@Configuration
public class ServicesConfig {

    @Bean
    public StudentService studentService(StudentDAO dao, MessageSource messageSource,  AppProps appProps) { return new StudentServiceImpl(dao, messageSource, appProps); }

    @Bean
    public QuestionService questionService(QuestionDAO dao, AppProps appProps) {
        return new QuestionServiceImpl(dao, appProps);
    }

    @Bean
    public ExamService examService(QuestionDAO questionDAO, MessageSource messageSource,  AppProps appProps) { return  new ExamServiceImpl(questionDAO, messageSource, appProps); }

}
