package ru.otus.spring.runner;

import org.springframework.core.io.Resource;
import ru.otus.spring.service.ExamService;
import ru.otus.spring.service.QuestionService;
import ru.otus.spring.service.StudentService;

public class InitApp {
    public void run(ExamService examService, StudentService studentService, QuestionService questionService , Resource resource) throws Exception {

        questionService.setResource(resource);
        boolean isExam=examService.startExam(studentService.logonStudent());
        studentService.setExam(isExam);
    }
}
