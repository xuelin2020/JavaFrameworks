package com.springmvc.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//判断session中是否存在user，如果存在继续请求，如果不存在跳转登录页面
		Object obj = request.getSession().getAttribute("user");
		if(obj!=null) {
			return true;
		}else {
			request.setAttribute("error", "请登录后再操作");
			//保存uri
			System.out.println(request.getServletPath());
			request.getSession().setAttribute("uri", request.getServletPath());
			request.getRequestDispatcher("/login/login").forward(request, response);
			return false;
		}
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
