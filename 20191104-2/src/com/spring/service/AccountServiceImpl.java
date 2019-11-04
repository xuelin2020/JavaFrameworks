package com.spring.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.AccountDao;

@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService{
	
	@Resource(name = "accountDao")
	private AccountDao accountDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED,readOnly = false,isolation = Isolation.REPEATABLE_READ)
	public void transfer(Integer from, Integer to, Double money) {
		
		accountDao.subMoney(from, money);
		
		//int a = 9/0;
		
		accountDao.subMoney(to, money);
		
	}

}
