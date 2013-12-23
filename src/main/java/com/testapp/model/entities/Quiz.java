package com.testapp.model.entities;

import java.util.List;

public class Quiz extends AbstractEntity {
    private String name;
    private List<QuizResults> quizResultsList;
    private Long subjectId;

    public Quiz(String name, Long fk_subject) {
        super();
        this.name = name;
        this.subjectId=fk_subject;
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

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

}
