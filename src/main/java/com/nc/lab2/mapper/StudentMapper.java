package com.nc.lab2.mapper;

import com.nc.lab2.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.nc.lab2.Lab2Application.log;

/**
 * Class for Student mapper.
 */
public class StudentMapper implements RowMapper<Student> {

    /** Logger instrument (slf4j) */
//    public static Logger log = LogManager.getLogger(StudentMapper.class);

    /**
     * Implementation of method that realize matching rows from database
     * with parameters of corresponding Object.
     * @param resultSet set of results, query in the database
     * @param rowNum number of Row
     * @return new Object of Student with param's from database
     */
    @Override
    public Student mapRow(ResultSet resultSet, int rowNum) {
        try {
            int student_id = resultSet.getInt("ST_ID");
            String student_name = resultSet.getString("ST_NAME");
            int student_group_id = resultSet.getInt("ST_GR_ID");
            int student_groupTeamMate_id = resultSet.getInt("ST_TEAMMATE_ID");
            return new Student(student_id, student_name, student_group_id, student_groupTeamMate_id);
        }  catch (SQLException e) {
            e.printStackTrace();
            log.warn("Some problem with database mapper (Student)");
            return null;
        }
    }
}
