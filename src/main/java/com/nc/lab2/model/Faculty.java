package com.nc.lab2.model;

import java.util.Objects;

/**
 * Class that describe Faculty entity.
 */
public class Faculty {

    /** Unique id of Faculty*/
    private int id;

    /** Name of Faculty*/
    private String name;

    /** Head Name of each Faculty*/
    private String HeadName;

    public Faculty() {
        super();
    }

    /**
     * Constructor - creation of new Faculty Object with param's.
     * @param id - unique id of Faculty
     * @param name - Name of Faculty.
     */
    public Faculty(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Constructor - creation of new Faculty Object with param's.
     * @param id - unique id of Faculty
     * @param name - Name of Faculty.
     * @param headName - Name of the Person who is group head
     */
    public Faculty(int id, String name, String headName) {
        this.id = id;
        this.name = name;
        HeadName = headName;
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

    public String getHeadName() {
        return HeadName;
    }

    public void setHeadName(String headName) {
        HeadName = headName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return id == faculty.id && Objects.equals(name, faculty.name) && Objects.equals(HeadName, faculty.HeadName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, HeadName);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", HeadName='" + HeadName + '\'' +
                '}';
    }


}
