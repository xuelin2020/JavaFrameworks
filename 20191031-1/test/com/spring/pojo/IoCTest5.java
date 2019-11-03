package com.spring.pojo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IoCTest5 {

	@SuppressWarnings("resource")
	@Test
	public void testInitAndDestroy() {
		
		//创建容器
		AbstractApplicationContext context =	new ClassPathXmlApplicationContext("applicationContext5.xml");
		//主动调用destory方法执行销毁
		//context.destroy();
		//或者调用close方法触发销毁
		context.close();
	}
	
}
