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
	private Person p1;
	
//	@Resource(name="person")
//	private Person p2;
	
	@Test
	public void test() {
		System.out.println(p1);
	}
}
