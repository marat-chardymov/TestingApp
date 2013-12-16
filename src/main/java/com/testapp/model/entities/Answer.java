package com.testapp.model.entities;

import java.beans.Transient;

public class Answer extends AbstractEntity {
    private String content;
    private boolean isRight;
    @javax.persistence.Transient
    private boolean isChosen;

    public Answer(String content, boolean isRight) {
        this.content = content;
        this.isRight = isRight;
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

}
