package ru.otus.spring.service;

import ru.otus.spring.dao.StudentDAO;
import ru.otus.spring.domain.Student;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StudentServiceImpl implements StudentService {
    private StudentDAO dao;

    public StudentServiceImpl(StudentDAO dao) {
     this.dao = dao;
    }

    @Override
    public Student getByFullName(String fullName) {
        return dao.findByFullName(fullName);
    }

    @Override
    public String logonStudent() {
        String fullName;
        System.out.println("Enter please full name");
        try {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(System.in));
            fullName = reader.readLine();

            Student student = getByFullName(fullName);
            System.out.println("Full Name: " + student.getFullName());
            return fullName;

        } catch (Exception err) { }
        return null;
    }

    @Override
    public void setExam(Boolean exam) { dao.setExam(exam);}

}
