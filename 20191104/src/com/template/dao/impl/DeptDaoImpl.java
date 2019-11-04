package com.template.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.template.dao.IDeptDao;
import com.template.pojo.Dept;
@Repository
public class DeptDaoImpl implements IDeptDao{
	@Autowired
	private JdbcTemplate temp;
	//private RowMapper<Dept> mapper = new BeanPropertyRowMapper<Dept>(Dept.class);
	public void setTemp(JdbcTemplate temp) {
		this.temp = temp;
	}
	
	private RowMapper<Dept> getMapper(){
		return new BeanPropertyRowMapper<Dept>(Dept.class);
	}

	@Override
	public int saveDept(Dept dept) {
		String sql = "insert into dept values(default, ?,?)";
		return temp.update(sql, dept.getName(), dept.getLoc());
	}

	@Override
	public int delDeptByNo(int no) {
		String sql ="delete from dept where deptno =?";
		return temp.update(sql, no);
	}

	@Override
	public int updateDept(Dept dept) {
		String sql = "update dept set dname = ?, loc=? where deptno =?";
		return temp.update(sql, dept.getName(), dept.getLoc(), dept.getDeptno());
	}

	@Override
	public Dept getDeptByNo(int no) {
		String sql = "select deptno, dname name, loc from dept where deptno = ?";
		return temp.queryForObject(sql, getMapper(), no);
	}

	@Override
	public List<Dept> getDeptsByName(String dname) {
		String sql = "select deptno, dname name, loc from dept where dname like ?";
		return temp.query(sql, getMapper(), "%"+dname+"%");
	}

	@Override
	public List<Dept> getAllDetps() {
		String sql = "select deptno, dname name, loc from dept";
		return temp.query(sql, getMapper());
	}

	@Override
	public int updateDeptNoByDno(int newno, int oldno) {
		String sql = "update dept set deptno =? where deptno =?";
		return temp.update(sql, newno, oldno);
	}
	
}
