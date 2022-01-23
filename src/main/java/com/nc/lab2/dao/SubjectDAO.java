package com.nc.lab2.dao;


import com.nc.lab2.mapper.FacultyMapper;
import com.nc.lab2.mapper.GroupMapper;
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


    //    ------------------------------------------------- Не реализованные методы

    public List<Subject> getAllSubject() {
        List<Subject> subjectList = null;
//        String sql =  "SELECT * FROM ST_GROUP LEFT JOIN STUDENTS ON ST_ID = GR_HEAD_ID LEFT JOIN FACULTYS  on FAC_ID = GR_FAC_ID";
//        Object[] params = new Object[] {};
//        GroupMapper mapper = new GroupMapper() {
//            @Override
//            public Group mapRow(ResultSet resultSet, int rowNum) throws SQLException {
//                Group group = null;
//                int group_id = resultSet.getInt("GR_ID");
//                String group_name = resultSet.getString("GR_NAME");
//                String group_fac = resultSet.getString("FAC_NAME");
//                String head_name =resultSet.getString("ST_NAME"); // tab STUDENTS Name
//                group = new Group(group_id, group_name, group_fac ,head_name);
//                return group;
//            }
//        };
//
//        groupList = this.getJdbcTemplate().query(sql, params, mapper);
        return subjectList;
    }

    public void addSubject (Subject subject) {
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
