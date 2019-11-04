package com.template.test;


import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.template.pojo.Dept;
import com.template.service.IAccountService;
import com.template.service.IDeptService;
import com.template.service.impl.AccountServiceImpl;
import com.template.service.impl.DeptServiceImpl;

public class Demo3 {

	public static void main(String[] args) throws Exception {
		ApplicationContext app = new ClassPathXmlApplicationContext("application-jdbctemplate.xml");
		IAccountService service = (IAccountService)app.getBean("accountServiceImpl");
		
		service.zhuanzhang(102, 101, 100);
		
	}

}
