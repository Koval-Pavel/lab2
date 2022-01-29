package com.nc.lab2.mapper;

import com.nc.lab2.model.Mark;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.nc.lab2.Lab2Application.log;

/**
 * Class for Mark mapper.
 */
public class MarkMapper implements RowMapper<Mark> {

    /**
     * Implementation of method that realize matching rows from database
     * with parameters of corresponding Object.
     * @param resultSet set of results, query in the database
     * @param rowNum number of Row
     * @return new Object of Mark with param's from database
     */
    @Override
    public Mark mapRow(ResultSet resultSet, int rowNum)  {
        try {
            int mark_id = resultSet.getInt("MARK_ID");
            int mark_st_id = resultSet.getInt("MARK_ST_ID");
            int mark_subj_id = resultSet.getInt("MARK_SUB_ID");
            double markDoubl = resultSet.getInt("MARK_MARK");
            String studentName = resultSet.getString("ST_NAME");
            String subjectName = resultSet.getString("SUB_NAME");
            String markDate = resultSet.getString("MARK_DATE");
            return new Mark(mark_id, mark_st_id, mark_subj_id, markDoubl,studentName,subjectName, markDate);
        } catch (SQLException e) {
            e.printStackTrace();
            log.warn("Some problem with database mapper (Mark)");
            return null;
        }
    }


}
