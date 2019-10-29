package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.pojo.Student;

public interface IStudentDao {
	
	
	@Select("select * from students where ssex = #{sex}")
	@Results(id="studentMap", value={
		@Result(column="sno", property="id"),
		@Result(column="sname", property="name"),
		@Result(column="ssex", property="sex"),
		@Result(column="sbirthday", property="birthday")
	})
	public List<Student> getStusBySex(String sex);
	
	@Select("select * from students")
	@ResultMap("studentMap")
	public List<Student> getAllStudent();

}
