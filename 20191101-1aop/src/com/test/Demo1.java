package com.test;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.UserServiceImpl;

public class Demo1 {
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserServiceImpl userService = app.getBean(UserServiceImpl.class);
		userService.update();
		
	}
}