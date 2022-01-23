package com.nc.lab2.mapper;

import com.nc.lab2.model.Group;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class GroupMapper implements RowMapper<Group> {

    @Override
    public Group mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Group group = null;
        int group_id = resultSet.getInt("GR_ID");
        String group_name = resultSet.getString("GR_NAME");
        int faculty_id =resultSet.getInt("GR_FAC_ID");
        int  head_id = resultSet.getInt("GR_HEAD_ID");
        group = new Group(group_id, group_name, faculty_id, head_id);
        return group;
    }


}
