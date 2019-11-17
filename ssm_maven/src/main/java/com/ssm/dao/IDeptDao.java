package com.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ssm.pojo.Dept;

public interface IDeptDao {
	@Select("select * from dept")
	public List<Dept> getAllDepts();
	@Select("select * from dept where deptno = #{deptno}")
	public Dept getDeptByNo(int deptno);
	@Insert("insert into dept values (#{deptno}, #{dname}, #{loc})")
	public int saveDept(Dept dept);
	@Update("update dept set dname = #{dname}, loc = #{loc} where deptno = #{deptno}")
	public int updateDept(Dept dept);
	@Delete("delete from dept where deptno = #{deptno}")
	public int deleteDeptByNo(int deptno);
}
