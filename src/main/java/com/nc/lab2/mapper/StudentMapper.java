package com.nc.lab2.mapper;

import com.nc.lab2.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Student student = null;
        int student_id = resultSet.getInt("ST_ID");
        String student_name = resultSet.getString("ST_NAME");
        int student_group_id = resultSet.getInt("ST_GR_ID");
        int student_groupTeamLead_id = resultSet.getInt("ST_TEAMMATE_ID");
        student = new Student(student_id,student_name,student_group_id,student_groupTeamLead_id);
        return student;
    }
}
