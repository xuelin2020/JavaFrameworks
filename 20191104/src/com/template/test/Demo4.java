package com.template.test;


import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.template.pojo.Dept;
import com.template.service.IAccountService;
import com.template.service.IDeptService;
import com.template.service.impl.AccountServiceImpl;
import com.template.service.impl.DeptServiceImpl;

public class Demo4 {

	public static void main(String[] args) throws Exception {
		ApplicationContext app = new ClassPathXmlApplicationContext("application-jdbctemplate.xml");
		IDeptService service = (IDeptService)app.getBean("deptServiceImpl");
		
		int cnt = service.updateDeptnoByDno(10, 100);
		System.out.println(cnt);
		
	}

}
