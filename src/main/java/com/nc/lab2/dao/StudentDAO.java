package com.nc.lab2.dao;


import com.nc.lab2.mapper.GroupMapper;
import com.nc.lab2.mapper.StudentMapper;
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

/** Class for Data Access Object for Students. */
@Repository
@Transactional
public class StudentDAO extends JdbcDaoSupport {


    /** SQL query for GET_ALL Students */
    private final String SQL_GET_ALL = "SELECT STUDENTS.ST_ID, STUDENTS.ST_NAME as ST_NAME1, GR_NAME, ST1.ST_NAME as ST_NAME2 FROM STUDENTS LEFT JOIN ST_GROUP on ST_GROUP.GR_ID = STUDENTS.ST_GR_ID left join STUDENTS ST1  on STUDENTS.ST_TEAMMATE_ID= ST1.ST_ID";

    /** SQL query for FIND Student */
    private final String SQL_FIND = "SELECT ST_ID, ST_NAME, ST_GR_ID, ST_TEAMMATE_ID FROM STUDENTS where ST_NAME = ? ";

    /** SQL query for list of possible teammate for Student */
    private final String SQL_SAVE_UP = "SELECT * FROM STUDENTS WHERE ST_NAME = ?";

    /** SQL query for SAVE Student */
    private final String SQL_SAVE = "UPDATE STUDENTS set ST_NAME = ?, ST_GR_ID = ?, ST_TEAMMATE_ID = ? WHERE ST_ID = ?";

    /** SQL query for check that this student is head of Group */
    private final String SQL_REMOVE_UP = "SELECT * FROM ST_GROUP WHERE GR_HEAD_ID = ?";

    /** SQL query for REMOVE Student */
    private final String SQL_REMOVE = "DELETE FROM STUDENTS WHERE ST_ID = ?";


    /** SQL query for ADD Student */
    private final String SQL_ADD = "INSERT INTO STUDENTS (ST_NAME, ST_GR_ID, ST_TEAMMATE_ID) VALUES (?,?,?)";

    /** SQL query for check available Groups for Student */
    private final String SQL_GET_AWL = "SELECT GR_ID, GR_NAME FROM ST_GROUP ";


    /** INFO message */
    private final String INFO_NO_STUD = "No Students with this name";

    /** INFO message */
    private final String INFO_ADD = "Student Added";

    /** INFO message */
    private final String INFO_REMOVE = "Student Deleted";

    /** INFO message */
    private final String INFO_NOT_REMOVE = "Student is Group Head, Edit Group and choose other Head";

    /** INFO message */
    private final String INFO_EDIT = "Student Edited";

    /** INFO message */
    private final String LOG_SQL_ERROR = "Some problem with Student SQL request";

    /** Constructor for StudentDAO Class */
    @Autowired
    public StudentDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    /**
     * Method that get from database list of all "Students".
     * @return List of all Students.
     */
    public  List<Student> getAllStudents() {
        try {
            List<Student> studentList;
            Object[] params = new Object[]{};
            StudentMapper mapper = new StudentMapper() {
                @Override
                public Student mapRow(ResultSet resultSet, int rowNum) {
                    try {
                        Student student;
                        int student_id = resultSet.getInt("ST_ID");
                        String student_name = resultSet.getString("ST_NAME1");
                        String student_group_name = resultSet.getString("GR_NAME");
                        String student_groupTeamMate = resultSet.getString("ST_NAME2");
                        student = new Student(student_id, student_name, student_group_name, student_groupTeamMate); // check constructor
                        return student;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        log.warn("Some problem with database mapper, custom mapper StudentDao");
                        return null;
                    }
                }
            };
            studentList = this.getJdbcTemplate().query(SQL_GET_ALL, params, mapper);
            return studentList;
        } catch (Exception e) {
            log.warn(LOG_SQL_ERROR);
            return null;
        }
    }

