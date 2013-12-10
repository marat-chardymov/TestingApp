package com.testapp.model.entities;

import java.util.List;

public class Test extends AbstractEntity {
    private String name;
    private List<Question> questionList;

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
}
