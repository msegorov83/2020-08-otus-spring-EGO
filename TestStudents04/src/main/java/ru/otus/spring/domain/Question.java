package ru.otus.spring.domain;

import java.util.List;

public class Question {
    private final String questionText;
    private final List<String> answers;
    private String correct;

    public Question(String questionText,List<String> answers, String correct) {
        this.questionText = questionText;
        this.answers = answers;
        this.correct = correct;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getCorrect() {
        return correct;
    }
}
