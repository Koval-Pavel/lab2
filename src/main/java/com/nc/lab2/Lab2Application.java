package com.nc.lab2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//@EnableCaching
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@SpringBootApplication
public class Lab2Application {

    /** Logger instrument (slf4j) */
    public static Logger log = LogManager.getLogger();

    public static void main(String[] args) {

        SpringApplication.run(Lab2Application.class, args);
    }

}
