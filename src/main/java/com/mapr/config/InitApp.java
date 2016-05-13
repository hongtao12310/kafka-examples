package com.mapr.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.mapr.examples.RedisTest;

import redis.clients.jedis.Jedis;

public class InitApp {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		Jedis jedis = (Jedis)ctx.getBean("jedis");
		System.out.println(jedis.get("guid"));
		jedis.set("name", "hongtao");
		
		RedisTest rTest = (RedisTest) ctx.getBean("redisTest");
		rTest.stringtest();
		ctx.close();
	}

	// public void onStartup(ServletContext container) throws ServletException {
	//
	// AnnotationConfigWebApplicationContext ctx = new
	// AnnotationConfigWebApplicationContext();
	// System.out.println(ctx.getBean("jedis"));
	// ctx.close();
	//
	// ctx.register(AppConfig.class);
	// ctx.setServletContext(container);
	//
	// ServletRegistration.Dynamic servlet = container.addServlet("dispatcher",
	// new DispatcherServlet(ctx));
	//
	// servlet.setLoadOnStartup(1);
	// servlet.addMapping("/");
	// }
}
