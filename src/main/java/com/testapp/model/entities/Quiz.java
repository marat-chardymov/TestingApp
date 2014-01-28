package com.testapp.model.entities;

import java.util.List;

public class Quiz extends Entity {
    private String name;
    private List<QuizResult> quizResultList;
    private List<Question> questions;
    private Long subjectId;

    public Quiz(String name, Long subjectId) {
        super();
        this.name = name;
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QuizResult> getQuizResultList() {
        return quizResultList;
    }

    public void setQuizResultList(List<QuizResult> quizResultList) {
        this.quizResultList = quizResultList;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

}
