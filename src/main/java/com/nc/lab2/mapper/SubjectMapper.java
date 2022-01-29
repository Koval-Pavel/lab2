package com.nc.lab2.mapper;

import com.nc.lab2.model.Subject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.nc.lab2.Lab2Application.log;

/**
 * Class for Student mapper.
 */
public class SubjectMapper implements RowMapper<Subject> {

    /**
     * Implementation of method that realize matching rows from database
     * with parameters of corresponding Object.
     * @param resultSet set of results, query in the database
     * @param rowNum number of Row
     * @return new Object of Subject with param's from database
     */
    @Override
    public Subject mapRow(ResultSet resultSet, int rowNum) {
        try {
            int subject_id = resultSet.getInt("SUB_ID");
            String subject_name = resultSet.getString("SUB_NAME");
            String subject_teacher_name = resultSet.getString("SUB_TEACHER_NAME");
            return new Subject(subject_id, subject_name, subject_teacher_name);
        } catch (SQLException e) {
            e.printStackTrace();
            log.warn("Some problem with database mapper (Subject)");
            return null;
        }
    }


}
