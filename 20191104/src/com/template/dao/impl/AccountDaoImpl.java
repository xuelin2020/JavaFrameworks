package com.template.dao.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.template.dao.IAccountDao;
@Repository
public class AccountDaoImpl implements IAccountDao{
	@Autowired
	private JdbcTemplate temp;
	

	public void setTemp(JdbcTemplate temp) {
		this.temp = temp;
	}


	@Override
	public int updateMoney(int aid, double money) {
		String sql = "update account set money = money + ? where aid = ?";
		return temp.update(sql, money, aid);
	}
	
	

}
