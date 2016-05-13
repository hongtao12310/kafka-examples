package com.mapr.beans;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;




//	@Autowired
//	private Friend friend;
//
//	public String get_friend_name() {
//		return friend.getName();
//	}

//	public static void main(String[] args) {
//		
////		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
////		Friend friend = ctx.getBean(Friend.class);
////		
////		System.out.println(friend.getName());
////		
////		AppConfig app = ctx.getBean(AppConfig.class);
////		app.print();
//		System.out.println(new TestBean().get_friend_name());
//	}

	public class InitApp implements WebApplicationInitializer {

		  public void onStartup(ServletContext container) throws ServletException {

		    AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		    ctx.register(AppConfig.class);
		    ctx.setServletContext(container);

		    ServletRegistration.Dynamic servlet = container.addServlet(
		        "dispatcher", new DispatcherServlet(ctx));

		    servlet.setLoadOnStartup(1);
		    servlet.addMapping("/");
		  }
	}

