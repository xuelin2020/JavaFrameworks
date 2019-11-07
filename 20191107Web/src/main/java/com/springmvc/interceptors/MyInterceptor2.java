package com.springmvc.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor2 implements HandlerInterceptor {

	@Override
	// 请求之前执行的操作，如果返回true继续请求，如果返回false终止请求
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		System.out.println(uri+"之前执行了--preHandle222222()");
		return true;
	}

	@Override
	//请求执行完毕后，执行此方法
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String uri = request.getRequestURI();
		System.out.println(uri+"执行完毕--postHandle22222()");

	}

	@Override
	//视图解析完毕，渲染视图之后，执行此方法
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String uri = request.getRequestURI();
		System.out.println(uri+"渲染完毕--afterCompletion222222()");

	}

}
