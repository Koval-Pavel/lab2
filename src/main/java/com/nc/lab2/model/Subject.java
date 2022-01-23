package com.nc.lab2.model;

public class Subject {

    private int id;
    private String name;
    private String TeacherName;

    public Subject(int id, String name, String teacherName) {
        this.id = id;
        this.name = name;
        TeacherName = teacherName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }
}
