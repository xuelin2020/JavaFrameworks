package com.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.ssm.pojo.Question;

public interface IQuestionDao {
	
	//@Select("select * from question where question like '%${kw}%'")
	@Select("select * from question where question like #{kw}")
	public List<Question> getQueByKeyword(String kw);

}
