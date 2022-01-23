package com.nc.lab2.dao;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;

@Repository
public class DAOInit {

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.password}")
    private String password;

    @Autowired
    private StudentDAO studentDAO;

    @EventListener(ApplicationReadyEvent.class)
    public void DBInit () {
        try {
           studentDAO.getAllStudents();
        } catch (Exception b) {
            try {
                Class.forName(driver);
                Connection conn = DriverManager.getConnection(
                        url, username, password);
                ScriptRunner sr = new ScriptRunner(conn);
                sr.runScript(new BufferedReader(new FileReader("d:/script1.sql")));

            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }


}
