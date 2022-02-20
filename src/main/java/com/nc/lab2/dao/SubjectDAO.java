package com.nc.lab2.dao;


import com.nc.lab2.mapper.SubjectMapper;
import com.nc.lab2.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

import static com.nc.lab2.Lab2Application.log;

/** Class for Data Access Object for Subject. */
@Repository
@Transactional
public class SubjectDAO extends JdbcDaoSupport {

    /** SQL query for GET_ALL Subjects */
    private final String SQL_GET_ALL = "SELECT * FROM STUDENT_LIST.SUBJECTS";

    /** SQL query for ADD new Subject */
    private final String SQL_ADD = "INSERT INTO STUDENT_LIST.SUBJECTS (\"SUB_NAME\", \"SUB_TEACHER_NAME\") VALUES (?, ?)";

    /** SQL query for REMOVE Subject */
    private final String SQL_REMOVE = "DELETE FROM STUDENT_LIST.SUBJECTS WHERE \"SUB_ID\" = ?";

    /** SQL query for SAVE Group */
    private final String SQL_SAVE = "UPDATE STUDENT_LIST.SUBJECTS set \"SUB_NAME\" = ?, \"SUB_TEACHER_NAME\" = ? where \"SUB_ID\" = ?";


    /** INFO message */
    private final String INFO_REMOVE = "Subject Deleted";

    /** INFO message */
    private final String INFO_ADD = "Subject Added";

    /** INFO message */
    private final String INFO_EDIT = "Subject Edited";

    /** INFO message */
    private final String LOG_SQL_ERROR = "Some problem with Subject SQL request";

    /** Constructor for GroupDAO Class */
    @Autowired
    public SubjectDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    /**
     * Method that get from database list of all "Subjects".
     * @return List of all Subjects.
     */
    public List<Subject> getAllSubject() {
        try {
            List<Subject> subjectList = null;
            Object[] params = new Object[]{};
            SubjectMapper mapper = new SubjectMapper();
            subjectList = this.getJdbcTemplate().query(SQL_GET_ALL, params, mapper);
            return subjectList;
        } catch (Exception e) {
            log.warn(LOG_SQL_ERROR);
            return null;
        }
    }

    /**
     * Method that add to database Subject.
     * @param subject Subject object with parameters for add to database
     * @return INFO message.
     */
    public String addSubject (Subject subject) {
        try {
            Object[] params = new Object[]{subject.getName(), subject.getTeacherName()};
            this.getJdbcTemplate().update(SQL_ADD, params);
            return INFO_ADD;
        } catch (Exception e) {
            log.warn(LOG_SQL_ERROR);
            return LOG_SQL_ERROR;
        }
    }

    /**
     * Method that remove Subject from database.
     * @param id unique identifier of Subject for remove
     * @return INFO message.
     */
    public String removeSubject(int id) {
        try {
            this.getJdbcTemplate().update(SQL_REMOVE, id);
            return INFO_REMOVE;
        } catch (Exception e) {
            log.warn(LOG_SQL_ERROR);
            return LOG_SQL_ERROR;
        }
    }

    /**
     * Method that save edited information about Subject in database.
     * @param subject object with edited parameters for save.
     * @return INFO message.
     */
    public String saveSubject(Subject subject) {
        try {
            Object[] params = new Object[]{subject.getName(), subject.getTeacherName(), subject.getId()};
            this.getJdbcTemplate().update(SQL_SAVE, params);
            return INFO_EDIT;
        } catch (Exception e) {
            log.warn(LOG_SQL_ERROR);
            return LOG_SQL_ERROR;
        }

    }

}
