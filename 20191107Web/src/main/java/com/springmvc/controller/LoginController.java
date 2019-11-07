package com.springmvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springmvc.pojo.User;
import com.springmvc.service.IUserService;

@Controller
@RequestMapping("/login")
@SessionAttributes({"user"})
public class LoginController {
	@Autowired
	private IUserService service;
	
	public void setService(IUserService service) {
		this.service = service;
	}

	@RequestMapping("/{page}")
	public String loginok(@PathVariable("page")String page) {
		return page;
	}
	
	//登录验证
	@RequestMapping(value="/loginCheck", method=RequestMethod.POST)
	public String loginCheck(User user, Model model,HttpSession session) {
		User u = service.checkLogin(user);
		if(u!=null) {
			model.addAttribute("user", u);
			String uri = (String)session.getAttribute("uri");
			if(uri==null) {
				return "loginok";
			}else {
				return "forward:"+uri;
			}
		}else {
			model.addAttribute("error", "用户名或密码错误");
			return "login";
		}
	}

}
