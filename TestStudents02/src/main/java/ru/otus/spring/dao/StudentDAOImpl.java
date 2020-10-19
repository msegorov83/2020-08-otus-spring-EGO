package ru.otus.spring.dao;

import ru.otus.spring.domain.Student;

public class StudentDAOImpl implements StudentDAO {
    private Student student;

    @Override
    public Student findByFullName(String fullName) {
        student = new Student(fullName, false);
        return student;
    }

    @Override
    public void setExam(Boolean exam) { student.setExam(exam); }
}
