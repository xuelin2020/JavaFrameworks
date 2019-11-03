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