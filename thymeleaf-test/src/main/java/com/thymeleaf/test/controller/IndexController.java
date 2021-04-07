package com.thymeleaf.test.controller;

import com.thymeleaf.test.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dick <18668485565@163.com>
 * @version V1.0.0
 * @description
 * @date created on 2020/4/30
 */
@Controller
public class IndexController {
    @GetMapping("/index")
    public String index(Model model) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User u = new User();
            u.setId((long) i);
            u.setName("Fighting:" + i);
            u.setAddress("深圳:" + i);
            users.add(u);
        }
        model.addAttribute("users", users);
        return "index";
    }
}
