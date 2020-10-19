package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.QuestionDAO;
import ru.otus.spring.dao.StudentDAO;
import ru.otus.spring.service.*;

@Configuration
public class ServicesConfig {

    @Bean
    public StudentService studentService(StudentDAO dao) { return new StudentServiceImpl(dao); }

    @Bean
    public QuestionService questionService(QuestionDAO dao) {
       return new QuestionServiceImpl(dao);
    }

    @Bean
    public ExamService examService(QuestionDAO questionDAO) { return  new ExamServiceImpl(questionDAO); }

}
