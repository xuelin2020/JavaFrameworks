package com.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.mybatis.pojo.Dept;

public interface DeptMapper {
	
	public List<Dept> getAllDept();
	
	public Dept getDeptByNo(int deptno);
	
	public int addDept(Dept dept);
	
	public int delDeptName(int no);
	
	public int updateDept(Dept dept);
	
	public List<Dept> getDeptByPage(Map<String,Integer> map);
	
}
