package com.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.pojo.Question;
import com.ssm.service.IQuestionService;

@Controller
public class QuestionController {
	@Autowired
	@Qualifier("questionService")
	private IQuestionService service;
	
	public void setService(IQuestionService service) {
		this.service = service;
	}

	@ResponseBody
	@RequestMapping("/question/find")
	public List<Question> getQuestionByWord(String keyword){
		List<Question> list = service.getQueByKeyword(keyword);
		return list;
	}
	

}
