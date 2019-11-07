package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class Controller1 {
	
	@RequestMapping("/interceptor")
	public String testInterceptor() {
		System.out.println("\ttestInterceptor()被执行");
		return "ok";
	}
	
	@RequestMapping("/interceptor3")
	public String testIntercepto3() {
		System.out.println("\ttestInterceptor3()被执行");
		return "ok";
	}
	
	@RequestMapping("/interceptor2")
	public String testInterceptor2() {
		System.out.println("\ttestInterceptor2()被执行");
		return "ok";
	}

}
