package com.nc.lab2.mapper;

import com.nc.lab2.model.Group;
import com.nc.lab2.model.Mark;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MarkMapper implements RowMapper<Mark> {

    @Override
    public Mark mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Mark mark = null;
        int mark_id = resultSet.getInt("MARK_ID");
        int mark_st_id = resultSet.getInt("MARK_ST_ID");
        int mark_subj_id = resultSet.getInt("MARK_SUB_ID");
        double markDoubl = resultSet.getInt("MARK_MARK");
        mark = new Mark(mark_id, mark_st_id, mark_subj_id, markDoubl);
        return mark;
    }


}
