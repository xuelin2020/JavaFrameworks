package com.springmvc.controllr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class FirstController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "Hello Spring MVC");
		req.getSession().setAttribute("msg", "Hello Spring MVC2");
		
		mv.setViewName("/page/first.jsp");
		return mv;
	}

}
