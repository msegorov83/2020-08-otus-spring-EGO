package ru.otus.spring.dao;

import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Question;

import java.util.List;

@Component
public interface QuestionDAO {
    void setQuestionList(Question question);
    List<Question> getQuestionList();
    void addQuestion(Question question);
}
