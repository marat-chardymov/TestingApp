package com.testapp.model.entities;

import java.util.List;

public class Question extends AbstractEntity {
    private String content;
    private List<Answer> answers;

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
}
