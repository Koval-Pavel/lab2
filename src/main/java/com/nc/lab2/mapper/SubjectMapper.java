package com.nc.lab2.mapper;

import com.nc.lab2.model.Group;
import com.nc.lab2.model.Subject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SubjectMapper implements RowMapper<Subject> {

    @Override
    public Subject mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Subject subject;
        int subject_id = resultSet.getInt("SUB_ID");
        String subject_name = resultSet.getString("SUB_NAME");
        String subject_teacher_name = resultSet.getString("SUB_TEACHER_NAME");

        subject = new Subject(subject_id, subject_name, subject_teacher_name);
        return subject;
    }


}
