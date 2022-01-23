package com.nc.lab2.model;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Student {
    private int id;
    private String name;
    private int groupId;
    private int groupTeamLeadId;
    private String GroupName;
    private String TeamMate_Name;
    private String infoMessage;
    private HashMap<String, Double > subjectListWithMark;



    public Student() {
        super();
    }

    public Student(int id, String name, int groupId, int groupTeamLeadId) {
        this.id = id;
        this.name = name;
        this.groupId = groupId;
        this.groupTeamLeadId = groupTeamLeadId;
    }

    public Student(int id, String name,  String groupName, int groupTeamLeadId) {
        this.id = id;
        this.name = name;
        this.groupTeamLeadId = groupTeamLeadId;
        GroupName = groupName;
    }

    public Student(int id, String name, String groupName, String teamMate_Name) {
        this.id = id;
        this.name = name;
        GroupName = groupName;
        TeamMate_Name = teamMate_Name;
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

    public int getGroupTeamLeadId() {
        return groupTeamLeadId;
    }

    public void setGroupTeamLeadId(int groupTeamLeadId) {
        this.groupTeamLeadId = groupTeamLeadId;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public String getTeamMate_Name() {
        return TeamMate_Name;
    }

    public void setTeamMate_Name(String teamMate_Name) {
        TeamMate_Name = teamMate_Name;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && groupId == student.groupId && groupTeamLeadId == student.groupTeamLeadId && Objects.equals(name, student.name) && Objects.equals(GroupName, student.GroupName) && Objects.equals(TeamMate_Name, student.TeamMate_Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, groupId, groupTeamLeadId, GroupName, TeamMate_Name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", groupId=" + groupId +
                ", groupTeamLeadId=" + groupTeamLeadId +
                ", GroupName='" + GroupName + '\'' +
                ", TeamMate_Name='" + TeamMate_Name + '\'' +
                '}';
    }
}