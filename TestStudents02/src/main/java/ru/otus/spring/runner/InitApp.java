package ru.otus.spring.runner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ru.otus.spring.Main;
import ru.otus.spring.service.ExamService;
import ru.otus.spring.service.QuestionService;
import ru.otus.spring.service.StudentService;


@Component
public class InitApp  {

    public void run() throws Exception {

        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        Resource resource = context.getResource("classpath:questions.csv");
        StudentService studentService=context.getBean(StudentService.class);
        QuestionService questionService = context.getBean(QuestionService.class);
        ExamService examService = context.getBean(ExamService.class);

        questionService.setResource(resource);
        boolean isExam=examService.startExam(studentService.logonStudent());
        studentService.setExam(isExam);

    }

}
