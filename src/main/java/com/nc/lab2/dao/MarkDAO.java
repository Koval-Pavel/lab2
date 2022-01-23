package com.nc.lab2.dao;


import com.nc.lab2.mapper.FacultyMapper;
import com.nc.lab2.mapper.GroupMapper;
import com.nc.lab2.model.Faculty;
import com.nc.lab2.model.Group;
import com.nc.lab2.model.Mark;
import com.nc.lab2.model.Student;
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
public class MarkDAO extends JdbcDaoSupport {

    @Autowired
    public MarkDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    //    ------------------------------------------------- Не реализованные методы

    public void addMark (Mark mark) {
//        String sqlUpdate = null;
//        if (group.getFacultyId() == 0){
//            sqlUpdate = "INSERT INTO ST_GROUP ( GR_NAME, GR_FAC_ID, GR_HEAD_ID) VALUES " +
//                    "('" + group.getName() + "', " + null + ", " + group.getHeadId() + ")";
//        } else {
//
//            sqlUpdate = "INSERT INTO ST_GROUP (GR_NAME, GR_FAC_ID, GR_HEAD_ID) VALUES " +
//                    "('" + group.getName() + "', " + group.getFacultyId() + ", " + null + ")"; // Временно добавляю нулл а вообще group.getHeadId()
//        }
//        this.getJdbcTemplate().update(sqlUpdate);
    }


}
