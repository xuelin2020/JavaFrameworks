package com.spring.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("accountDao")
public class AccountDaoImpl implements AccountDao{

	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jt;
	
	
	@Override
	public void addMoney(Integer id, Double money) {
		String sql = "update ar_account set money = money + ? where id = ? ";
		jt.update(sql,money,id);
		
	}

	@Override
	public void subMoney(Integer id, Double money) {
		String sql = "update ar_account set money = money - ? where id = ? ";
		jt.update(sql,money,id);
		
	}

}
