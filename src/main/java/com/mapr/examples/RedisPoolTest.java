package com.mapr.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component("redisPoolTest")
public class RedisPoolTest {
	
	@Autowired
	private JedisPool jPool;
	
	public boolean test() {
		Jedis jedis = jPool.getResource();
		jedis.auth("hello");
		jedis.set("name", "jedis");
		
		if (jedis.get("name").equals("jedis")) {
			System.out.println("JedisPool Test PASS");
			return true;
		} else {
			System.out.println("JedisPool Test FAIL");
			return false;
		}
	}
}
