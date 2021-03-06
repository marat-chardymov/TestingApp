package com.testapp.model.entities;

import java.util.List;

public class User extends Entity {
    private String name;
    private String surname;
    private String email;
    private List<QuizResult> quizResultList;
    private String password;
    private Role role;

    public User(String name, String surname, String email, String password, Role role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public List<QuizResult> getQuizResultList() {
        return quizResultList;
    }

    public void setQuizResultList(List<QuizResult> quizResultList) {
        this.quizResultList = quizResultList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
