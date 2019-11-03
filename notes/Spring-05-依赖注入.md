# set方法注入

- 主要注入对象的属性，最常用set方法，主方法

- pojo类

- 配置bean

- ```xml
  <bean name="person1" class="com.spring.pojo.Person">
  		<property name="name" value="helen"></property>
  		<property name="age" value="18"></property>
  </bean>
  ```

- 测试类

- ```java
  @Test
  	public void testProperty() {
  		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-injection.xml");
  		Person person1 = (Person)context.getBean("person1");
  		System.out.println(person1);
  	}
  ```

- 测试结果

> 构造方法被调用
>
> Person [name=helen, age=18]

- 复杂注入

- 创建pojo类Car.java

- ```java
  package com.spring.pojo;
  
  public class Car {
  	
  	private String name;
  	private String color;
  	
  	public Car() {
  		super();
  	}
  	
  	public String getName() {
  		return name;
  	}
  	public void setName(String name) {
  		this.name = name;
  	}
  	public String getColor() {
  		return color;
  	}
  	public void setColor(String color) {
  		this.color = color;
  	}
  	@Override
  	public String toString() {
  		return "Car [name=" + name + ", color=" + color + "]";
  	}
  
  }
  ```

- 在Person中添加Car

- ```java
  private Car car;
  
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
  ```

- 配置bean

- ```xml
  	<bean name="car" class="com.spring.pojo.Car">
  		<property name="name" value="BMW"></property>
  		<property name="color" value="red"></property>
  	</bean>
  	<bean name="person1" class="com.spring.pojo.Person">
  		<property name="name" value="helen"></property>
  		<property name="age" value="18"></property>
  		<property name="car" ref="car"></property>
  	</bean>
  ```

  <u></u>

- 执行测试类

  > 构造方法被调用
  >
  > Person [name=helen, age=18, car=Car [name=BMW, color=red]]

- value 表示值类型的注入；ref 表示对象类型的注入

# 构造方法注入

- 直接创建的时候把属性注入，即是创建对象的方式又是依赖注入的方式

- 为 Person 类添加有参构造方法

- ```java
  public Person(String name, Car car) {
  		super();
  		System.out.println("构造方法Person(String name, Car car)");
  		this.name = name;
  		this.car = car;
  	}
  ```

- 配置bean

- ```xml
  <bean name="person2" class="com.spring.pojo.Person">
  		<constructor-arg name="name" value="Tom"></constructor-arg>
  		<constructor-arg name="car" ref="car"></constructor-arg>
  	</bean>
  ```

- 测试

- ```java
  @Test
  	public void testConstructor() {
  		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-injection.xml");
  		Person person2 = (Person)context.getBean("person2");
  		System.out.println(person2);
  	}
  ```

- 测试结果

> 构造方法Person(String name, Car car)
>
> Person [name=Tom, age=null, car=Car [name=BMW, color=red]]

- 方法重载时，用index属性选择具体的调用方法（同类型顺序）

- ```xml
  <bean name="person2" class="com.spring.pojo.Person">
    <!-- 调用第一个参数是name的 -->
  		<constructor-arg name="name" value="Tom" index="0"></constructor-arg>
  		<constructor-arg name="car" ref="car"></constructor-arg>
  	</bean>
  ```

- 使用 type 指定方法参数类型（不同类型）

- ```xml
  <bean name="person2" class="com.spring.pojo.Person">
    <!-- 调用第一个参数是name的 -->
  		<constructor-arg name="name" value="Tom" index="0" type="java.lang.Integer"></constructor-arg>
  		<constructor-arg name="car" ref="car"></constructor-arg>
  	</bean>
  ```

  

# p名称空间注入

- set方法的另一种写法，spring4新特性

  - p : 属性名 表示值类型
  - p ：属性名-ref 表示引用类型

- 先导入名称空间

- ```xml
  xmlns:p="http://www.springframework.org/schema/p
  ```

- 使用p：属性注入

- ```xml
  <bean name="person3" class="com.spring.pojo.Person" 
  	p:name="Tom" p:age="19" p:car-ref="car"></bean>
  ```

- 测试

- ```java
  @Test
  	public void testP() {
  		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-injection.xml");
  		Person person3 = (Person)context.getBean("person3");
  		System.out.println(person3);
  	}
  ```

- 测试结果

> Person [name=Tom, age=19, car=Car [name=BMW, color=red]]

- 典型案例：配置数据源

- ```xml
  <bean id="myDataSource" class="org.apache.commons.dbcp.BasicDataSource"
        destroy-method="close"
        p:driverClassName="com.mysql.jdbc.Driver"
        p:url="jdbc:mysql://localhost:3306/mydb"
        p:username="root"
        p:password="masterkaoli"/>
  ```



# Spel表达式注入

- el包，表达式支持

- ```xml
  <bean name="person4" class="com.spring.pojo.Person">
  		<property name="name" value="#{person1.name}"></property>
  		<property name="age" value="#{person1.age}"></property>
  		<property name="car" ref="car"></property>
  	
  	</bean>
  ```

- 测试

- ```java
  @Test
  	public void testSpel() {
  		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-injection.xml");
  		Person person4 = (Person)context.getBean("person4");
  		System.out.println(person4);
  	}
  ```

  