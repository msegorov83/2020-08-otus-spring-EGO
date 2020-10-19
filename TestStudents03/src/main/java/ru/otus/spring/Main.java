package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.Resource;
import ru.otus.spring.config.AppProps;
import ru.otus.spring.runner.InitApp;
import ru.otus.spring.service.ExamService;
import ru.otus.spring.service.QuestionService;
import ru.otus.spring.service.StudentService;

import java.util.Locale;

@SpringBootApplication
@EnableConfigurationProperties(AppProps.class)
public class Main {

    private static AppProps appProps;

    public Main(AppProps appProps) {
        this.appProps = appProps;
    }

    public static void main(String[] args) throws Exception {

        var context = SpringApplication.run(Main.class, args);

        Resource resource;
        if(appProps.getLocale().equals(Locale.ENGLISH))
            resource = context.getResource("classpath:questions_en.csv");
        else
            resource = context.getResource("classpath:questions_ru.csv");

        StudentService studentService=context.getBean(StudentService.class);
        QuestionService questionService = context.getBean(QuestionService.class);
        ExamService examService = context.getBean(ExamService.class);

        new InitApp().run(examService,studentService,questionService, resource);

    }



}