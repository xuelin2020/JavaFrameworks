package com.springmvc.controllr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/springmvc")
public class TwoController {

	@RequestMapping("/two")
	public ModelAndView test() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "111");
		mv.setViewName("first");
		return mv;
	}
	
	@RequestMapping("/three")
	public ModelAndView test3() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "222");
		mv.setViewName("first");
		return mv;
	}
}
