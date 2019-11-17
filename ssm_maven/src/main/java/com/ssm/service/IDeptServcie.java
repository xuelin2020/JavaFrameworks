package com.ssm.service;

import java.util.List;

import com.ssm.pojo.Dept;

public interface IDeptServcie {
	public List<Dept> getAllDepts();
	public Dept getDeptByNo(int deptno);
	public int saveDept(Dept dept);
	public int updateDept(Dept dept);
	public int deleteDeptByNo(int deptno);
}
