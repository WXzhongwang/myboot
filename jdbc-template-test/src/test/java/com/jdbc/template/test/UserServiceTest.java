package com.jdbc.template.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    public UserService userService;
    @Test
    public void addUser() {
        User user = new User();
        user.setEmail("18668485565@163.com");
        user.setAge(18);
        user.setName("Jane");
        userService.addUser(user);
    }

    @Test
    public void addUser2() {
        User user = new User();
        user.setEmail("18668485565@163.com");
        user.setAge(18);
        user.setName("Helen");
        int id = userService.addUser2(user);
        System.out.println(id);
    }

    @Test
    public void deleteUserById() {
        userService.deleteUserById(1L);
    }

    @Test
    public void updateUserById() {
        User user = new User();
        user.setId(1L);
        user.setEmail("1528683621@qq.com");
        user.setAge(18);
        user.setName("Siri");
        userService.updateUserById(user);
    }

    @Test
    public void getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        for (User user:allUsers
             ) {
            System.out.println(user.getId() + "," + user.getAge() + "," + user.getName() +"," +user.getEmail());
        }

    }
}