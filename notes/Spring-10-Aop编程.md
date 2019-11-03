- 创建web项目
- 导包

- 日志

- 准备一个目标对象

  - 先创建接口，再创建实现类

- ```java
  package com.spring.aop.service;
  
  public class UserServiceImpl implements UserService{
  
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

  - 前置通知
  - 后置通知（如果出现异常就不调用）：在目标方法之后调用
  - 后置通知（无论是否出现异常都会调用）：在目标方法之后调用
  - 环绕通知：在目标方法之前、后调用
  - 异常通知：出现异常则调用

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

- 配置织入，将通知织入到目标对象

  - 加入命名空间

- ```
  
  ```

  

- 测试

