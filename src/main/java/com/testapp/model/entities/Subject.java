package com.testapp.model.entities;

import java.util.List;

public class Subject extends AbstractEntity {
    private String name;
    private List<Test> tests;
    private Tutor tutors;
    private List<Student> students;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
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