    /**
     * Method that add to database Student.
     * @param student Student object with parameters for add to database
     * @return INFO message.
     */
    public String addStudent(Student student) {
        try {
            String infoMessage;
            List<Student> TeamMate;
            Object[] params;
            if (!student.getTeamMate_Name().equals("")) {
                params = new Object[]{student.getTeamMate_Name()};
                StudentMapper mapper = new StudentMapper();
                TeamMate = this.getJdbcTemplate().query(SQL_SAVE_UP, params, mapper);
                if (TeamMate.isEmpty()) {
                    infoMessage = INFO_NO_STUD;
                } else {
                    params = new Object[]{student.getName(), student.getGroupId(), TeamMate.get(0).getId()};
                    this.getJdbcTemplate().update(SQL_ADD, params);
                    infoMessage = INFO_ADD;
                }
            } else {
                if (student.getGroupId() == 0) {
                    params = new Object[]{student.getName(), null, student.getGroupTeamMateId()};
                } else {
                    params = new Object[]{student.getName(), student.getGroupId(), student.getGroupTeamMateId()};
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
     * Method that remove Student from database.
     * @param id unique identifier of Student for remove
     * @return INFO message.
     */
    public String removeStudent(int id) {
        try {
            List<Group> studentList; // Group
            String infoMessage;
            Object[] params = new Object[]{id};
            GroupMapper mapper = new GroupMapper();
            studentList = this.getJdbcTemplate().query(SQL_REMOVE_UP, params, mapper);
            if (studentList.isEmpty()) {
                this.getJdbcTemplate().update(SQL_REMOVE, id);
                infoMessage = INFO_REMOVE;
            } else {
                infoMessage = INFO_NOT_REMOVE;
            }
            return infoMessage;
        } catch (Exception e) {
            log.warn(LOG_SQL_ERROR);
            return LOG_SQL_ERROR;
        }

    }

    /**
     * Method that save edited information about Student in database.
     * @param student object with edited parameters for save.
     * @return INFO message.
     */
    public String saveStudent(Student student) {
        try {
            String infoMessage;
            List<Student> TeamMate;
            Object[] params;
            if (!student.getTeamMate_Name().equals("")) {
                params = new Object[]{student.getTeamMate_Name()};
                StudentMapper mapper = new StudentMapper();
                TeamMate = this.getJdbcTemplate().query(SQL_SAVE_UP, params, mapper);
                if (TeamMate.isEmpty()) {
                    infoMessage = INFO_NO_STUD;
                } else {
                    params = new Object[]{student.getName(),
                            student.getGroupId() == 0 ? null : student.getGroupId(), TeamMate.get(0).getId(), student.getId()};
                    this.getJdbcTemplate().update(SQL_SAVE, params);
                    infoMessage = INFO_ADD;
                }
            } else {
                if (student.getGroupId() == 0) {
                    params = new Object[]{student.getName(),
                            null, student.getGroupTeamMateId() == 0 ? null : student.getGroupTeamMateId(), student.getId()};
                } else {
                    params = new Object[]{student.getName(), student.getGroupId(),
                            student.getGroupTeamMateId() == 0 ? null : student.getGroupTeamMateId(), student.getId()};
                }
                this.getJdbcTemplate().update(SQL_SAVE, params);
                infoMessage = INFO_ADD;
            }
            return infoMessage;
        } catch (Exception e) {
            log.warn(LOG_SQL_ERROR);
            return LOG_SQL_ERROR;
        }
    }

    /**
     * Method that get list of available Group for Student from database.
     * @return LIST of Groups.
     */
    public List<Group> getAwailGroup() {
        try {
            List<Group> groupList;
            Object[] params = new Object[]{};
            GroupMapper mapper = new GroupMapper() {
                @Override
                public Group mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                    Group group;
                    int group_id = resultSet.getInt("GR_ID");
                    String group_name = resultSet.getString("GR_NAME");
                    group = new Group(group_id, group_name);
                    return group;
                }
            };
            groupList = this.getJdbcTemplate().query(SQL_GET_AWL, params, mapper);
            return groupList;
        } catch (Exception e) {
            log.warn(LOG_SQL_ERROR);
            return null;
        }
    }

    /**
     * Method that find Student from database.
     * @return LIST of Students.
     */
    public List<Student> findStudentAccount(String name) {
        try {
            List<Student> studentList;
            Object[] params = new Object[]{name};
            StudentMapper mapper = new StudentMapper();
            studentList = this.getJdbcTemplate().query(SQL_FIND, params, mapper);
            return studentList;
        } catch (Exception e) {
            log.warn(LOG_SQL_ERROR);
        return null;
        }
    }
}
