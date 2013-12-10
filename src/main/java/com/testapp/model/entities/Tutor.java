package com.testapp.model.entities;

import java.util.List;

public class Tutor extends Person {
    private Subject subjects;

    public Subject getSubjects() {
        return subjects;
    }

    public void setSubjects(Subject subjects) {
        this.subjects = subjects;
    }
}
