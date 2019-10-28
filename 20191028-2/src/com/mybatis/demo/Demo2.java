package com.mybatis.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mybatis.pojo.Dept;

public class Demo2 {

	public static void main(String[] args) throws IOException {
		//读取mybatis配置文件
		InputStream in = Resources.getResourceAsStream("Sqlconfig.xml");
		//创建session工厂
		SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(in);
		//创建session对象
		SqlSession session = fac.openSession();
		
		//获取mapper中一个sql语句，并执行获得结果
		//（“命名空间.语句id”）
		List<Dept> list = session.selectList("first.getAllDept");
		System.out.println(list.size());
		for(Dept d : list) {
			System.out.println(d);
		}
		//提交
		session.commit();
		//关闭会话
		session.close();
	}
}