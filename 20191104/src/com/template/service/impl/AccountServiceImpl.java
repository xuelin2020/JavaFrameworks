package com.template.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.template.dao.IAccountDao;
import com.template.service.IAccountService;
@Service("accountServiceImpl")
@Transactional
public class AccountServiceImpl implements IAccountService {
	@Autowired
	private IAccountDao dao ;
	
	public void setDao(IAccountDao dao) {
		this.dao = dao;
	}

	@Override
	public void zhuanzhang(int chuid, int ruid, double money) {
		dao.updateMoney(chuid, money*-1);
		System.out.println("钱已转出");
		
		dao.updateMoney(ruid, money);
		System.out.println("钱已转入");
		//System.out.println(1/0);
	}

}
