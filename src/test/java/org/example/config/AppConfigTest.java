package org.example.config;

import org.example.App;
import org.example.dao.MessageDao;
import org.example.models.Message;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

class AppConfigTest {
    public static ApplicationContext appCon;
    public static ConfigurableApplicationContext app;
    @BeforeAll
    static public void BeforeAllTest(){
        app = SpringApplication.run(App.class);
        appCon = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @Test
    void beanMessage() {
        assertDoesNotThrow (() -> (Message) appCon.getBean("beanMessage"));
    }

    @Test
    void beanJdbcTemplate() {
        assertDoesNotThrow (() -> (JdbcTemplate) appCon.getBean("beanJdbcTemplate"));
    }

    @Test
    void beanMessageDao() {
        assertDoesNotThrow (() -> (MessageDao) appCon.getBean("beanMessageDao"));
    }

    @Test
    void beanDataSource() {
        assertDoesNotThrow (() -> (DataSource) appCon.getBean("beanDataSource"));
    }

    @AfterAll
    static public void AfterAllTest(){
        app.close();
    }
}