package com.template.dao;

import java.util.List;

import com.template.pojo.Dept;

public interface IDeptDao {
	public int saveDept(Dept dept);
	public int delDeptByNo(int no);
	public int updateDept(Dept dept);
	public Dept getDeptByNo(int no);
	public List<Dept> getDeptsByName(String dname);
	public List<Dept> getAllDetps();
	public int updateDeptNoByDno(int newno, int oldno);
}
