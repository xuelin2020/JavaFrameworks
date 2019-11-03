package com.spring.aop.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//创建容器
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AopTest {
	
	@Resource(name="userService")
	private UserService userService;
	
	@Test
	public void testUpdate() {
		userService.update();
	}
	
}
