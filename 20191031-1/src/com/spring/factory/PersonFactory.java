package com.spring.factory;

import com.spring.pojo.Person;

public class PersonFactory {
	
	public static Person creatPerson1() {
		
		System.out.println("静态工厂创建Person");
		
		return new Person();
	}
	
	public Person creatPerson2() {
		System.out.println("实例工厂创建Person");
		return new Person();
	}
	
}
