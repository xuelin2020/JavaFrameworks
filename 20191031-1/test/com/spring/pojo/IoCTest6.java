package com.spring.pojo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IoCTest6 {

	@SuppressWarnings("resource")
	
	@Test
	public void testStaticFactory() {
		AbstractApplicationContext context =	new ClassPathXmlApplicationContext("applicationContext6.xml");
		Person person1 = (Person)context.getBean("person1");
	}
	
	
	
}
