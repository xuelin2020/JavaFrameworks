package com.springmvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/restful")
public class RestFulController {
	
	@RequestMapping("/result")
	public String getResult() {
		return "result";
	}

	//获取 method="get"
	@RequestMapping(value="/dododo", method=RequestMethod.GET)
	public String get(String name, Model model) {
		model.addAttribute("msg", "get请求，获取"+name+"数据");
		return "result";
	}
	
	//新增 method="post"
	@RequestMapping(value="/dododo", method=RequestMethod.POST)
	public String post(String name, Model model) {
		model.addAttribute("msg", "post请求，新增"+name+"数据");
		return "result";
	}
	
	//修改 method="post" <input type="hidden" value="put" name="_method" />
	@RequestMapping(value="/dododo", method=RequestMethod.PUT)
	@ResponseBody
	public void put(String name, Model model, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//model.addAttribute("msg", "put请求，修改"+name+"数据");
		//req.getRequestDispatcher("/WEB-INF/result.jsp").forward(req, res);
		req.getSession().setAttribute("msg", "put请求，修改"+name+"数据");
		res.sendRedirect("result");
	}
	
	//删除 method="post" <input type="hidden" value="delete" name="_method" />
	@RequestMapping(value="/dododo", method=RequestMethod.DELETE)
	@ResponseBody
	public void delete(String name, Model model,HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getSession().setAttribute("msg", "delete请求，删除"+name+"数据");
		res.sendRedirect("result");
	}
}
