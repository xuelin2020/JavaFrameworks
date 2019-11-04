package com.spring.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TxTest {

	@Resource(name="accountService")
	private AccountService accountService;
	
	@Test
	public void testTransfer() {
		
		accountService.transfer(1, 2, 100.00);
		
	}
}
