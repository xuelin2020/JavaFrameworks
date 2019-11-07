package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pojo.User;
@Controller
@RequestMapping("/model")
@SessionAttributes({"user"})
public class DataController2 {
	@RequestMapping("/int")
	public String sendInt(Model model) {
		model.addAttribute("string", 1000);
		return "show";
	}
	@RequestMapping("/user")
	public String sendUser(Model model) {
		User u = new User(222,"Ð¡¶þ","222222");
		model.addAttribute("user", u);
		return "show";
	}

}
