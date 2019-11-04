package com.template.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.template.dao.IEmpDao;
@Repository
public class EmpDaoImpl implements IEmpDao {
	@Autowired
	private JdbcTemplate temp;
	
	
	public JdbcTemplate getTemp() {
		return temp;
	}


	public void setTemp(JdbcTemplate temp) {
		this.temp = temp;
	}


	@Override
	public int updateEmpDeptnoByDno(int newDno, int oldDno) {
		String sql = "update emp set deptno = ? where deptno =?";
		return temp.update(sql, newDno, oldDno);
	}

}
