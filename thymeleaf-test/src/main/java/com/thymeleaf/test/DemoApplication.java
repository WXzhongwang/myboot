package com.thymeleaf.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * @author shengwangzhong
 */
@SpringBootApplication
@EnableScheduling
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Scheduled(fixedRate = 2000)
	public void fixedRate() {
		System.out.println("fixedRate >>> "+new Date());
	}
	@Scheduled(fixedDelay = 2000)
	public void fixedDelay() {
		System.out.println("fixedDelay >>> "+new Date());
	}
	@Scheduled(initialDelay = 2000, fixedDelay = 2000)
	public void initialDelay() {
		System.out.println("initialDelay >>> "+new Date());
	}

}
