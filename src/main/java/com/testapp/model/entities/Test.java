package com.testapp.model.entities;

import java.util.List;

public class Test extends AbstractEntity {
    private String name;
    private List<Question> questionList;
    private List<TestResults> testResultsList;
    private Test test;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public List<TestResults> getTestResultsList() {
        return testResultsList;
    }

    public void setTestResultsList(List<TestResults> testResultsList) {
        this.testResultsList = testResultsList;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
