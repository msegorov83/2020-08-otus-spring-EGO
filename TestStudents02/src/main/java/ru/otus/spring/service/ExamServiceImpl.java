package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionDAO;
import ru.otus.spring.domain.Question;

public class ExamServiceImpl implements ExamService  {
    private QuestionDAO questionDAO;

    public  ExamServiceImpl(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    @Override
    public boolean startExam(final String fulname)  throws Exception {
        int countCorrectAnswers = 0;

        for (Question question:questionDAO.getQuestionList()) {
            System.out.println(question.getQuestionText());
            System.out.println(question.getAnswers());

                System.out.println("Enter please answer 1 - " + question.getAnswers().stream().count());
                int buffSimbol = (char) System.in.read();

                if (question.getCorrect().equals(Character.toString((char) buffSimbol))) {
                    countCorrectAnswers = countCorrectAnswers + 1;
                    System.out.println("Is correct");
                } else {
                    System.out.println("Is not correct");
                }
               System.in.read();
        }

        if (countCorrectAnswers == questionDAO.getQuestionList().stream().count()) {
            System.out.println(fulname  + "passed the exam");
            return true;
        } else {
            System.out.println(fulname + "did not pass exam");
            return false;
        }

    }
}
