package com.testapp.model.entities;

import java.util.List;

public class Subject extends AbstractEntity {
    private String name;
    private List<Quiz> quizzes;

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
        return quizzes;
    }

    public void setQuizs(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }


}
