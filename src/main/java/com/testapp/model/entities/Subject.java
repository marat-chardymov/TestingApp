package com.testapp.model.entities;

import java.util.List;

public class Subject extends AbstractEntity {
    private String name;
    private List<Quiz> quizs;
    private Tutor tutors;
    private List<Student> students;

    public Subject(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Quiz> getQuizs() {
        return quizs;
    }

    public void setQuizs(List<Quiz> quizs) {
        this.quizs = quizs;
    }

    public Tutor getTutors() {
        return tutors;
    }

    public void setTutors(Tutor tutors) {
        this.tutors = tutors;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
