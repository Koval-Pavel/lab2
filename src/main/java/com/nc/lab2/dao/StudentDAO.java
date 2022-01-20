package com.nc.lab2.dao;


import com.nc.lab2.mapper.StudentMapper;
import com.nc.lab2.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class StudentDAO extends JdbcDaoSupport {

    @Autowired
    public StudentDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Student> getAllStudents() {
        List<Student> studentList;
        String sql = "SELECT * FROM STUDENTS33";
        Object[] params = new Object[] {};
        StudentMapper mapper = new StudentMapper();
        studentList = this.getJdbcTemplate().query(sql, params, mapper);
        return studentList;
    }


    //На данній моммент реализован только поиск по имени
    public List<Student> findStudentAccount(String name) {
        List<Student> studentList;
        String sql = "SELECT STUDE33_ID, STUDE33_NAME, STUDE33_AGE, STUDE33_GROUP FROM STUDENTS33" + " where STUDE33_NAME = ? ";
        Object[] params = new Object[] { name };
        StudentMapper mapper = new StudentMapper();
        try {
            studentList = this.getJdbcTemplate().query(sql, params, mapper);
            return studentList;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void saveStudent(Student student) {
        String sqlUpdate = "UPDATE STUDENTS33 set STUDE33_NAME = ?, STUDE33_AGE = ?, STUDE33_GROUP = ? where STUDE33_ID = ?" ;
        this.getJdbcTemplate().update(sqlUpdate, student.getName(), student.getAge(), student.getGroup(), student.getId());
    }
    public void removeStudent(Student student) {
        String sqlUpdate = "DELETE FROM STUDENTS33 WHERE STUDE33_ID = ?" ;
        this.getJdbcTemplate().update(sqlUpdate,  student.getId());
    }
    public void addStudent(Student student) {
        String sqlUpdate = "INSERT INTO STUDENTS33 (STUDE33_ID, STUDE33_NAME, STUDE33_AGE, STUDE33_GROUP) VALUES " +
                "(" + student.getId() + ", '" + student.getName() + "', "+ student.getAge() + ", '"+ student.getGroup() + "')";
        this.getJdbcTemplate().update(sqlUpdate);
    }

    public Student findMaxId () {
        String sql = "SELECT STUDE33_ID, STUDE33_NAME, STUDE33_AGE, STUDE33_GROUP FROM STUDENTS33" + " where STUDE33_ID = (SELECT MAX(STUDE33_ID) FROM STUDENTS33) ";
        Object[] params = new Object[]{};
        StudentMapper mapper = new StudentMapper();
        try {
            Student student = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return student;
        } catch (EmptyResultDataAccessException e) {
            return null;

        }
    }

}
