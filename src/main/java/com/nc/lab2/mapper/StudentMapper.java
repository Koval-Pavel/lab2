package com.nc.lab2.mapper;

import com.nc.lab2.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Student student = null;
        int student_id = resultSet.getInt("STUDE33_ID");
        String student_name = resultSet.getString("STUDE33_NAME");
        int student_age = resultSet.getInt("STUDE33_AGE");
        String student_group = resultSet.getString("STUDE33_GROUP");
        student = new Student(student_id,student_name,student_group,student_age);
        return student;
    }
}
