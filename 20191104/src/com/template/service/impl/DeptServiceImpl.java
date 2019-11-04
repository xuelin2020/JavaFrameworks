package com.template.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.template.dao.IDeptDao;
import com.template.dao.IEmpDao;
import com.template.pojo.Dept;
import com.template.service.IDeptService;

@Service
@Transactional
public class DeptServiceImpl implements IDeptService {
	@Autowired
	private IDeptDao dao;
	@Autowired
	private IEmpDao empDao;	
	
	public void setDao(IDeptDao dao) {
		this.dao = dao;
	}
	

	public void setEmpDao(IEmpDao empDao) {
		this.empDao = empDao;
	}


	@Override
	public int saveDept(Dept dept) {
		// TODO Auto-generated method stub
		return dao.saveDept(dept);
	}

	@Override
	public int delDeptByNo(int no) {
		// TODO Auto-generated method stub
		return dao.delDeptByNo(no);
	}

	@Override
	public int updateDept(Dept dept) {
		// TODO Auto-generated method stub
		return dao.updateDept(dept);
	}

	@Override
	public Dept getDeptByNo(int no) {
		// TODO Auto-generated method stub
		return dao.getDeptByNo(no);
	}

	@Override
	public List<Dept> getDeptsByName(String dname) {
		// TODO Auto-generated method stub
		return dao.getDeptsByName(dname);
	}

	@Override
	public List<Dept> getAllDetps() {
		// TODO Auto-generated method stub
		return dao.getAllDetps();
	}

	@Override
	public int updateDeptnoByDno(int newNo, int oldNo) {
		//先更新部门表，再更新员工表
		int cnt=0;
		cnt +=dao.updateDeptNoByDno(newNo, oldNo);
		System.out.println(1/0);
		cnt +=empDao.updateEmpDeptnoByDno(newNo, oldNo);
		return cnt;
		
		
	}

}
