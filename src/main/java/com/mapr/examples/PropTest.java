package com.mapr.examples;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;


public class PropTest {

	public String get_property(String pro) {
		Properties props = new Properties();
		String propspath = "app.props";
		InputStream stream = this.getClass().getClassLoader().getResourceAsStream(propspath);
		
		try {
			props.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return props.getProperty(pro);
	}
	public static void main(String[] args) {
		String url = new PropTest().get_property("redis.url");
		System.out.println(url);
		System.out.println(new PropTest().get_property("redis.authurl"));
		
	}

}
