- 创建项目

- 导包

  <img src="/Users/xuelin/Documents/Repositories/JavaFrameworks/notes/img/image-20191105145304608.png" alt="image-20191105145304608" style="zoom:50%;" />

- 配置web.xml

- ![image-20191105145805308](/Users/xuelin/Documents/Repositories/JavaFrameworks/notes/img/image-20191105145805308.png)

- ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" id="WebApp_ID" version="3.1">
    
    <!-- The front controller of this Spring Web application, responsible for handling all application requests -->
  	<servlet>
  		<servlet-name>springDispatcherServlet</servlet-name>
  		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
  		<init-param>
  			<param-name>contextConfigLocation</param-name>
        <!-- 改这！ -->
  			<param-value>/WEB-INF/springMVC-config.xml</param-value>
  		</init-param>
  		<load-on-startup>1</load-on-startup>
  	</servlet>
  
  	<!-- Map all requests to the DispatcherServlet for handling -->
  	<servlet-mapping>
  		<servlet-name>springDispatcherServlet</servlet-name>
            <!-- 改这！ -->
  		<url-pattern>/</url-pattern>
  	</servlet-mapping>
    
    <welcome-file-list>
      <welcome-file>index.jsp</welcome-file>
    </welcome-file-list>
  </web-app>
  ```

- src下创建普通类FirstController.java实现Controller接口

- ```java
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
  
  ```

- 配置springMVC

- ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
  	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  <!-- 自定义controller配置url -->
  <bean name="/springmvc/first" class="com.springmvc.controllr.FirstController"></bean>
  <!-- 指定处理器映射器 -->
  <bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"></bean>
  <!-- 指定处理器适配器 -->
  <bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"></bean>
  <!-- 指定视图解析器 -->
  <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"></bean>
  </beans>
  
  ```

- page文件夹中创建page.jsp测试

- ```java
  <%@ page language="java" contentType="text/html; charset=UTF-8"
      pageEncoding="UTF-8"%>
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  </head>
  <body>
  <h1>Spring MVC</h1>
  msg = ${msg }<br/>
  request.msg = ${requestScope.msg }<br/>
  session.msg = ${sessionScope.msg }<br/>
  application.msg = ${applicationScope.msg }<br/>
  
  </body>
  </html>
  ```

  访问路径为 自定义controller配置url

  ```http
  http://localhost:8080/项目名/springmvc/first
  ```

- ## 注解方式（主要使用方式）

- 创建普通类TwoController.java，注解方式实现类中方法

- ```java
  package com.springmvc.controllr;
  
  import org.springframework.stereotype.Controller;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.servlet.ModelAndView;
  
  @Controller
  @RequestMapping("/springmvc")//父路径可提到类上边
  public class TwoController {
  
  	@RequestMapping("/two")
  	public ModelAndView test() {
  		ModelAndView mv = new ModelAndView();
  		mv.addObject("msg", "1");
  		mv.setViewName("first");
  		return mv;
  	}
  	
  	@RequestMapping("/three")
  	public ModelAndView test3() {
  		ModelAndView mv = new ModelAndView();
  		mv.addObject("msg", "2");
  		mv.setViewName("first");
  		return mv;
  	}
  }
  
  ```

- 创建spring配置文件

- ```xml
  <!-- 组件扫描 -->
  <context:component-scan base-package="com.springmvc.controllr"></context:component-scan>
  <!-- 视图解析器 -->
  <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
  	<property name="prefix" value="/page/"></property>
  	<property name="suffix" value=".jsp"></property>
  </bean>
  ```

  视图解析器

         springmvc中View Resolver负责将处理结果生成View视图，处理器执行完业务逻辑后将带有逻辑视图的ModelAndView返回给前端控制器，前端控制器再将ModelAndView发送给视图解析器，视图解析器（View Resolver）首先根据逻辑视图名解析成物理视图名即具体的页面地址，再生成View视图对象，最后对View进行渲染将处理结果通过页面展示给用户。

  > 视图解析器使用 SpringMVC 框架默认的InternalResourceViewResolver，这个视图解析器支持 JSP 视图解析
  >
  > 在 springmvc.xml 配置文件中配置如下：
  >
  > ```xml
  > <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
  > 
  > <!-- 配置逻辑视图的前缀 -->
  > 
  > <property name="prefix" value="/WEB-INF/jsp/" />
  > 
  > <!-- 配置逻辑视图的后缀 -->
  > 
  > <property name="suffix" value=".jsp" />
  > 
  > </bean>
  > ```
  >
  > 逻辑视图名需要在 controller 中返回的  ModelAndView 中指定，比如逻辑视图名为 ItemList ，则最终返回的jsp视图地址为:
  >
  > “WEB-INF/jsp/itemList.jsp”
  >
  > 最终jsp物理地址：前缀+逻辑视图名+后缀

- 修改xml的配置路径

- 测试

- 实现了一个controller处理不同请求

- @RequestMapping注解提供了一系列属性

  - method

  - ```
    
    ```

    

# maven搭建

- 