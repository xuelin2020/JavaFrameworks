package com.template.test;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestDome5 {
	
	@Before
	public void printBefore() {
		System.out.println("testPrint() 执行之前");
	}
	
	@After
	public void printAfter() {
		System.out.println("testPrint() 执行之后");
	}
	
	@Test
	public void testPrint() {
		
		System.out.println("这是一个单元测试~~~~~");
	}
	
	@Test
	public void testPrint2() {
		
		System.out.println("这是一个单元测试2~~~~~");
	}

}
