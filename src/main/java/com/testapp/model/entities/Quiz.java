package com.testapp.model.entities;

import java.util.List;

public class Quiz extends AbstractEntity {
    private String name;
    private List<QuizResults> quizResultsList;

    public Quiz(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QuizResults> getQuizResultsList() {
        return quizResultsList;
    }

    public void setQuizResultsList(List<QuizResults> quizResultsList) {
        this.quizResultsList = quizResultsList;
    }

}
