package com.nc.lab2.dao;


import com.nc.lab2.mapper.FacultyMapper;
import com.nc.lab2.mapper.GroupMapper;
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

@Repository
@Transactional
public class GroupDAO extends JdbcDaoSupport {

    @Autowired
    public GroupDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Group> getAllGroup() {
        List<Group> groupList;
        String sql =  "SELECT * FROM ST_GROUP LEFT JOIN STUDENTS ON ST_ID = GR_HEAD_ID LEFT JOIN FACULTYS  on FAC_ID = GR_FAC_ID";
        Object[] params = new Object[] {};
        GroupMapper mapper = new GroupMapper() {
            @Override
            public Group mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Group group = null;
                int group_id = resultSet.getInt("GR_ID");
                String group_name = resultSet.getString("GR_NAME");
                String group_fac = resultSet.getString("FAC_NAME");
                String head_name =resultSet.getString("ST_NAME"); // tab STUDENTS Name
                group = new Group(group_id, group_name, group_fac ,head_name);
                return group;
            }
        };

        groupList = this.getJdbcTemplate().query(sql, params, mapper);
        return groupList;
    }

    public void addGroup (Group group) {
        String sqlUpdate = null;
        if (group.getFacultyId() == 0){
            sqlUpdate = "INSERT INTO ST_GROUP ( GR_NAME, GR_FAC_ID, GR_HEAD_ID) VALUES " +
                    "('" + group.getName() + "', " + null + ", " + group.getHeadId() + ")";
        } else {

            sqlUpdate = "INSERT INTO ST_GROUP (GR_NAME, GR_FAC_ID, GR_HEAD_ID) VALUES " +
                    "('" + group.getName() + "', " + group.getFacultyId() + ", " + null + ")"; // Временно добавляю нулл а вообще group.getHeadId()
        }
        this.getJdbcTemplate().update(sqlUpdate);
    }

    public List<Faculty> getAwailFaculty() {
        List<Faculty> facultyList;
        String sql =  "SELECT FAC_ID, FAC_NAME FROM FACULTYS ";
        Object[] params = new Object[] {};
        FacultyMapper mapper = new FacultyMapper() {

            @Override
            public Faculty mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Faculty faculty = null;
                int faculty_id = resultSet.getInt("FAC_ID");
                String faculty_name = resultSet.getString("FAC_NAME");
                faculty = new Faculty(faculty_id, faculty_name);
                return faculty;
            }
        };
        facultyList = this.getJdbcTemplate().query(sql, params, mapper);
        return facultyList;
    }

////    ------------------------------------------------- Не реализованные методы

    public List<Student> getGroupStudents() {
        List<Student> GroupStudentsList = null;
        return GroupStudentsList;
    }

    public void saveGroup(Group group) {
//        String sqlUpdate = "UPDATE STUDENTS set ST_NAME = ?, ST_GR_ID = ?, ST_TEAMMATE_ID = ? where ST_ID = ?" ;
//
//        if (student.getGroupId() == 0) {
//            this.getJdbcTemplate().update(sqlUpdate, student.getName(), null, student.getGroupTeamLeadId(), student.getId());
//
//        } else {
//            this.getJdbcTemplate().update(sqlUpdate, student.getName(), student.getGroupId(), student.getGroupTeamLeadId(), student.getId());
//        }
    }

    public String removeGroup(int id) {
        List<Faculty> groupList;
        String infoMessage = null;
//        String sqlUpdate = " SELECT * FROM ST_GROUP WHERE GR_HEAD_ID = ?";
//        Object[] params = new Object[] { id };
//        GroupMapper mapper = new GroupMapper();
//        studentList = this.getJdbcTemplate().query(sqlUpdate, params,  mapper);
//        if (studentList.isEmpty()) {
//            String sqlUpdate1 = " DELETE FROM STUDENTS WHERE ST_ID = ?" ;
//            this.getJdbcTemplate().update(sqlUpdate1,  id);
//            infoMessage = "Deleted";
//        } else {
//            infoMessage = "Student is Group Head, Edit Group and choose other Head";
//        }
        return infoMessage;
    }


}
