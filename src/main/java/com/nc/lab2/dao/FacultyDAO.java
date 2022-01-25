package com.nc.lab2.dao;


import com.nc.lab2.mapper.FacultyMapper;
import com.nc.lab2.model.Faculty;
import com.nc.lab2.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class FacultyDAO extends JdbcDaoSupport {

    @Autowired
    public FacultyDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Faculty> getAllFaculty() {
        List<Faculty> facultyList;
        String sql =  "SELECT * FROM FACULTYS";
        Object[] params = new Object[] {};
        FacultyMapper mapper = new FacultyMapper();
        facultyList = this.getJdbcTemplate().query(sql, params, mapper);
        return facultyList;
    }

    public void addFaculty (Faculty faculty) {
        String sqlUpdate;
            sqlUpdate = "INSERT INTO FACULTYS (FAC_NAME, FAC_HEAD_NAME) VALUES (?,?)";
        this.getJdbcTemplate().update(sqlUpdate,faculty.getName(), faculty.getHeadName() );
    }

    public String removeFaculty(int id) {
        String infoMessage;
        String sqlUpdate2 = " DELETE FROM FacultyS WHERE FAC_ID = ?" ;
        this.getJdbcTemplate().update(sqlUpdate2,  id);
        infoMessage = "Deleted";
        return infoMessage;
    }

    public String saveFaculty(Faculty faculty) {
        String infoMessage = null;
        String sqlUpdate;
        sqlUpdate = "UPDATE FacultyS set FAC_NAME = ?, FAC_HEAD_NAME = ? where FAC_ID = ?" ;
        this.getJdbcTemplate().update(sqlUpdate, faculty.getName(), faculty.getHeadName(), faculty.getId());
        return infoMessage;
    }


}
