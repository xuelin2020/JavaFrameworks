package com.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.IDeptDao;
import com.ssm.pojo.Dept;
import com.ssm.service.IDeptServcie;
import com.ssm.service.IQuestionService;
@Service("deptServcie")
@Transactional
public class DeptServiceImpl implements IDeptServcie {
	@Autowired
	private IDeptDao deptDao;
	
	
	public IDeptDao getDeptDao() {
		return deptDao;
	}

	public void setDeptDao(IDeptDao deptDao) {
		this.deptDao = deptDao;
	}

	@Override
	public List<Dept> getAllDepts() {
		// TODO Auto-generated method stub
		return deptDao.getAllDepts();
	}

	@Override
	public Dept getDeptByNo(int deptno) {
		// TODO Auto-generated method stub
		return deptDao.getDeptByNo(deptno);
	}

	@Override
	public int saveDept(Dept dept) {
		// TODO Auto-generated method stub
		return deptDao.saveDept(dept);
	}

	@Override
	public int updateDept(Dept dept) {
		// TODO Auto-generated method stub
		return deptDao.updateDept(dept);
	}

	@Override
	public int deleteDeptByNo(int deptno) {
		// TODO Auto-generated method stub
		return deptDao.deleteDeptByNo(deptno);
	}

	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("spring-bean.xml");
		IDeptServcie s = (IDeptServcie)app.getBean("deptServcie");
		//System.out.println(s.getAllDepts().size());
		Dept d = s.getDeptByNo(138);
		//System.out.println(d);
		//d.setDeptno(200);
		//System.out.println(s.saveDept(d));
		System.out.println(s.deleteDeptByNo(200));
		//System.out.println(s.updateDept(d));
	}
}
