package com.template.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.template.dao.IAccountDao;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-jdbctemplate.xml")
public class TestDemo6 {
	@Autowired
	@Qualifier("accountDaoImpl")
	private IAccountDao dao;
	
	@Test
	public void test() {
		int cnt = dao.updateMoney(101, 1000);
		System.out.println(cnt);
	}
}
/*
 * spring集成了Junit单元测试
 * 1）导入Junit的jar， spring-test.jar
 * 2）类上添加2个注解
 * @RunWith(SpringJUnit4ClassRunner.class)  指定使用的spring中的Junit
 * @ContextConfiguration("classpath:application-jdbctemplate.xml") 指定读取的配置文件
 * 3）某个类中的方法进行单元测试，则需要在类中定义属性，并@Autowired
 * 4) 测试的方法使用@Test，就可以进行单元测试了
 * */


