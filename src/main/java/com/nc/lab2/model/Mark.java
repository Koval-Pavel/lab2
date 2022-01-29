package com.nc.lab2.model;

/**
 * Class that describe Mark entity.
 */
public class Mark {

    /** Unique id of Mark*/
    private int id;

    /** id of the Student that have this Mark*/
    private int studentId;

    /** id of the Subject for this Mark*/
    private int subjectId;

    /** Value of the Mark*/
    private double value;

    /** Name of the Student that have this Mark*/
    private String studentName;

    /** Name of the Subject for this Mark*/
    private String subjectName;

    /** date when mark recived */
    private String date;

    public Mark() {
        super();
    }

    /**#
     * Constructor - creation of new Mark Object with param's.
     * @param id - Unique id of Mark
     * @param studentId - id of the Student that have this Mark
     * @param subjectId - id of the Subject for this Mark
     * @param value - Value of the Mark
     * @param studentName - Name of the Student that have this Mark
     * @param subjectName - Name of the Subject for this Mark
     * @param date - date when mark recived
     */
    public Mark(int id, int studentId, int subjectId, double value, String studentName, String subjectName, String date) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.value = value;
        this.studentName = studentName;
        this.subjectName = subjectName;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", subjectId=" + subjectId +
                ", value=" + value +
                ", studentName='" + studentName + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", subjectDate='" + date + '\'' +
                '}';
    }
}
