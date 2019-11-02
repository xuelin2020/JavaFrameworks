- 创建web项目

- 导包

- 写一个 Service 接口

- ```java
  package com.service;
  
  public interface IUserService {
  	
  	public void save();
  	public void delete();
  	public void update();
  	public void select();
  	
  }
  
  ```

  

- 写一个接口的实现类

- ```java
  package com.service;
  
  public class UserServiceImpl implements IUserService{
  
  	@Override
  	public void save() {
  		System.out.println("保存用户");		
  	}
  
  	@Override
  	public void delete() {
  		System.out.println("删除用户");		
  		
  	}
  
  	@Override
  	public void update() {
  		System.out.println("更新用户");		
  		
  	}
  
  	@Override
  	public void select() {
  		System.out.println("查询用户");		
  		
  	}
  }
  
  ```

- 编写通知

- ```java
  package com.advice;
  
  import org.aspectj.lang.ProceedingJoinPoint;
  
  public class TransactionAdvice {
  	
  	public void before() {
  		System.out.println("前置通知被执行");
  	}
  	
  	public void afterReturning() {
  		System.out.println("后置通知被执行（出现异常不调用）");
  	}
  	
  	public void after() {
  		System.out.println("后置通知被执行（无论是否出现异常都会调用）");
  	}
  	
  	public void afterException() {
  		System.out.println("异常通知被执行");
  	}
  	
  	public Object around(ProceedingJoinPoint point) throws Throwable{
  		
  		System.out.println();
  		Object proceed = point.proceed();//调用目标方法
  		System.out.println();
  
  		return proceed;
  	}
  	
  }
  ```

- 回到 Spring ，创建一个 Spring_xml 文件，加入 aop 名称空间

- ```
  
  ```

  