package org.example.dao;

import org.example.models.Message;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class MessageDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public MessageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //запрос на количество записей в таблице
    public int count(){
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM messages", Integer.class);
    }

    //запрос на все записи из таблицы
    public List<Message> allNotes(){
        return jdbcTemplate.query("SELECT * FROM messages", new BeanPropertyRowMapper<>(Message.class));
    }

    //запрос на все записи отвечающие условию такого же имени пользователя
    public List<Message> notesByUsername(String username){
        return jdbcTemplate.query("SELECT * FROM messages WHERE username=?", new Object[]{username},
                new BeanPropertyRowMapper<>(Message.class));
    }
    //запрос на добавление новой записи в таблицу messages
    public void noteSave(@NotNull Message message){
        jdbcTemplate.update("INSERT INTO messages VALUES(?, ?, ?)", count()+1, message.getUsername(),
                message.getTextMessage());
    }
}
