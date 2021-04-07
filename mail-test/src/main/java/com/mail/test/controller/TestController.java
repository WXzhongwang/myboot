package com.mail.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dick <18668485565@163.com>
 * @version V1.0.0
 * @description
 * @date created on 2020/5/19
 */
@RestController
public class TestController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String index(){
        return "test";
    }
}
