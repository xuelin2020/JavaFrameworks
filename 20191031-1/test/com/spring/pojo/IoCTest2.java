package com.spring.pojo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IoCTest2 {

	@SuppressWarnings("resource")
	@Test
	public void testIdAndName() {
		
		//创建容器
		ApplicationContext context =	new ClassPathXmlApplicationContext("applicationContext2.xml");
		//查找对象
		Person p1 = (Person)context.getBean("p1");
		Person p2 = (Person)context.getBean("p2");
		System.out.println(p1);
		System.out.println(p2);
	}
	
}
