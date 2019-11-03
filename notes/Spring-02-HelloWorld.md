- 创建web项目

- 引入jar包

- 引入日志配置文件

- 创建java类

  - ```java
    package com.spring.pojo;
    
    public class Person {
    	
    	private String name;
    	private Integer age;
    	public Person(){
    		super();
    		System.out.println("构造方法被调用");
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
    	@Override
    	public String toString() {
    		return "Person [name=" + name + ", age=" + age + "]";
    	}
    	
    	//getters、sertters、toString
    }
    
    ```

- 安装 spring 插件

- 创建bean容器、配置bean对象（名字和路径没有特殊要求）

  - 一般命名为 applicationContext-xxx.xml（放在src下）

  - ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
    	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    	
    	<!-- bean:使容器创建 Person 的对象 -->
    	<!-- name:相当于变量名 Person p = new Person(); -->
    	<!-- class:类的全限定名 -->
    	<bean name="p" class="com.spring.pojo.Person"></bean>
    </beans>
    ```

- 测试用例

  - ```java
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
    
    ```

- 测试结果

  <img src="/Users/xuelin/Documents/Github/JavaFrameworks/notes/img/截屏2019-11-03下午5.37.02.png" alt="截屏2019-11-03下午5.37.02" style="zoom:50%;" />

- 思考：
  - 除了name 、 class ，bean中有没有其他属性
  - 对象是什么时候创建的，bean 初始化时还是什么时候用什么时候创建
  - 对象创建出来是单例还是多例，创建了几个
  - 对象中的两个属性如何赋值
  - 等等