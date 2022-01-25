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
                Group group;
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

    public String addGroup (Group group) {
        String infoMessage = null;
        String sqlUpdate;
        List<Student> GroupHead;
        if (!group.getHeadName().equals("")) {
            sqlUpdate = "SELECT * FROM STUDENTS WHERE ST_NAME = '" + group.getHeadName() + "'";
            Object[] params = new Object[]{};
            StudentMapper mapper = new StudentMapper();
            GroupHead = this.getJdbcTemplate().query(sqlUpdate, params, mapper);
            if (GroupHead.isEmpty()) {
                infoMessage = "No Students with this name";
            } else {
                sqlUpdate =  "INSERT INTO ST_GROUP (GR_NAME, GR_FAC_ID, GR_HEAD_ID) VALUES " +
                        "('" + group.getName() + "', " + group.getFacultyId() + ", " + GroupHead.get(0).getId() + ")";
                this.getJdbcTemplate().update(sqlUpdate);
            }
        } else {
            if (group.getFacultyId() == 0) {
                sqlUpdate = "INSERT INTO ST_GROUP ( GR_NAME, GR_FAC_ID, GR_HEAD_ID) VALUES " +
                        "('" + group.getName() + "', " + null + ", " + null + ")";
            } else {

                sqlUpdate = "INSERT INTO ST_GROUP (GR_NAME, GR_FAC_ID, GR_HEAD_ID) VALUES " +
                        "('" + group.getName() + "', " + group.getFacultyId() + ", " + null + ")"; // Временно добавляю нулл а вообще group.getHeadId()
            }

            this.getJdbcTemplate().update(sqlUpdate);
        }
        return infoMessage;
    }

    public List<Faculty> getAwailFaculty() {
        List<Faculty> facultyList;
        String sql =  "SELECT FAC_ID, FAC_NAME FROM FACULTYS ";
        Object[] params = new Object[] {};
        FacultyMapper mapper = new FacultyMapper() {

            @Override
            public Faculty mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Faculty faculty;
                int faculty_id = resultSet.getInt("FAC_ID");
                String faculty_name = resultSet.getString("FAC_NAME");
                faculty = new Faculty(faculty_id, faculty_name);
                return faculty;
            }
        };
        facultyList = this.getJdbcTemplate().query(sql, params, mapper);
        return facultyList;
    }

    public String removeGroup(int id) {
        String infoMessage;
        String sqlUpdate1 = " UPDATE STUDENTS set ST_GR_ID = null WHERE ST_GR_ID = ?" ;
        this.getJdbcTemplate().update(sqlUpdate1,  id);
        String sqlUpdate2 = " DELETE FROM ST_GROUP WHERE GR_ID = ?" ;
        this.getJdbcTemplate().update(sqlUpdate2,  id);
        infoMessage = "Deleted";
        return infoMessage;
    }

    public String saveGroup(Group group) {
        String infoMessage = null;
        String sqlUpdate;
        List<Student> GroupHead;
        if (!group.getHeadName().equals("")) {
            sqlUpdate = "SELECT * FROM STUDENTS WHERE ST_NAME = '" + group.getHeadName() + "'";
            Object[] params = new Object[]{};
            StudentMapper mapper = new StudentMapper();
            GroupHead = this.getJdbcTemplate().query(sqlUpdate, params, mapper);
            if (GroupHead.isEmpty()) {
                infoMessage = "No Students with this name";
            } else {
                sqlUpdate = "UPDATE ST_GROUP set GR_NAME = ?, GR_FAC_ID = ?, GR_HEAD_ID = ? WHERE GR_ID = ?";
                this.getJdbcTemplate().update(sqlUpdate, group.getName(),
                        group.getFacultyId() == 0? null : group.getFacultyId(), GroupHead.get(0).getId(), group.getId());
            }
        } else {
            // подкорректировать, можно не добавлять head_ИД
            sqlUpdate = "UPDATE ST_GROUP set GR_NAME = ?, GR_FAC_ID = ?, GR_HEAD_ID = ? where GR_ID = ?" ;
            if (group.getFacultyId() == 0) {
                this.getJdbcTemplate().update(sqlUpdate, group.getName(),
                        null, group.getHeadId() == 0? null : group.getHeadId(), group.getId());
            } else {
                this.getJdbcTemplate().update(sqlUpdate, group.getName(), group.getFacultyId(),
                        group.getHeadId() == 0? null : group.getHeadId(), group.getId());
            }
        }
        return infoMessage;
    }

////    ------------------------------------------------- Не реализованные методы

    public List<Student> getGroupStudents() {
        List<Student> GroupStudentsList = null;
        return GroupStudentsList;
    }
}
