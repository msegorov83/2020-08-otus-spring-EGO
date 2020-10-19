package ru.otus.spring.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import ru.otus.spring.dao.QuestionDAO;
import ru.otus.spring.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private QuestionDAO dao;

    public QuestionServiceImpl(QuestionDAO dao) {
        this.dao = dao;
    }

    public void setDao(QuestionDAO dao) {
        this.dao = dao;
    }

    @Override
    public QuestionDAO getQuestions() { return dao; }

    @Override
    public void setResource(Resource resourceCSV) throws IOException {

            InputStream stream=resourceCSV.getInputStream();
            BufferedReader buff = new BufferedReader(new InputStreamReader(stream));
            CSVParser parser = CSVFormat.DEFAULT.withHeader().withDelimiter(';').parse(buff);

            for (CSVRecord record:parser) {
                String questionText=record.get("Question").trim();
                List<String> answers = new ArrayList<>();
                answers.add(record.get("Answer1").trim());
                answers.add(record.get("Answer2").trim());
                answers.add(record.get("Answer3").trim());
                String correct = record.get("Correct").trim() ;
                Question question = new Question(questionText,answers,correct);
                dao.addQuestion(question);
            }

    }


}
