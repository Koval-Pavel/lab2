package com.nc.lab2.dao;


import com.nc.lab2.mapper.FacultyMapper;
import com.nc.lab2.mapper.GroupMapper;
import com.nc.lab2.mapper.StudentMapper;
import com.nc.lab2.mapper.SubjectMapper;
import com.nc.lab2.model.Faculty;
import com.nc.lab2.model.Group;
import com.nc.lab2.model.Student;
import com.nc.lab2.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class SubjectDAO extends JdbcDaoSupport {

    @Autowired
    public SubjectDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Subject> getAllSubject() {
        List<Subject> subjectList = null;
        String sql =  "SELECT * FROM SUBJECTS";
        Object[] params = new Object[] {};
        SubjectMapper mapper = new SubjectMapper() ;
        subjectList = this.getJdbcTemplate().query(sql, params, mapper);
        return subjectList;
    }

    public void addSubject (Subject subject) {
        String sqlUpdate;
            sqlUpdate = "INSERT INTO SUBJECTS (SUB_NAME, SUB_TEACHER_NAME) VALUES (?, ?)";
        this.getJdbcTemplate().update(sqlUpdate, subject.getName(), subject.getTeacherName());
    }

    public String removeSubject(int id) {
        String infoMessage;
        String sqlUpdate2 = " DELETE FROM SUBJECTS WHERE SUB_ID = ?" ;
        this.getJdbcTemplate().update(sqlUpdate2,  id);
        infoMessage = "Deleted";
        return infoMessage;
    }

    public String saveSubject(Subject subject) {
        String infoMessage = null;
        String sqlUpdate;
            sqlUpdate = "UPDATE SUBJECTS set SUB_NAME = ?, SUB_TEACHER_NAME = ? where SUB_ID = ?" ;
                this.getJdbcTemplate().update(sqlUpdate, subject.getName(), subject.getTeacherName(), subject.getId());
        return infoMessage;
    }

}
