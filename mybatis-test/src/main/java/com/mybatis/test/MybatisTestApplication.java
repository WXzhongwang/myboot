package com.mybatis.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shengwangzhong
 */
@SpringBootApplication
@MapperScan(basePackages = "com.mybatis.test")
public class MybatisTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisTestApplication.class, args);
	}

}
