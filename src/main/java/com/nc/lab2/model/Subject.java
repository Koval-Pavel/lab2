package com.nc.lab2.model;

public class Subject {

    /** Unique id of the Subject*/
    private int id;

    /** name of the Subject */
    private String name;

    /** name of the Teacher Name */
    private String TeacherName;

    public Subject() {
        super();
    }


    /**
     * Constructor - creation of new Subject Object with param's.
     * @param id - Unique id of the Subject
     * @param name - name of the Subject
     */
    public Subject(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    /**
     * Constructor - creation of new Subject Object with param's.
     * @param id - Unique id of the Subject
     * @param name - name of the Subject
     * @param teacherName - name of the Teacher Name
     */
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
