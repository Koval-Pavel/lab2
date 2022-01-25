package com.nc.lab2.dao;


import com.nc.lab2.mapper.FacultyMapper;
import com.nc.lab2.model.Faculty;
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
            sqlUpdate = "INSERT INTO FACULTYS (FAC_NAME, FAC_HEAD_NAME) VALUES " +
                    "('" + faculty.getName() + "', '" + faculty.getHeadName()+ "')";
        this.getJdbcTemplate().update(sqlUpdate);
    }


}
