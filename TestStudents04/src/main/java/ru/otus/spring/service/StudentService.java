package ru.otus.spring.service;

import ru.otus.spring.domain.Student;

public interface StudentService {
    Student getByFullName(String fullName);
    String logonStudent(String userName);
    void setExam(Boolean exam);
    boolean showExam();

}

