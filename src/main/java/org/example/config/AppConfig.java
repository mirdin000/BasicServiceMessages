package org.example.config;

import org.example.dao.MessageDao;
import org.example.models.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
@ComponentScan("org.example.dao.MessageDao")
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Bean
    @Scope("prototype")
    @Qualifier("beanMessage")
    public Message beanMessage(){
        return new Message();
    }
    @Bean
    @Qualifier("beanMessageDao")
    public MessageDao beanMessageDao(){
        return new MessageDao(beanJdbcTemplate());
    }
    @Bean
    @Qualifier("beanJdbcTemplate")
    public JdbcTemplate beanJdbcTemplate(){
        return new JdbcTemplate(beanDataSource());
    }

    //описывается связь с properties файлом для вставки значений из него с помощью SPeL выражений
    @Value("${spring.datasorce.driverClassName}")
    private String driver;
    @Value("${spring.datasorce.url}")
    private String url;
    @Value("${spring.datasorce.username}")
    private String username;
    @Value("${spring.datasorce.password}")
    private String password;
    @Bean
    @Qualifier("beanDataSource")
    public DataSource beanDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}