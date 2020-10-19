package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import ru.otus.spring.config.AppProps;
import ru.otus.spring.dao.QuestionDAO;
import ru.otus.spring.domain.Question;

public class ExamServiceImpl implements ExamService {
    private QuestionDAO questionDAO;
    private MessageSource messageSource;
    private AppProps appProps;

    public  ExamServiceImpl(QuestionDAO questionDAO, MessageSource messageSource, AppProps appProps) {
        this.questionDAO = questionDAO;
        this.messageSource=messageSource;
        this.appProps=appProps;
      }

    @Override
    public boolean startExam(final String fulname)  throws Exception {

        int countCorrectAnswers = 0;

        for (Question question:questionDAO.getQuestionList()) {
            System.out.println(question.getQuestionText());
            System.out.println(question.getAnswers());

            var chooseAsnwer_locale = messageSource.getMessage("exam.chooseasnwer",
                    new String[]{String.valueOf(question.getAnswers().stream().count())},
                    appProps.getLocale());

            System.out.println(chooseAsnwer_locale);
            int buffSimbol = (char) System.in.read();

            if (question.getCorrect().equals(Character.toString((char) buffSimbol))) {
                countCorrectAnswers = countCorrectAnswers + 1;
                var correctAnswer_locale = messageSource.getMessage("exam.correctanswer", null, appProps.getLocale());
                System.out.println(correctAnswer_locale);
            } else {
                var notCorrectAnswer_locale = messageSource.getMessage("exam.notcorrectanswer", null, appProps.getLocale());
                System.out.println(notCorrectAnswer_locale);
            }
            System.in.read();
        }

        if (countCorrectAnswers == questionDAO.getQuestionList().stream().count()) {
            var passed_locale = messageSource.getMessage("exam.passed", new String[]{fulname}, appProps.getLocale());
            System.out.println(passed_locale);
            return true;
        } else {
            var notPassed_locale = messageSource.getMessage("exam.notpassed", new String[]{fulname}, appProps.getLocale());
            System.out.println(notPassed_locale);
            return false;
        }

    }
}
