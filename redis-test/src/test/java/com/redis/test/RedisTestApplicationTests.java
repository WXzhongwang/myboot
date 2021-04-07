package com.redis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTestApplicationTests {

	@Autowired
	RedisTemplate redisTemplate;

	@Test
	public void hello() {
		ValueOperations ops = redisTemplate.opsForValue();
		ops.set("data-redis", "hello redis");
		Object hello = ops.get("data-redis");
		System.out.println(hello);
	}

	@Test
	public void contextLoads() {
	}

}
