package com.mapr.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

@Component("redisTest")
public class RedisTest {
	
	@Autowired
	private Jedis jedis;
	
	public Boolean stringtest() {
		jedis.set("name", "hello");
		if (jedis.get("name").equals("hello")) {
			System.out.println("String Testing PASS");
			return true;
		}
		System.out.println("String Testing Failed");
		return false;
	}
	
//	public static void main(String[] args) {
//		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
//		RedisTest redisTest = new RedisTest();
//		redisTest.stringtest();
//		ctx.close();
//	}
}
