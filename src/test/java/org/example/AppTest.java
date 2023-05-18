package org.example;

import junit.framework.TestCase;
import org.example.config.AppConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    public static ApplicationContext appCon;
    public static ConfigurableApplicationContext app;
    @BeforeAll
    static public void BeforeAllTest(){
        app = SpringApplication.run(App.class);
        appCon = new AnnotationConfigApplicationContext(AppConfig.class);
    }
    @AfterAll
    static public void AfterAllTest(){
        app.close();
    }
}
