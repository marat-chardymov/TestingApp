package com.testapp.model.entities;

import java.beans.Transient;

public class Answer extends AbstractEntity {
    private String content;
    private boolean isRight;
    @javax.persistence.Transient
    private boolean isChosen;
    private Question question;

    public Answer(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public boolean isChosen() {
        return isChosen;
    }

    public void setChosen(boolean isChosen) {
        this.isChosen = isChosen;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
