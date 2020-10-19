package ru.ostus.spring.domain;

import java.util.List;

public class Question {
    private String questionText;
    private List<String> answers;

    public Question() {}

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
