package ru.otus.spring.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ru.otus.spring.Main;
import ru.otus.spring.config.AppProps;
import ru.otus.spring.service.ExamService;
import ru.otus.spring.service.QuestionService;
import ru.otus.spring.service.StudentService;

import java.util.Locale;

@Component
public class InitApp {

    private AppProps appProps;

    public InitApp(AppProps appProps) {
        this.appProps = appProps;
    }

    public void run() throws Exception {

        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        Resource resource;
        if(appProps.getLocale().equals(Locale.ENGLISH))
            resource = context.getResource("classpath:questions_en.csv");
        else
            resource = context.getResource("classpath:questions_ru.csv");

        StudentService studentService=context.getBean(StudentService.class);
        QuestionService questionService = context.getBean(QuestionService.class);
        ExamService examService = context.getBean(ExamService.class);

        questionService.setResource(resource);
        boolean isExam=examService.startExam(studentService.logonStudent());
        studentService.setExam(isExam);
    }
}
