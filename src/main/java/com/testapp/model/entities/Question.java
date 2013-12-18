package com.testapp.model.entities;

import java.util.List;

public class Question extends AbstractEntity {
    private String content;
    private List<Answer> answers;
    private Quiz quiz;

    public Question(Quiz quiz, String content) {
        super();
        this.quiz = quiz;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
