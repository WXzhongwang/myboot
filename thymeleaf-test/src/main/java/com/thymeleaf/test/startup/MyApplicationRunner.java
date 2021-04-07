package com.thymeleaf.test.startup;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author dick <18668485565@163.com>
 * @version V1.0.0
 * @description ApplicationRunner
 * @date created on 2020/4/30
 */
@Component
@Order(100)
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> nonOptionArgs = args.getNonOptionArgs();
        System.out.println("MyApplicationRunner >>> " + nonOptionArgs);
        Set<String> optionNames = args.getOptionNames();
        for (String key : optionNames) {
            System.out.println("MyApplicationRunner >>> "+ key + ":" + args.getOptionValues(key));
        }
        String[] sourceArgs = args.getSourceArgs();
        System.out.println("MyApplicationRunner >>> " + Arrays.toString(sourceArgs));
    }
}