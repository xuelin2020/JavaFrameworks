package com.xl.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.xl.pojo.Dept;
import com.xl.service.DeptService;

public class Test {
//	DeptService ds = new DeptService();
//	public void add() {
//		Dept dept = new Dept();
//		dept.setDeptno(99);
//		dept.setDname("www");
//		dept.setLco("啊");
//		boolean ref = ds.addDept(dept);
//		if (ref) {
//			System.out.println("1");
//		}else {
//			System.out.println("2");
//		}
//	}
//	public static void main(String[] args) {
//		Test test = new Test();
//		test.add();
//	}
	
	public static void main(String[] args) throws IOException {
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(in);
		SqlSession session = fac.openSession();
		
		Dept d = new Dept();
		int cnt = session.insert("com.xl.dao.addDept", d);
		System.out.println(cnt);
		
	
	}
}
