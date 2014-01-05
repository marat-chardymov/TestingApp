package com.testapp.model.entities;

public class Answer extends Entity {
    private String content;
    private boolean isRight;
    @javax.persistence.Transient
    private boolean isChosen;
    private Long questionId;

    public Answer(String content, boolean isRight, Long questionId) {
        this.content = content;
        this.isRight = isRight;
        this.questionId = questionId;
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

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
