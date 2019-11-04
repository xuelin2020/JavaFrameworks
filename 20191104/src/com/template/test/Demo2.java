package com.template.test;


import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.template.pojo.Dept;
import com.template.service.IDeptService;
import com.template.service.impl.DeptServiceImpl;

public class Demo2 {

	public static void main(String[] args) throws Exception {
		ApplicationContext app = new ClassPathXmlApplicationContext("application-jdbctemplate.xml");
		IDeptService service = app.getBean(DeptServiceImpl.class);
		int cnt = service.delDeptByNo(1);
		System.out.println("更新受影响行数："+cnt);
		
		Dept d = service.getDeptByNo(10);
		System.out.println(d);
		
		List<Dept> list1 = service.getDeptsByName("市场");
		for(Dept d1 : list1) {
			System.out.println(d1);
		}
		
		System.out.println("--------------------");
		List<Dept> list2 = service.getAllDetps();
		for(Dept d2 : list2) {
			System.out.println(d2);
		}
		
	}

}
