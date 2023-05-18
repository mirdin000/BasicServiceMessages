package org.example.models;

import org.example.App;
import org.example.config.AppConfig;
import org.example.dao.MessageDao;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {
    public static ApplicationContext appCon;
    public static ConfigurableApplicationContext app;
    public static MessageDao MD1;
    public static Message M1;
    @BeforeAll
    static public void BeforeAllTest(){
        app = SpringApplication.run(App.class);
        appCon = new AnnotationConfigApplicationContext(AppConfig.class);

        MD1 = (MessageDao) appCon.getBean("beanMessageDao");
        M1 = MD1.notesByUsername("Mark").get(0);
    }

    @Test
    void getId() {
        long expected = 2;
        M1.setId(expected);
        assertEquals(expected, M1.getId());
    }

    @Test
    void setId() {
        long expected = 2;
        M1.setId(expected);
        assertEquals(expected, M1.getId());
    }

    @Test
    void getUsername() {
    }

    @Test
    void getTextMessage() {
    }

    @Test
    void setUsername() {
    }

    @Test
    void setTextMessage() {
    }

    @AfterAll
    static public void AfterAllTest(){
        app.close();
    }
}