package com.testapp.model.entities;

import java.util.List;

public class Question extends Entity {
    private String content;
    private List<Answer> answers;
    private Long quizId;

    public Question(String content, Long quizId) {
        super();
        this.quizId = quizId;
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

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }
}
