package com.nc.lab2.dao;


import com.nc.lab2.mapper.FacultyMapper;
import com.nc.lab2.mapper.GroupMapper;
import com.nc.lab2.mapper.StudentMapper;
import com.nc.lab2.model.Faculty;
import com.nc.lab2.model.Group;
import com.nc.lab2.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.nc.lab2.Lab2Application.log;


/** Class for Data Access Object for Group. */
@Repository
@Transactional
public class GroupDAO extends JdbcDaoSupport {

    /** SQL query for GET_ALL Groups */
    private final String SQL_GET_ALL = "SELECT * FROM ST_GROUP LEFT JOIN STUDENTS ON ST_ID = GR_HEAD_ID LEFT JOIN FACULTYS  on FAC_ID = GR_FAC_ID";

    /** SQL query for check available group head for ADD Group */
    private final String SQL_ADD_CHECK_AWAIL = "SELECT * FROM STUDENTS WHERE ST_NAME = ?";

    /** SQL query for ADD Group */
    private final String SQL_ADD = "INSERT INTO ST_GROUP ( GR_NAME, GR_FAC_ID, GR_HEAD_ID) VALUES (?,?,?)";

    /** SQL query for Get available Faculty */
    private final String SQL_GET_FAC = "SELECT FAC_ID, FAC_NAME FROM FACULTYS";

    /** SQL query for check group have a Head Student*/
    private final String SQL_REMOVE_UP = " UPDATE STUDENTS set ST_GR_ID = null WHERE ST_GR_ID = ?";

    /** SQL query for REMOVE Group */
    private final String SQL_REMOVE = "DELETE FROM ST_GROUP WHERE GR_ID = ?";

    /** SQL query for SAVE Group */
    private final String SQL_SAVE = "UPDATE ST_GROUP set GR_NAME = ?, GR_FAC_ID = ?, GR_HEAD_ID = ? WHERE GR_ID = ?";

    /** SQL query for get students list for selected group */
    private final String SQL_GET_STDNTS = "SELECT * FROM STUDENTS WHERE ST_GR_ID = ?";

    /** INFO message */
    private final String INFO_NO_STUD = "No Students with this name";

    /** INFO message */
    private final String INFO_ADD = "Group Added";

    /** INFO message */
    private final String INFO_REMOVE = "Group Deleted";

    /** INFO message */
    private final String INFO_EDIT = "Group Edited";

    /** INFO message */
    private final String LOG_SQL_ERROR = "Some problem with Group SQL request";

