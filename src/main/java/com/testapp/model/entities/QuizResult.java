package com.testapp.model.entities;

import java.util.Date;

public class QuizResult extends Entity {
    private int scores;
    private String quizName;
    private Long quizId;
    private String userName;
    private Long userId;
    private Date date;

    public QuizResult(Long userId, Long quizId, int scores) {
        this.userId = userId;
        this.quizId = quizId;
        this.scores = scores;
    }

    public QuizResult(String username, String quizName, int scores, Date date) {
        this.userName = username;
        this.quizName = quizName;
        this.scores = scores;
        this.date = date;
    }

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
