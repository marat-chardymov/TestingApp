package com.testapp.model.entities;

import java.util.List;

public class Group extends AbstractEntity {
    private String name;
    private List<Student> group;

    public Group(String name, List<Student> group) {
        this.name = name;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getGroup() {
        return group;
    }

    public void setGroup(List<Student> group) {
        this.group = group;
    }

}
