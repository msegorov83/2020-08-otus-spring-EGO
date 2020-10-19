package ru.otus.spring.dao;

import ru.otus.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;


public class QuestionDAOImpl implements QuestionDAO {
    private List<Question> questionList;

    public QuestionDAOImpl() {
        questionList=new ArrayList<>();
    }

    @Override
    public void setQuestionList(Question question) {
        questionList=new ArrayList<>();
    }

    @Override
    public List<Question> getQuestionList() {
        return questionList;
    }

    @Override
    public void addQuestion(Question question) {
        questionList.add(question);

    }
}
