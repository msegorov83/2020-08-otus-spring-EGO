package ru.otus.spring.service;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;
import ru.otus.spring.dao.QuestionDAO;

import java.io.IOException;

public interface QuestionService {
    void setResource(Resource resourceCSV) throws IOException;
    QuestionDAO getQuestions();
}
