package com.spring.pojo;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InjectionTest {

	
	@Test
	public void testProperty() {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-injection.xml");
		Person person1 = (Person)context.getBean("person1");
		//System.out.println(person1);
	}
	
	@Test
	public void testConstructor() {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-injection.xml");
		Person person2 = (Person)context.getBean("person2");
		System.out.println(person2);
	}
	
	@Test
	public void testP() {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-injection.xml");
		Person person3 = (Person)context.getBean("person3");
		System.out.println(person3);
	}
	
	@Test
	public void testSpel() {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-injection.xml");
		Person person4 = (Person)context.getBean("person4");
		System.out.println(person4);
	}
	
	@Test
	public void testCollections() {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-injection.xml");
		Person person5 = (Person)context.getBean("person5");
		System.out.println(person5);
	}
	
}
