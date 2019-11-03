package com.spring.pojo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


//@Service("person")
//@Repository("person")
//@Controller("person")
//<bean name="Person" class="com.spring.pojo.Person"></bean>

@Component("person")
//@Scope(value = "singleton")
//@Scope(value ="prototype")

public class Person {

	@Value(value = "Hello")
	private String name;
	@Value("11")
	private Integer age;
	
//	@Autowired
//	@Qualifier("car2")
	
	@Resource(name="car1")
	private Car car;
	
	public Person(){
		super();
		System.out.println("构造方法被调用");
	}
	
	public Person(String name, Car car) {
		super();
		System.out.println("构造方法Person(String name, Car car)");
		this.name = name;
		this.car = car;
	}
	
	public Person( Car car,String name) {
		super();
		System.out.println("构造方法Person(Car car,String name)");
		this.name = name;
		this.car = car;
	}
	
	public Person( Car car,Integer name) {
		super();
		System.out.println("构造方法Person(Car car,String name)");
		this.name = name+"";
		this.car = car;
	}

	@PostConstruct//初始化
	public void init() {
		System.out.println("Person被初始化！");
	}
	@PreDestroy//销毁
	public void destroy() {
		System.out.println("Person被销毁");
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", car=" + car + "]";
	}

	
	
}
