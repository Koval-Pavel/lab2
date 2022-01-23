package com.nc.lab2.model;

import java.util.Objects;

public class Group {
    private int id;
    private String name;
    private int FacultyId;
    private int headId;
    private String HeadName;
    private String FacultyName;


    public Group() {super();}

    public Group(int id, String name, int facultyId, int headId) {
        this.id = id;
        this.name = name;
        FacultyId = facultyId;
        this.headId = headId;
    }

    public Group(int id, String name, String facultyName, String headName) {
        this.id = id;
        this.name = name;
        HeadName = headName;
        FacultyName = facultyName;
    }

    public Group(int id, String name,  int facultyId) {
        this.id = id;
        this.name = name;
        this.FacultyId = facultyId;
    }

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Group(int id, String name, int facultyId, String headName) {
        this.id = id;
        this.name = name;
        FacultyId = facultyId;
        HeadName = headName;
    }

    public Group(int id, String name, String headName) {
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

    public int getFacultyId() {
        return FacultyId;
    }

    public void setFacultyId(int facultyId) {
        FacultyId = facultyId;
    }

    public int getHeadId() {
        return headId;
    }

    public void setHeadId(int headId) {
        this.headId = headId;
    }

    public String getHeadName() {
        return HeadName;
    }

    public void setHeadName(String headName) {
        HeadName = headName;
    }

    public String getFacultyName() {
        return FacultyName;
    }

    public void setFacultyName(String facultyName) {
        FacultyName = facultyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return id == group.id && FacultyId == group.FacultyId && headId == group.headId && Objects.equals(name, group.name) && Objects.equals(HeadName, group.HeadName) && Objects.equals(FacultyName, group.FacultyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, FacultyId, headId, HeadName, FacultyName);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", FacultyId=" + FacultyId +
                ", headId=" + headId +
                ", HeadName='" + HeadName + '\'' +
                ", FacultyName='" + FacultyName + '\'' +
                '}';
    }
}
