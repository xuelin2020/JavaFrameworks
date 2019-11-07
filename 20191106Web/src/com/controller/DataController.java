package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pojo.User;

@Controller
@RequestMapping("/data")
@SessionAttributes({"string","user","list"})
public class DataController {
	
	@RequestMapping("/string")
	public String sendString(Map<String,String> map) {
		map.put("string", "hello String");
		return "show";
	}
	
	@RequestMapping("/user")
	public String sendUser(Map<String,User> map) {
		User u = new User(101, "Tom", "123456");
		map.put("user", u);
		return "show";
	}
	
	@RequestMapping("/list")
	public String sendList(Map<String,List<User>> map) {
		User u1 = new User(101, "Tom", "123456");
		User u2 = new User(102, "mary", "654321");
		List<User> list = new ArrayList<User>();
		list.add(u1);
		list.add(u2);
		map.put("list", list);
		return "show";
	}
	
	@RequestMapping("/all")
	public String sendAll(Map<String,Object> map) {
		User u1 = new User(101, "Tom", "123456");
		User u2 = new User(102, "mary", "654321");
		List<User> list = new ArrayList<User>();
		list.add(u1);
		list.add(u2);
		map.put("string", "��� map");
		map.put("user", u1);
		map.put("list", list);
		return "show";
	}
	

}