    /** Constructor for GroupDAO Class */
    @Autowired
    public GroupDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    /**
     * Method that get from database list of all "Groups".
     * @return List of all Groups.
     */
    public List<Group> getAllGroup() {
        try {
            Object[] params = new Object[]{};
            GroupMapper mapper = new GroupMapper() {
                @Override
                public Group mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                    Group group;
                    int group_id = resultSet.getInt("GR_ID");
                    String group_name = resultSet.getString("GR_NAME");
                    String group_fac = resultSet.getString("FAC_NAME");
                    String head_name = resultSet.getString("ST_NAME"); // tab STUDENTS Name
                    group = new Group(group_id, group_name, group_fac, head_name);
                    return group;
                }
            };
            return this.getJdbcTemplate().query(SQL_GET_ALL, params, mapper);
        } catch (Exception e) {
            log.warn(LOG_SQL_ERROR);
            return null;
        }
    }

    /**
     * Method that add to database Group.
     * @param group Group object with parameters for add to database
     * @return INFO message.
     */
    public String addGroup (Group group) {
        try {
            String infoMessage = null;
            Object[] params;
            List<Student> GroupHead;
            if (!group.getHeadName().equals("")) {
                params = new Object[]{group.getHeadName()};
                StudentMapper mapper = new StudentMapper();
                GroupHead = this.getJdbcTemplate().query(SQL_ADD_CHECK_AWAIL, params, mapper);
                if (GroupHead.isEmpty()) {
                    infoMessage = INFO_NO_STUD;
                } else {
                    this.getJdbcTemplate().update(SQL_ADD, group.getName(), group.getFacultyId(), GroupHead.get(0).getId());
                    infoMessage = INFO_ADD;
                }
            } else {
                if (group.getFacultyId() == 0) {
                    params = new Object[]{group.getName(), null, null};
                } else {
                    params = new Object[]{group.getName(), group.getFacultyId(), null};
                }
                this.getJdbcTemplate().update(SQL_ADD, params);
                infoMessage = INFO_ADD;
            }
            return infoMessage;
        } catch (Exception e) {
            log.warn(LOG_SQL_ERROR);
            return LOG_SQL_ERROR;
        }
    }

    /**
     * Method that remove Group from database.
     * @param id unique identifier of Group for remove
     * @return INFO message.
     */
    public String removeGroup(int id) {
        try {
            this.getJdbcTemplate().update(SQL_REMOVE_UP, id);
            this.getJdbcTemplate().update(SQL_REMOVE, id);
            return INFO_REMOVE;
        } catch (Exception e) {
            log.warn(LOG_SQL_ERROR);
            return LOG_SQL_ERROR;
        }

    }

    /**
     * Method that save edited information about Group in database.
     * @param group object with edited parameters for save.
     * @return INFO message.
     */
    public String saveGroup(Group group) {
        try {
            String infoMessage = null;
            Object[] params;
            List<Student> GroupHead;
            if (!group.getHeadName().equals("")) {
                params = new Object[]{group.getHeadName()};
                StudentMapper mapper = new StudentMapper();
                GroupHead = this.getJdbcTemplate().query(SQL_ADD_CHECK_AWAIL, params, mapper);
                if (GroupHead.isEmpty()) {
                    infoMessage = INFO_NO_STUD;
                } else {

                    params = new Object[]{group.getName(), group.getFacultyId() == 0 ? null : group.getFacultyId(),
                            GroupHead.get(0).getId(), group.getId()};
                    this.getJdbcTemplate().update(SQL_SAVE, params);
                    infoMessage = INFO_EDIT;
                }
            } else {
                if (group.getFacultyId() == 0) {
                    params = new Object[]{group.getName(), null, group.getHeadId() == 0 ? null : group.getHeadId(), group.getId()};
                } else {
                    params = new Object[]{group.getName(), group.getFacultyId(),
                            group.getHeadId() == 0 ? null : group.getHeadId(), group.getId()};
                }
                this.getJdbcTemplate().update(SQL_SAVE, params);
                infoMessage = INFO_EDIT;
            }
            return infoMessage;
        } catch (Exception e) {
            log.warn(LOG_SQL_ERROR);
            return LOG_SQL_ERROR;
        }
    }

    /**
     * Method that get list of available Faculty for Group from database.
     * @return LIST of Faculty's.
     */
    public List<Faculty> getAwailFaculty() {
        try {
            Object[] params = new Object[]{};
            FacultyMapper mapper = new FacultyMapper() {

                @Override
                public Faculty mapRow(ResultSet resultSet, int rowNum) {
                    try {
                        Faculty faculty;
                        int faculty_id = resultSet.getInt("FAC_ID");
                        String faculty_name = resultSet.getString("FAC_NAME");
                        faculty = new Faculty(faculty_id, faculty_name);
                        return faculty;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        log.warn("Some problem with database mapper, custom mapper GroupDAO");
                        return null;
                    }
                }
            };
            return this.getJdbcTemplate().query(SQL_GET_FAC, params, mapper);
        } catch (Exception e) {
            log.warn(LOG_SQL_ERROR);
            return null;
        }
    }

    /**
     * Method that get list of available Students of selected Group from database.
     * @param id unique Selected Group id.
     * @return LIST of Students.
     */
    public List<Student> getGroupStudents(int id) {
        try {
            List<Student> GroupStudentsList;
            Object[] params = new Object[]{id};
            StudentMapper mapper = new StudentMapper();
            GroupStudentsList = this.getJdbcTemplate().query(SQL_GET_STDNTS, params, mapper);
            return GroupStudentsList;
        } catch (Exception e) {
            log.warn(LOG_SQL_ERROR);
            return null;
        }
    }
}
