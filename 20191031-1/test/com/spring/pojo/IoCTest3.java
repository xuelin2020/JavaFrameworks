package com.spring.pojo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IoCTest3 {

	@SuppressWarnings("resource")
	@Test
	public void testScpoe() {
		
		//创建容器
		ApplicationContext context =	new ClassPathXmlApplicationContext("applicationContext3.xml");
		//查找对象
		Person p1 = (Person)context.getBean("p1");
		Person p2 = (Person)context.getBean("p2");
		//scope="singleton"或默认时	值是true
		//scope="prototype"时	值是false
		System.out.println(p1==p2);
	}
	
}
