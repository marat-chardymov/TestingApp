package com.testapp.model.entities;

import java.util.List;

public class Group extends AbstractEntity {
    private String name;
    private List<Student> students;

    public Group(String name, List<Student> group) {
        this.name = name;
        this.students = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

}
