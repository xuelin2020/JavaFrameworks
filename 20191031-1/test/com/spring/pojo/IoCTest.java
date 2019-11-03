package com.spring.pojo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IoCTest {

	@SuppressWarnings("resource")
	@Test
	public void testCreatPerson() {
		
		//创建容器
		ApplicationContext context =	new ClassPathXmlApplicationContext("applicationContext.xml");
		//查找对象
		Person p = (Person)context.getBean("p");
		System.out.println(p);
	}
	
}
