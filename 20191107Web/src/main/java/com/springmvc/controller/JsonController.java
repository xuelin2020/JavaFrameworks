package com.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springmvc.pojo.User;
import com.springmvc.service.IUserService;

@Controller
@RequestMapping("/json")
public class JsonController {
	@Autowired
	private IUserService service;
	
	
	public void setService(IUserService service) {
		this.service = service;
	}

	@RequestMapping("/getall")
	@ResponseBody
	public List<User> getallUser(){
		return service.getAllUsers();
	}
	
	@RequestMapping("/findUser")
	@ResponseBody
	public User findUserByName(String name) {
		System.out.println(name);
		User user = service.findUserByName(name);
		return user;
	}
	
	@RequestMapping("/testjson")
	@ResponseBody // response.getWriter().print("hello")
	public String getString() {
		return "hello";
	}
	
	@RequestMapping("/getArray")
	@ResponseBody
	public List<String> getJsonArray(){
		List<String> list = new ArrayList<String>();
		list.add("张三");
		list.add("lisi");
		list.add("王五");
		list.add("赵六");
		return list;
	}
	
	@RequestMapping("/getObject")
	@ResponseBody
	public User getObject() {
		//User u = new User(101, "xiaoming", "123456", "小明");
		User u = null;
		return u;
	}
	
}
