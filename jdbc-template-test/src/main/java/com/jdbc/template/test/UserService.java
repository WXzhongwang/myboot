package com.jdbc.template.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

/**
 * @author dick <18668485565@163.com>
 * @version V1.0.0
 * @description
 * @date created on 2020/5/6
 */
@Service
public class UserService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addUser(User user) {
        return jdbcTemplate.update("insert into user (name,age, email) values (?,?,?);", user.getName(), user.getAge(), user.getEmail());
    }

    public int addUser2(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int update = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("insert into user (name,age, email) values (?,?,?);", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getName());
                ps.setInt(2, user.getAge());
                ps.setString(3, user.getEmail());
                return ps;
            }
        }, keyHolder);
        user.setId(keyHolder.getKey().longValue());
        System.out.println(user);
        return update;
    }

    public int deleteUserById(Long id) {
        return jdbcTemplate.update("delete from user where id=?", id);
    }

    public int updateUserById(User user) {
        return jdbcTemplate.update("update user set name=?,age=?,email=? where id=?", user.getName(), user.getAge(), user.getEmail(),user.getId());
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query("select * from user", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                String name = resultSet.getString("name");
                Integer age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                long id = resultSet.getLong("id");
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setAge(age);
                user.setEmail(email);
                return user;
            }
        });
    }


}
