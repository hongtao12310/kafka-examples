package com.mapr.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisPool;

@Component
public class RedisPoolConnection {

	@Bean
	public JedisPool jedisPool() {
		JedisPool jPool = new JedisPool("redis://192.168.56.103:6379");
		return jPool;
	}
}
