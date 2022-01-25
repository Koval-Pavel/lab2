package com.nc.lab2.dao;


import com.nc.lab2.mapper.GroupMapper;
import com.nc.lab2.mapper.StudentMapper;
import com.nc.lab2.model.Group;
import com.nc.lab2.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class StudentDAO extends JdbcDaoSupport {

    @Autowired
    public StudentDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public  List<Student> check() {
        List<Student> studentList;
        String sql = "SELECT * FROM STUDENTS";
        Object[] params = new Object[] {};
        StudentMapper mapper = new StudentMapper();
        studentList = this.getJdbcTemplate().query(sql, params, mapper);
        return studentList;
    }

    public  List<Student> getAllStudents() {
        List<Student> studentList;
        String sql = "SELECT STUDENTS.ST_ID, STUDENTS.ST_NAME as ST_NAME1, GR_NAME, ST1.ST_NAME as ST_NAME2 FROM STUDENTS LEFT JOIN ST_GROUP on ST_GROUP.GR_ID = STUDENTS.ST_GR_ID left join STUDENTS ST1  on STUDENTS.ST_TEAMMATE_ID= ST1.ST_ID";
        Object[] params = new Object[] {};
        StudentMapper mapper = new StudentMapper() {
            @Override
            public Student mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Student student;
                int student_id = resultSet.getInt("ST_ID");
                String student_name = resultSet.getString("ST_NAME1");
                String student_group_name = resultSet.getString("GR_NAME");
                String student_groupTeamMate = resultSet.getString("ST_NAME2");
                student = new Student(student_id,student_name,student_group_name,student_groupTeamMate); // check constructor
                return student;
            }
        };
        studentList = this.getJdbcTemplate().query(sql, params, mapper);
        return studentList;
    }

