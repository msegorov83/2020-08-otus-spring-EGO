package ru.ostus.spring.dao;

import ru.ostus.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionDAOImpl implements QuestionDAO {
    private List<Question> questionList;

    public QuestionDAOImpl() { }

    public void setQuestionList(Question question) {
        questionList=new ArrayList<>();

    }

    public void addQuestion(Question question) {
         questionList.add(question);
    }

    public List<Question> getQuestionList() {
        return questionList;

    }
}
