- 导入jar包

- 在Person中添加注解

- ```java
  @Component("person")
  //<bean name="Person" class="com.spring.pojo.Person"></bean>
  public class Person {
  ```

- 创建测试类

- ```java
  package com.spring.pojo;
  
  import javax.annotation.Resource;
  
  import org.junit.Test;
  import org.junit.runner.RunWith;
  import org.springframework.test.context.ContextConfiguration;
  import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
  
  //创建容器
  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration("classpath:applicationContext.xml")
  public class RunWithTest {
  	
  	@Resource(name="person")
  	private Person p;
  	
  	@Test
  	public void test() {
  		System.out.println(p);
  	}
  }
  
  ```

  