package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.tribes.util.Arrays;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pojo.Dept;

@Controller
@RequestMapping("/getdata")
public class GetDataController {
	
	@RequestMapping("/request")
	public String request(HttpServletRequest req, Model model) {
		String deptno = req.getParameter("deptno");
		String dname = req.getParameter("dname");
		String loc = req.getParameter("loc");
		Dept d = new Dept();
		d.setDname(dname);
		d.setLoc(loc);
		d.setDeptno(Integer.parseInt(deptno));
		model.addAttribute("dept", d);
		return "showDept";
	}
	
	@RequestMapping("/simple")
	public String simpleType(@RequestParam("no")int deptno, String dname, String loc, Model model) {
		Dept d = new Dept();
		d.setDeptno(deptno);
		d.setDname(dname);
		d.setLoc(loc);
		System.out.println(d);
		model.addAttribute("dept", d);
		return "showDept";
	}
	
	@RequestMapping("/pojo2")
	public String pojoType2(Dept d, Model model) {
		System.out.println(d);
		model.addAttribute("dept", d);
		return "showDept";
	}
	
	@RequestMapping("/manyarray")
	public String array(@RequestParam("hobby")String[] hobby, Model model) {
		System.out.println(Arrays.toString(hobby));
		model.addAttribute("many", hobby);
		return "showMany";
	}
	
	@RequestMapping("/manylist")
	public String array(@RequestParam("hobby")List<String> hobby, Model model) {
		System.out.println(hobby);
		model.addAttribute("many", hobby);
		return "showMany";
	}


}
