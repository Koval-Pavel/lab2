package com.nc.lab2.model;

public class Mark {

    private int id;
    private int StudentId;
    private int SubjectId;
    private double mark;

    public Mark(int id, int studentId, int subjectId, double mark) {
        this.id = id;
        StudentId = studentId;
        SubjectId = subjectId;
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }

    public int getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(int subjectId) {
        SubjectId = subjectId;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
}
