package ru.ostus.spring.dao;

import ru.ostus.spring.domain.Question;

import java.util.List;

public interface QuestionDAO {
    void setQuestionList(Question question);
    List<Question> getQuestionList();
    void addQuestion(Question question);


}
