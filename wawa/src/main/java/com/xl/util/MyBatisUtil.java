package com.xl.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	
	public static SqlSessionFactory factory;
	
	static {
		
		try {
			InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
			System.out.println(is);
			factory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static SqlSession getSqlSession() {
		return MyBatisUtil.factory.openSession();
	}
	
	public static void closeSqlSession(SqlSession session) {
		if (session!=null) {
			session.close();
		}
	}
	
	
}
