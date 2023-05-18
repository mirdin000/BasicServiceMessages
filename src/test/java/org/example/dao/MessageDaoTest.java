package org.example.dao;

import org.example.App;
import org.example.config.AppConfig;
import org.example.models.Message;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class MessageDaoTest {
    public static ApplicationContext appCon;
    public static ConfigurableApplicationContext app;
    public static MessageDao MD1;
    @BeforeAll
    static public void BeforeAllTest(){
        app = SpringApplication.run(App.class);
        appCon = new AnnotationConfigApplicationContext(AppConfig.class);
        MD1 = (MessageDao) appCon.getBean("beanMessageDao");
    }

    @Test
    void countCorrect() {
        assertDoesNotThrow(() -> MD1.count());
    }

    @Test
    void notesByUsername() {
        String expected = "Mark";
        Message M1 = MD1.notesByUsername(expected).get(0);
        assertEquals(expected, M1.getUsername());
    }

    @Test
    void allNotesCorrect() {
        assertDoesNotThrow(() -> MD1.allNotes());
    }

    @Test
    void noteSave() {
        Message M5 = new Message();
        M5.setUsername("ArturM");
        M5.setTextMessage("Hello");

        assertDoesNotThrow(() -> MD1.noteSave(M5));
    }

    @AfterAll
    static public void AfterAllTest(){
        app.close();
    }
}