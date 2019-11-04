package com.template.test;

import java.beans.PropertyVetoException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.template.pojo.Dept;

public class Demo1 {

	public static void main(String[] args) throws Exception {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		ds.setDriverClass("com.mysql.jdbc.Driver");
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/j1903");
		ds.setUser("root");
		ds.setPassword("root");
		JdbcTemplate temp = new JdbcTemplate(ds);
		
		//插入
//		String sql = "insert into dept values (default,?,?)";
//		int cnt = temp.update(sql, "培训部","深圳");
//		System.out.println(cnt);
		
		//查询一条记录
//		String sql = "select deptno, dname, loc from dept where deptno = ?";
		
		RowMapper<Dept> mapper = new BeanPropertyRowMapper<Dept>(Dept.class);
//		Dept dept = temp.queryForObject(sql, mapper, 10);
//		System.out.println(dept); 
		
//		String sql = "select dname from dept";
//		List<String> names = temp.queryForList(sql, String.class);
//		for(String n :names) {
//			System.out.println(n);
//		}

		
		String sql = "select deptno, dname name, loc from dept limit ?,?";
		List<Dept> list = temp.query(sql, mapper, 0, 10);
		for(Dept d : list) {
			System.out.println(d);
		}
		
	}

}
