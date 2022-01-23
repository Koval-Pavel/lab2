package com.nc.lab2.mapper;

import com.nc.lab2.model.Faculty;
import com.nc.lab2.model.Group;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class FacultyMapper implements RowMapper<Faculty> {

    @Override
    public Faculty mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Faculty faculty = null;
        int faculty_id = resultSet.getInt("FAC_ID");
        String faculty_name = resultSet.getString("FAC_NAME");
        String faculty_head_name = resultSet.getString("FAC_HEAD_NAME");
        faculty = new Faculty(faculty_id, faculty_name, faculty_head_name);
        return faculty;
    }
}
