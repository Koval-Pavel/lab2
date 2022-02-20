package com.nc.lab2.dao;

import com.nc.lab2.mapper.StudentMapper;
import org.apache.ibatis.jdbc.ScriptRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;

import static com.nc.lab2.Lab2Application.log;

@Repository
public class DAOInit extends JdbcDaoSupport {

    private final String SQL_CHECK = "SELECT * FROM STUDENT_LIST.STUDENTS";

    /** Field with username information */
    @Value("${spring.datasource.username}")
    private String username;

    /** Field with driver information */
    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    /** Field with url information */
    @Value("${spring.datasource.url}")
    private String url;

    /** Field with password information */
    @Value("${spring.datasource.password}")
    private String password;

    /** Field with script address information */
    @Value("${spring.datasource.script}")
    private String script;


//    @Autowired
//    private StudentDAO studentDAO;

    /**
     * Constructor for class
     * @param dataSource
     */
    @Autowired
    public DAOInit(DataSource dataSource) { this.setDataSource(dataSource); }

    /**
     * Method for initialization of Data Base
     */
    @EventListener(ApplicationReadyEvent.class)
    public void DBInit () {
        try {
            Object[] params = new Object[] {};
            StudentMapper mapper = new StudentMapper();
            this.getJdbcTemplate().query(SQL_CHECK, params, mapper);
            log.info("Database already exist");
        } catch (Exception e) {
            try {
                Class.forName(driver);
                Connection conn = DriverManager.getConnection(
                        url, username, password);
                ScriptRunner sr = new ScriptRunner(conn);
                URL url = this.getClass().getClassLoader().getResource(script);
                sr.runScript(new BufferedReader(new FileReader(url.getFile())));
                log.info("Database created");
            } catch (Exception c) {
                System.err.println(c);
                log.warn("Database Creation failed");
            }
        }
    }


}
