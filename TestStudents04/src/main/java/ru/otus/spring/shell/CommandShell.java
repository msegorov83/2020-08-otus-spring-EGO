package ru.otus.spring.shell;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.ExitRequest;

import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.config.AppProps;
import ru.otus.spring.service.ExamService;
import ru.otus.spring.service.QuestionService;
import ru.otus.spring.service.StudentService;

import java.util.Locale;


@ShellComponent
public class CommandShell {

    private StudentService studentService;
    private ExamService examService;
    private QuestionService questionService;
    private String userName;
    private AppProps appProps;
    private Resource resource;

    public CommandShell(StudentService studentService, ExamService examService, QuestionService questionService, AppProps appProps) {
        this.studentService=studentService;
        this.examService=examService;
        this.questionService=questionService;
        this.appProps = appProps;
    }

    @ShellMethod(value = "Login command", key = {"login"})
    public String logonStudent(@ShellOption(defaultValue = "student") String userName) {
        this.userName = studentService.logonStudent(userName); return "signedin";
    }

    @ShellMethod(value = "Start exam", key = {"start"})
    @ShellMethodAvailability(value = "isStartCommandAvailable")
    public String startExam() throws Exception {

        if(resource == null){
            ApplicationContext context = new AnnotationConfigApplicationContext();
            if(appProps.getLocale().equals(Locale.ENGLISH))
                resource = context.getResource("classpath:questions_en.csv");
            else
                resource = context.getResource("classpath:questions_ru.csv");
            questionService.setResource(resource);
        }

        boolean isExam=examService.startExam(userName);
        studentService.setExam(isExam);
        return "started";
    }

    private Availability isStartCommandAvailable() {
        return userName == null? Availability.unavailable("Login first"): Availability.available();
    }



}
