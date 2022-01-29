package com.nc.lab2.dao;


import com.nc.lab2.mapper.FacultyMapper;
import com.nc.lab2.mapper.GroupMapper;
import com.nc.lab2.model.Faculty;
import com.nc.lab2.model.Group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

import static com.nc.lab2.Lab2Application.log;


/** Class for Data Access Object for Faculty. */
@Repository
@Transactional
public class FacultyDAO extends JdbcDaoSupport {

    /** SQL query for GET_ALL Faculty */
    private final String SQL_GET_ALL = "SELECT * FROM FACULTYS";

    /** SQL query for ADD Faculty */
    private final String SQL_ADD = "INSERT INTO FACULTYS (FAC_NAME, FAC_HEAD_NAME) VALUES (?,?)";

    /** SQL query for get list of groups for Faculty */
    private final String SQL_REMOVE_UP = "SELECT * FROM ST_GROUP WHERE GR_FAC_ID = ?";

    /** SQL query for REMOVE Faculty */
    private final String SQL_REMOVE = "DELETE FROM FacultyS WHERE FAC_ID = ?";

    /** SQL query for SAVE Faculty */
    private final String SQL_SAVE = "UPDATE FacultyS set FAC_NAME = ?, FAC_HEAD_NAME = ? where FAC_ID = ?";

    /** INFO message */
    private final String INFO_DEL = "Faculty Deleted.";

    /** INFO message */
    private final String INFO_ADD = "Faculty Added.";

    /** INFO message */
    private final String INFO_EDIT = "Faculty Edited.";

    /** INFO message */
    private final String INFO_FAC_MAIN = "Faculty is Main for some Group, change group faculty for other";

    /** INFO message */
    private final String LOG_SQL_ERROR = "Some problem with Faculty SQL request";

    /** Constructor for FacultyDAO Class */
    @Autowired
    public FacultyDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    /**
     * Method that get from database list of all "Faculty's".
     * @return List of all Faculty's.
     */
    public List<Faculty> getAllFaculty() {
        try {
            List<Faculty> facultyList;
            Object[] params = new Object[]{};
            FacultyMapper mapper = new FacultyMapper();
            facultyList = this.getJdbcTemplate().query(SQL_GET_ALL, params, mapper);
            return facultyList;
        } catch (Exception e) {
            log.warn(LOG_SQL_ERROR);
            return null;
        }
    }

    /**
     * Method that add to database Faculty.
     * @param faculty Faculty object with parameters for add to database
     * @return INFO message.
     */
    public String addFaculty(Faculty faculty) {
        try {
            this.getJdbcTemplate().update(SQL_ADD, faculty.getName(), faculty.getHeadName());
            return INFO_ADD;
        } catch (Exception e) {
            log.warn(LOG_SQL_ERROR);
            return LOG_SQL_ERROR;
        }
    }

    /**
     * Method that remove Faculty from database.
     * @param id unique identifier of Faculty for remove
     * @return INFO message.
     */
    public String removeFaculty(int id) {
        try {
            List<Group> groupList;
            String infoMessage;
            Object[] params = new Object[]{id};
            GroupMapper mapper = new GroupMapper();
            groupList = this.getJdbcTemplate().query(SQL_REMOVE_UP, params, mapper);
            if (groupList.isEmpty()) {
                this.getJdbcTemplate().update(SQL_REMOVE, params);
                infoMessage = INFO_DEL;
                log.info(INFO_DEL);
            } else {
                infoMessage = INFO_FAC_MAIN;
            }
            return infoMessage;
        } catch (Exception e) {
            log.warn(LOG_SQL_ERROR);
            return LOG_SQL_ERROR;
        }
    }

    /**
     * Method that save edited information about Faculty in database.
     * @param faculty faculty object with edited parameters for save.
     * @return INFO message.
     */
    public String saveFaculty(Faculty faculty) {
        try {
            String infoMessage;
            this.getJdbcTemplate().update(SQL_SAVE, faculty.getName(), faculty.getHeadName(), faculty.getId());
            infoMessage = INFO_EDIT;
            return infoMessage;
        } catch (Exception e) {
            log.warn(LOG_SQL_ERROR);
            return LOG_SQL_ERROR;
        }
    }


}
