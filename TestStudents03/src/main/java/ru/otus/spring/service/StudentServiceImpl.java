package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import ru.otus.spring.config.AppProps;
import ru.otus.spring.dao.StudentDAO;
import ru.otus.spring.domain.Student;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StudentServiceImpl implements StudentService {
    private StudentDAO dao;
    private MessageSource messageSource;
    private AppProps appProps;

    public StudentServiceImpl(StudentDAO dao, MessageSource messageSource, AppProps appProps)
    {
        this.dao = dao;
        this.messageSource = messageSource;
        this.appProps = appProps;
     }

    @Override
     public Student getByFullName(String fullName) {
        return dao.findByFullName(fullName);
    }


    @Override
    public String logonStudent() {

        var enterMessage_locale = messageSource.getMessage("student.entermessage", null ,appProps.getLocale());
        System.out.println(enterMessage_locale);

        try {
            String buff;
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(System.in));
            buff = reader.readLine();

            Student student = getByFullName(buff);
            var fullName_locale = messageSource.getMessage("student.fullname", new String[]{student.getFullName()} ,appProps.getLocale());
            System.out.println(fullName_locale);

            return buff;

        } catch (Exception err) { }
        return null;
    }

    @Override
    public void setExam(Boolean exam) { dao.setExam(exam);}
}
