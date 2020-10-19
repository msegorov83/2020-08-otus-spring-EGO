package ru.otus.spring.domain;

public class Student {
    private final String fullName;
    private Boolean isExam;

    public Student(String fullName, Boolean exam) {
        this.fullName = fullName;
        this.isExam = exam;
    }

    public String getFullName() {
        return fullName;
    }

    public Boolean getExam() {
        return isExam;
    }

    public void setExam(boolean exam) { this.isExam = exam; }
}
