package com.mapr.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.Jedis;

@Configuration("redisConnection")
public class RedisConnection {

	@Bean
	public Jedis jedis() {
		// return new Jedis("192.168.56.103", 6379);
		// solution 1
//		Jedis jedis = new Jedis("redis://192.168.56.103:6379");
//		jedis.auth("hello");
//		return jedis;
		
		//solution 2
		Jedis jedis = new Jedis("redis://:hello@192.168.56.103:6379");
		return jedis;		
	}

}
