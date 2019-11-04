package com.spring.jdbctemplate;

import java.beans.PropertyVetoException;
import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import com.pring.utils.DBUtil;
import com.pring.utils.DataSourceUtil;

public class TestJdbcTemplate {
	
	@Test
	public void testSave1() throws Exception{
		
		//准备连接池
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/j1903");
		dataSource.setUser("root");
		dataSource.setPassword("123456");
		
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		String sql = "insert into dept VALUES (default,?,?)";
		
		jt.update(sql,"test1","test1");
	}
	
	@Test
	public void testSave2() throws Exception{
		
		DataSource dataSource = DataSourceUtil.getDataSource();
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		String sql = "insert into dept VALUES (default,?,?)";
		jt.update(sql,"test1","test1");
	}
	
}
