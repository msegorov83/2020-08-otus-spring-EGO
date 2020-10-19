package ru.ostus.spring.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.Resource;
import ru.ostus.spring.dao.QuestionDAO;
import ru.ostus.spring.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private QuestionDAO dao;
    private Resource resourceCSV;

    public void setDao(QuestionDAO dao) {
        this.dao = dao;
    }

    public void setResource() {

        try {
            InputStream stream=resourceCSV.getInputStream();
            BufferedReader buff = new BufferedReader(new InputStreamReader(stream));
            CSVParser parser = CSVFormat.DEFAULT.withHeader().withDelimiter(';').parse(buff);

            for (CSVRecord record:parser) {
                String questionText=record.get("Question").trim();
                List<String> answers = new ArrayList<>();
                answers.add(record.get("Answer1").trim());
                answers.add(record.get("Answer2").trim());
                answers.add(record.get("Answer3").trim());
                Question question = new Question();
                question.setQuestionText(questionText);
                question.setAnswers(answers);
                dao.addQuestion(question);
            }

        } catch (IOException err ) {}
    }

    public void showTest() {
        for (Question question:dao.getQuestionList()) {
            System.out.println(question.getQuestionText());
            System.out.println(question.getAnswers());
            try {
                System.out.println("Press any key");
                System.in.read();
            } catch (Exception err) {}
        }
    }

    public void setResourceCSV(Resource resourceCSV) {
        this.resourceCSV = resourceCSV;
    }
}
