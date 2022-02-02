package com.nc.lab2.model;

import java.util.Objects;

public class Student {

    /** Unique id of Student*/
    private int id;

    /** name of the Student */
    private String name;

    /** Group Id of the Student*/
    private int groupId;

    /** Student Teammate ID*/
    private int groupTeamMateId;

    /** Group Name of the Student*/
    private String groupName;

    /** Student Teammate Name*/
    private String teamMate_Name;

    public Student() {
        super();
    }

    /**
     * Constructor - creation of new Student Object with param's.
     * @param id - Unique id of Student
     * @param name - name of the Student
     * @param groupId - Group Id of the Student
     * @param groupTeamMateId - Student Teammate ID
     */
    public Student(int id, String name, int groupId, int groupTeamMateId) {
        this.id = id;
        this.name = name;
        this.groupId = groupId;
        this.groupTeamMateId = groupTeamMateId;
    }

    /**
     * onstructor - creation of new Student Object with param's.
     * @param id - Unique id of Student
     * @param name - name of the Student
     * @param groupId- Group Id of the Student
     * @param groupTeamMateId- Student Teammate ID
     * @param teamMate_Name- Student Teammate Name
     */
    public Student(int id, String name, int groupId, int groupTeamMateId, String teamMate_Name) {
        this.id = id;
        this.name = name;
        this.groupId = groupId;
        this.groupTeamMateId = groupTeamMateId;
        this.teamMate_Name = teamMate_Name;
    }

    /**
     * Constructor - creation of new Student Object with param's.
     * @param id - Unique id of Student
     * @param name - name of the Student
     * @param groupName - Group Name of the Student
     * @param teamMate_Name - Student Teammate Name
     */
    public Student(int id, String name, String groupName, String teamMate_Name) {
        this.id = id;
        this.name = name;
        this.groupName = groupName;
        this.teamMate_Name = teamMate_Name;
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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getGroupTeamMateId() {
        return groupTeamMateId;
    }

    public void setGroupTeamMateId(int groupTeamMateId) {
        this.groupTeamMateId = groupTeamMateId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTeamMate_Name() {
        return teamMate_Name;
    }

    public void setTeamMate_Name(String teamMate_Name) {
        this.teamMate_Name = teamMate_Name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && groupId == student.groupId && groupTeamMateId == student.groupTeamMateId && Objects.equals(name, student.name) && Objects.equals(groupName, student.groupName) && Objects.equals(teamMate_Name, student.teamMate_Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, groupId, groupTeamMateId, groupName, teamMate_Name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", groupId=" + groupId +
                ", groupTeamMateId=" + groupTeamMateId +
                ", GroupName='" + groupName + '\'' +
                ", TeamMate_Name='" + teamMate_Name + '\'' +
                '}';
    }
}