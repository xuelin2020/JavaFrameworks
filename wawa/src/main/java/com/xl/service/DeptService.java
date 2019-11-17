package com.xl.service;

import org.apache.ibatis.session.SqlSession;

import com.xl.dao.DeptDao;
import com.xl.pojo.Dept;
import com.xl.util.MyBatisUtil;

public class DeptService {
	public boolean addDept(Dept dept) {
		SqlSession session = null;
		
		int ref=0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			
			ref=session.getMapper(DeptDao.class).addDept(dept);
			
			session.commit();
			
		} catch (Exception e) {
			session.rollback();
		}finally {
			MyBatisUtil.closeSqlSession(session);
		}
		
		if (ref==1) {
			return true;
		}else {
			return false;
		}
	
		
	}
}
