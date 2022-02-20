package com.nc.lab2.dao;


import com.nc.lab2.mapper.MarkMapper;
import com.nc.lab2.mapper.SubjectMapper;
import com.nc.lab2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import static com.nc.lab2.Lab2Application.log;

/** Class for Data Access Object for Faculty. */
@Repository
@Transactional
public class MarkDAO extends JdbcDaoSupport {

    /** SQL query for GET_ALL Mark for selected Student */
    private final String SQL_GET_ALL = "SELECT * FROM STUDENT_LIST.MARKS, STUDENT_LIST.STUDENTS, STUDENT_LIST.SUBJECTS where \"MARK_ST_ID\" = STUDENTS.\"ST_ID\" and \"MARK_SUB_ID\" = SUBJECTS.\"SUB_ID\" and \"MARK_ST_ID\" = ? and  \"MARK_SUB_ID\" = ?";

    /** SQL query for check available subjects */
    private final String SQL_AWAIL_SUB = "SELECT \"SUB_ID\", \"SUB_NAME\" FROM STUDENT_LIST.SUBJECTS";

    /** SQL query for add mark to subject */
    private final String SQL_ADD_UP = "INSERT INTO STUDENT_LIST.MARKS (\"MARK_ST_ID\", \"MARK_SUB_ID\", \"MARK_MARK\", \"MARK_DATE\") VALUES (?,?,?,?)";

    /** SQL query for REMOVE Mark */
    private final String SQL_REMOVE = "DELETE FROM STUDENT_LIST.MARKS WHERE \"MARK_ID\" = ?";


    /** INFO message */
    private final String INFO_ADD = "Mark Added";

    /** INFO message */
    private final String INFO_REMOVE = "Mark Deleted";

    /** INFO message */
    private final String INFO_INV = "Invalid date format, please try again";

    /** INFO message */
    private final String LOG_SQL_ERROR = "Some problem with Mark SQL request";

    /** Constructor for MarkDAO Class */
    @Autowired
    public MarkDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    /** Field with formatter for parsing time */
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** Field with student id for INFO */
    private int studIdForInfo;


    /**
     * Method that get from database list of all "Marks".
     * @return List of all Marks.
     */
    public List<Mark> getAllMark(int studId, int subjectId) {
        try {
            studIdForInfo = studId;
            Object[] params = new Object[]{studId, subjectId};
            MarkMapper mapper = new MarkMapper();
            return this.getJdbcTemplate().query(SQL_GET_ALL, params, mapper);
        } catch (Exception e) {
            log.warn(LOG_SQL_ERROR);
            return null;
        }
    }

    /**
     * Method that add to database Mark.
     * @param mark Markd object with parameters for add to database
     * @return INFO message.
     */
    public String addMark (Mark mark) {
        try {
            String InfoMessage;
            try {
                LocalDate.parse(mark.getDate(), formatter);
                Object[] params = new Object[]{mark.getStudentId(), mark.getSubjectId(), mark.getValue(), mark.getDate()};
                this.getJdbcTemplate().update(SQL_ADD_UP, params);
                InfoMessage = INFO_ADD;
            } catch (DateTimeParseException ex) {
                InfoMessage = INFO_INV;
            }
            return InfoMessage;
        } catch (Exception e) {
            log.warn(LOG_SQL_ERROR);
            return LOG_SQL_ERROR;
        }
    }

    /**
     * Method that remove Mark from database.
     * @param id unique identifier of Mark for remove
     * @return INFO message.
     */
    public String removeMark(int id) {
        try {
            Object[] params = new Object[]{id};
            this.getJdbcTemplate().update(SQL_REMOVE, params);
            return INFO_REMOVE + studIdForInfo;
        } catch (Exception e) {
            log.warn(LOG_SQL_ERROR);
            return LOG_SQL_ERROR;
        }
    }

    /**
     * Method that get list of available Subject from database.
     * @return LIST of Subjects.
     */
    public List<Subject> getAwailSubject() {
        try {
            Object[] params = new Object[]{};
            SubjectMapper mapper = new SubjectMapper() {
                @Override
                public Subject mapRow(ResultSet resultSet, int rowNum) {
                    try {
                        int subject_id = resultSet.getInt("SUB_ID");
                        String subject_name = resultSet.getString("SUB_NAME");
                        return new Subject(subject_id, subject_name);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        log.warn("Some problem with database mapper, custom mapper MarkDao");
                        return null;
                    }
                }

            };
            return this.getJdbcTemplate().query(SQL_AWAIL_SUB, params, mapper);
        } catch (Exception e) {
            log.warn(LOG_SQL_ERROR);
            return null;
        }
    }
}
