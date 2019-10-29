package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.dao.IStudentDao;
import com.pojo.Student;

public class Demo2 {

	public static void main(String[] args) throws IOException {
		InputStream in = Resources.getResourceAsStream("Sqlconfig.xml");
		SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(in);
		SqlSession session = fac.openSession();
		IStudentDao sd = session.getMapper(IStudentDao.class);
		//List<Student> list = sd.getStusBySex("ÄÐ");
		List<Student> list = sd.getAllStudent();
		for(Student s : list) {
			System.out.println(s);
		}
		session.commit();
		session.close();

	}

}
