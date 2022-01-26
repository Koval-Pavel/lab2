package com.nc.lab2.dao;


import com.nc.lab2.mapper.FacultyMapper;
import com.nc.lab2.mapper.GroupMapper;
import com.nc.lab2.model.Faculty;
import com.nc.lab2.model.Group;
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
        List<Group> groupList; // Group
        String infoMessage;
        String sqlUpdate = " SELECT * FROM ST_GROUP WHERE GR_FAC_ID = ?";
        Object[] params = new Object[] { id };
        GroupMapper mapper = new GroupMapper();
        groupList = this.getJdbcTemplate().query(sqlUpdate, params,  mapper);
        if (groupList.isEmpty()) {
            String sqlUpdate1 =  "DELETE FROM FacultyS WHERE FAC_ID = ?" ;
            this.getJdbcTemplate().update(sqlUpdate1,  params);
            infoMessage = "Deleted";
        } else {
            infoMessage = "Faculty is Main for some Group, change group faculty for other";
        }
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
