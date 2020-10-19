package ru.otus.spring.service;

import ru.otus.spring.domain.Student;

public interface StudentService {
    Student getByFullName(String fullName);
    String logonStudent();
    void setExam(Boolean exam);

}
