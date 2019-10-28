package com.mybatis.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mybatis.dao.DeptMapper;
import com.mybatis.pojo.Dept;

public class Demo3 {

	public static void main(String[] args) throws IOException {
		InputStream in = Resources.getResourceAsStream("Sqlconfig.xml");
		SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(in);
		SqlSession session = fac.openSession();
		
		//获取接口类型的对象
		DeptMapper  dm = session.getMapper(DeptMapper.class);
		
		List<Dept> list =  new ArrayList<Dept>();
		
		System.out.println(dm.getAllDept());
		
		
		session.commit();
		session.close();
		
		

	}

}
