package com.testapp.model.entities;

import java.util.List;

public class Subject extends AbstractEntity {
    private String name;
    private List<Test> tests;

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
}
