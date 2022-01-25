package com.nc.lab2.model;

import java.util.HashMap;
import java.util.Objects;

public class Student {
    private int id;
    private String name;
    private int groupId;
    private int groupTeamMateId;
    private String GroupName;
    private String TeamMate_Name;
    private String infoMessage;
    private HashMap<String, Double > subjectListWithMark;
    private int teamLeader;



    public Student() {
        super();
    }

    public Student(int id, String name, int groupId, int groupTeamMateId, int teamLeader) {
        this.id = id;
        this.name = name;
        this.groupId = groupId;
        this.groupTeamMateId = groupTeamMateId;
        this.teamLeader = teamLeader;
    }

    public Student(int id, String name,  String groupName, int groupTeamMateId) {
        this.id = id;
        this.name = name;
        this.groupTeamMateId = groupTeamMateId;
        GroupName = groupName;
    }

    public Student(int id, String name, String groupName, String teamMate_Name) {
        this.id = id;
        this.name = name;
        GroupName = groupName;
        TeamMate_Name = teamMate_Name;
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student(int id, String name, int groupId, int groupTeamMateId, String groupName, String teamMate_Name, String infoMessage, HashMap<String, Double> subjectListWithMark, int teamLeader) {
        this.id = id;
        this.name = name;
        this.groupId = groupId;
        this.groupTeamMateId = groupTeamMateId;
        GroupName = groupName;
        TeamMate_Name = teamMate_Name;
        this.infoMessage = infoMessage;
        this.subjectListWithMark = subjectListWithMark;
        this.teamLeader = teamLeader;
    }

    public Student(int id, String name, String groupName, String TeamMate_Name, int teamLeader) {
        this.id = id;
        this.name = name;
        this.TeamMate_Name = TeamMate_Name;
        GroupName = groupName;
        this.teamLeader = teamLeader;
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

    public int getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(int teamLeader) {
        this.teamLeader = teamLeader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && groupId == student.groupId && groupTeamMateId == student.groupTeamMateId && Objects.equals(name, student.name) && Objects.equals(GroupName, student.GroupName) && Objects.equals(TeamMate_Name, student.TeamMate_Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, groupId, groupTeamMateId, GroupName, TeamMate_Name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", groupId=" + groupId +
                ", groupTeamLeadId=" + groupTeamMateId +
                ", GroupName='" + GroupName + '\'' +
                ", TeamMate_Name='" + TeamMate_Name + '\'' +
                '}';
    }
}