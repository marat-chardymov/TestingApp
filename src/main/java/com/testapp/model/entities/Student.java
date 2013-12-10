package com.testapp.model.entities;

import java.util.List;

public class Student extends Person {
    private List<TestResults> testResultsList;
    private List<Subject> subjects;

    public List<TestResults> getTestResultsList() {
        return testResultsList;
    }

    public void setTestResultsList(List<TestResults> testResultsList) {
        this.testResultsList = testResultsList;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