//    На даннsй моммент реализован только поиск по имени
    public List<Student> findStudentAccount(String name) {
        List<Student> studentList;
        String sql = "SELECT ST_ID, ST_NAME, ST_GR_ID, ST_TEAMMATE_ID FROM STUDENTS" + " where ST_NAME = ? ";
        Object[] params = new Object[] { name };
        StudentMapper mapper = new StudentMapper();
        try {
            studentList = this.getJdbcTemplate().query(sql, params, mapper);
            return studentList;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public String saveStudent(Student student) {
        String infoMessage = null;
        List<Student> TeamMate;
        if (!student.getTeamMate_Name().equals("")) {
            String sqlUpdate = "SELECT * FROM STUDENTS WHERE ST_NAME = '" + student.getTeamMate_Name() + "'";
            Object[] params = new Object[]{};
            StudentMapper mapper = new StudentMapper();
            TeamMate = this.getJdbcTemplate().query(sqlUpdate, params, mapper);
            if (TeamMate.isEmpty()) {
                infoMessage = "No Students with this name";
            } else {
                sqlUpdate = "UPDATE STUDENTS set ST_NAME = ?, ST_GR_ID = ?, ST_TEAMMATE_ID = ? WHERE ST_ID = ?";
                this.getJdbcTemplate().update(sqlUpdate, student.getName(),
                        student.getGroupId() == 0? null:student.getGroupId(),  TeamMate.get(0).getId(), student.getId());
            }
        } else {
            // подкорректировать, можно не добавлять тиммате_ИД
            String sqlUpdate = "UPDATE STUDENTS set ST_NAME = ?, ST_GR_ID = ?, ST_TEAMMATE_ID = ? where ST_ID = ?" ;
            if (student.getGroupId() == 0) {
                this.getJdbcTemplate().update(sqlUpdate, student.getName(),
                        null, student.getGroupTeamMateId() == 0?  null: student.getGroupTeamMateId(), student.getId());
            } else {
                this.getJdbcTemplate().update(sqlUpdate, student.getName(), student.getGroupId(),
                        student.getGroupTeamMateId() ==0?null: student.getGroupTeamMateId(), student.getId());
            }
        }
        return infoMessage;
    }

    public String removeStudent(int id) {
        List<Group> studentList; // Group
        String infoMessage;
        String sqlUpdate = " SELECT * FROM ST_GROUP WHERE GR_HEAD_ID = ?";
        Object[] params = new Object[] { id };
        GroupMapper mapper = new GroupMapper();
        studentList = this.getJdbcTemplate().query(sqlUpdate, params,  mapper);
        if (studentList.isEmpty()) {
            String sqlUpdate1 = " DELETE FROM STUDENTS WHERE ST_ID = ?" ;
            this.getJdbcTemplate().update(sqlUpdate1,  id);
            infoMessage = "Deleted";
        } else {
            infoMessage = "Student is Group Head, Edit Group and choose other Head";
        }
        return infoMessage;
    }

    public String addStudent(Student student) {
        String infoMessage = null;
        List<Student> TeamMate;
        if (!student.getTeamMate_Name().equals("")) {
            String sqlUpdate = "SELECT * FROM STUDENTS WHERE ST_NAME = '" + student.getTeamMate_Name() + "'";
            Object[] params = new Object[]{};
            StudentMapper mapper = new StudentMapper();
            TeamMate = this.getJdbcTemplate().query(sqlUpdate, params, mapper);
            if (TeamMate.isEmpty()) {
                infoMessage = "No Students with this name";
            } else {
                sqlUpdate = "INSERT INTO STUDENTS (ST_NAME, ST_GR_ID, ST_TEAMMATE_ID) VALUES " +
                        "('" + student.getName() + "', " + student.getGroupId() + ", "  + TeamMate.get(0).getId() + ")";
                this.getJdbcTemplate().update(sqlUpdate);
            }
        } else {

            // подкорректировать, можно не добавлять тиммате_ИД
            String sqlUpdate;
            if (student.getGroupId() == 0) {
                sqlUpdate = "INSERT INTO STUDENTS ( ST_NAME, ST_GR_ID,  ST_TEAMMATE_ID) VALUES " +
                        "('" + student.getName() + "', " + null + ", " + student.getGroupTeamMateId() + ")";
            } else {
                sqlUpdate = "INSERT INTO STUDENTS (ST_NAME, ST_GR_ID,  ST_TEAMMATE_ID) VALUES " +
                        "('" + student.getName() + "', " + student.getGroupId() + ", "  + student.getGroupTeamMateId() + ")";
            }
            this.getJdbcTemplate().update(sqlUpdate);
        }
        return infoMessage;
    }

    public List<Group> getAwailGroup() {
        List<Group> groupList;
        String sql =  "SELECT GR_ID, GR_NAME FROM ST_GROUP ";
        Object[] params = new Object[] {};
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
        groupList = this.getJdbcTemplate().query(sql, params, mapper);
        return groupList;
    }


////    ------------------------------------------------- Не реализованные методы
    public void getStudentsInfo() {
    }

    public  Student StudentWithMark() {
        Student studentWithMark = null;
//        String sql = "SELECT * FROM STUDENTS LEFT JOIN ST_GROUP on GR_ID = STUDENTS.ST_GR_ID";
//        Object[] params = new Object[] {};
//        StudentMapper mapper = new StudentMapper() {
//            @Override
//            public Student mapRow(ResultSet resultSet, int rowNum) throws SQLException {
//                Student student = null;
//                int student_id = resultSet.getInt("ST_ID");
//                String student_name = resultSet.getString("ST_NAME");
//                String student_group_name = resultSet.getString("GR_NAME");
//                int student_groupTeamLead_id = resultSet.getInt("ST_TEAMMATE_ID");
//                student = new Student(student_id,student_name,student_group_name,student_groupTeamLead_id);
//                return student;
//            }
//        };
//        studentWithMark = this.getJdbcTemplate().query(sql, params, mapper);
        return studentWithMark;
    }

}
