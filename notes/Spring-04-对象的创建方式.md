# 对象创建的四种方式

## 无参构造函数

- 前面的方式调用了Person的无参构造函数创建了Person对象

## 有参构造函数

- 参考后边的依赖注入

## 静态工厂方法和实例工厂方法

- 主要用于第三方组件整合到Spring中

- 创建一个工厂类

- ```java
  package com.spring.factory;
  
  import com.spring.pojo.Person;
  
  public class PersonFactory {
  	
  	public static Person creatPerson1() {
  		
  		System.out.println("静态工厂创建Person");
  		
  		return new Person();
  	}
  	
  }
  
  ```

- 配置 bean 

- ```xml
  	<bean name="person1" class="com.spring.factory.PersonFactory" factory-method="creatPerson1" ></bean>
    
  ```

- 测试类

- ```java
  @Test
  	public void testStaticFactory() {
  		AbstractApplicationContext context =	new ClassPathXmlApplicationContext("applicationContext6.xml");
  		//Person person1 = (Person)context.getBean("person1");
  	}
  ```

- 测试结果

 > 静态工厂创建Person
  >
  > 构造方法被调用

- 实例工厂

- ```java
  public Person creatPerson2() {
  		System.out.println("实例工厂创建Person");
  		return new Person();
  	}
  ```

- 配置bean

- ```xml
  <bean name="personFactory" class="com.spring.factory.PersonFactory" ></bean>
  	<bean name="person2" factory-bean="personFactory" factory-method="creatPerson2"></bean>
  ```

- 测试类

- ```java
  @Test
  	public void testStaticFactory() {
  		AbstractApplicationContext context =	new ClassPathXmlApplicationContext("applicationContext6.xml");
  ```

- 测试结果

  > 实例工厂创建Person
  >
  > 构造方法被调用