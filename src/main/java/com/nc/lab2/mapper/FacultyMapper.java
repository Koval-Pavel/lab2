package com.nc.lab2.mapper;

import com.nc.lab2.model.Faculty;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.nc.lab2.Lab2Application.log;

/**
 * Class for Faculty mapper.
 */
public class FacultyMapper implements RowMapper<Faculty> {

    /**
     * Implementation of method that realize matching rows from database
     * with parameters of corresponding Object.
     * @param resultSet set of results, query in the database
     * @param rowNum number of Row
     * @return new Object of Group with param's from database
     */
    @Override
    public Faculty mapRow(ResultSet resultSet, int rowNum) {

        try {
            int faculty_id = resultSet.getInt("FAC_ID");
            String faculty_name = resultSet.getString("FAC_NAME");
            String faculty_head_name = resultSet.getString("FAC_HEAD_NAME");
            return new Faculty(faculty_id, faculty_name, faculty_head_name);
        } catch (SQLException e) {
            e.printStackTrace();
            log.warn("Some problem with database mapper (Faculty)");
            return null;
        }
    }
}
