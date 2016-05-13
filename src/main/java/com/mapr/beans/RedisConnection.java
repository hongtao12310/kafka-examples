package com.mapr.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.Jedis;

@Configuration("redisConnection")
public class RedisConnection {

	@Bean
	public Jedis jedis() {
//		new Jedis(uri)
		return new Jedis("192.168.56.103", 6379);
	}
	
	
}
