package com.ssm.service;

import java.util.List;

import com.ssm.pojo.Question;

public interface IQuestionService {
	public List<Question> getQueByKeyword(String kw);
}
