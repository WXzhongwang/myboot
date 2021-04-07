package com.mybatis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapper2Test {

    @Autowired
    public UserMapper2 userMapper;

    @Test
    public void getAllUsers() {
        List<User> allUsers = userMapper.getAllUsers();

        for (User user:allUsers
        ) {
            System.out.println(user.getId() + "," + user.getAge() + "," + user.getName() +"," +user.getEmail());
        }
    }

    @Test
    public void getUserById() {
        userMapper.getUsersByName("jane");
    }

    @Test
    public void getUsersByName() {
        userMapper.getUsersByName("dick");
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setEmail("18668485565@163.com");
        user.setAge(18);
        user.setName("Jane");
        userMapper.addUser(user);
    }

    @Test
    public void updateUserById() {
        User user = new User();
        user.setId(1L);
        user.setEmail("1528683621@qq.com");
        user.setAge(18);
        user.setName("Siri");
        userMapper.updateUserById(user);
    }

    @Test
    public void deleteUserById() {
        userMapper.deleteUserById(1);
    }
}