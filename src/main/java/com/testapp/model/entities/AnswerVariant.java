package com.testapp.model.entities;

public class AnswerVariant extends AbstractEntity {
    private String answerv;
    private boolean isRight;

    public AnswerVariant(String answerv) {
        this.answerv = answerv;
    }

    public String getAnswerv() {
        return answerv;
    }

    public void setAnswerv(String answerv) {
        this.answerv = answerv;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }
}
