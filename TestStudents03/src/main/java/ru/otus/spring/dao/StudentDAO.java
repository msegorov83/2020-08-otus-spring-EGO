package ru.otus.spring.dao;

import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Student;

@Component
public interface StudentDAO {
    Student findByFullName(String fullName);
    void setExam(Boolean exam);
}
