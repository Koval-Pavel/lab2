package com.nc.lab2.mapper;

import com.nc.lab2.model.Group;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.nc.lab2.Lab2Application.log;

/**
 * Class for Group mapper.
 */
public class GroupMapper implements RowMapper<Group> {

    /** Logger instrument (slf4j) */
//    public static Logger log = LogManager.getLogger(GroupMapper.class);

    /**
     * Implementation of method that realize matching rows from database
     * with parameters of corresponding Object.
     * @param resultSet set of results, query in the database
     * @param rowNum number of Row
     * @return new Object of Faculty with param's from database
     */
    @Override
    public Group mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        try {
            int group_id = resultSet.getInt("GR_ID");
            String group_name = resultSet.getString("GR_NAME");
            int faculty_id = resultSet.getInt("GR_FAC_ID");
            int head_id = resultSet.getInt("GR_HEAD_ID");
            return new Group(group_id, group_name, faculty_id, head_id);
        } catch (SQLException e) {
            e.printStackTrace();
            log.warn("Some problem with database mapper (Subject)");
            return null;
        }
    }
}
