package com.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.IQuestionDao;
import com.ssm.pojo.Question;
import com.ssm.service.IQuestionService;
@Service("questionService")
@Transactional
public class QuestionServiceImpl implements IQuestionService {
	@Autowired
	private IQuestionDao dao;
	public void setDao(IQuestionDao dao) {
		this.dao = dao;
	}

	public List<Question> getQueByKeyword(String kw) {
		kw = "%"+kw+"%";
		return dao.getQueByKeyword(kw);
	}
	
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("spring-bean.xml");
		IQuestionService s = (IQuestionService)app.getBean("questionService");
		List<Question> list = s.getQueByKeyword("java");
		System.out.println(list.size());
	}

}
