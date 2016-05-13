package com.mapr.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



class Friend {
	private String name;
	private int id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}


@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.mapr")
public class AppConfig {

	public void print() {
		System.out.println("hello world");
	}
	
	
	@Bean
	public Friend friend() {
		Friend friend = new Friend();
		friend.setName("李明");
		friend.setId(1);
		return friend;
	}
}
