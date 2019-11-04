package com.spring.dao;

public interface AccountDao {
	
	void addMoney(Integer id,Double money);
	
	void subMoney(Integer id,Double money);
}
