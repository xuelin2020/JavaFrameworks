# Spring MVC的常用注解

## @RequestMapping注解

- 为了简化开发， Spring MVC提供有一系列注解供开发人员使用。除了@Controller、@RequestMapping之外，Spring MVC还提供有@RequestParam、@PathVariable、@CookieValue、@RequestHeader等注解。由于前面的小节已演示过@Controller注解的使用方法，此处不再赘述，接下来开始详细讲解关于@RequestMapping的知识。

- @RequestMapping 用于处理请求地址映射，当 @RequestMapping 用于一个 Controller 类时，表示类中的所有响应请求的方法都是以该注解指定的地址作为父路径，当 @RequestMapping 用于Controller 类中的一个方法时，该方法将成为处理请求的方法。


@RequestMapping注解提供了一系列属性，具体如表所示

| 属性     | 描述                                                         |
| -------- | ------------------------------------------------------------ |
| value    | 指定请求的实际地址                                           |
| method   | 指定请求的实际地址，指定的地址可以是具体地址、可以RestFul动态获取、也可以使用正则设置； |
| consumes | 指定处理请求的提交内容类型                                   |
| produces |                                                              |
| params   |                                                              |
| headers  |                                                              |
| name     |                                                              |

- 表中列举了@RequestMapping注解支持的属性，其中最为常用的是value和method，在实际开发中，开发人员可根据具体需求选择使用。


```java
@Controller
public class MyController03 {
	// 访问page02.jsp
	@RequestMapping(value = "/toWelcome")
	public ModelAndView toWelcome() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("page02");
		return mv;
	}

	// 访问page01.jsp
	@RequestMapping(value = "/welcome", method = RequestMethod.POST)
	public ModelAndView welcome() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "Hello Word");
		mv.setViewName("page01");
		return mv;
	}
}

```

## @RequestParam注解 

- @RequestParam注解用于获取请求参数的值，它可以将请求参数赋值给方法中的形参，进而完成对请求参数的处理。


- @RequestParam注解提供了若干属性，具体如表所示。

- | 属性         | 描述                     |
  | ------------ | ------------------------ |
  | value        | 指定 Coolie 的名称       |
  | required     | 指定参数是否是必须绑定的 |
  | defaultValue | 指定参数的默认值         |

- 表中列举了@RequestParam注解支持的属性，其中最为常用的是value和defaultValue，在实际开发中，开发人员可根据具体需求选择使用。

- ```java
  @Controller
  public class MyController04 {
  // 访问page03.jsp
  @RequestMapping(value = "/toLogin")
  public ModelAndView toLogin() {
  	ModelAndView mv = new ModelAndView();
  	mv.setViewName("page03");
  	return mv;
  }
  
  // 处理登录请求
  @RequestMapping(value = "/login")
  public ModelAndView login(@RequestParam(value = "username", defaultValue = "xiaofeng") String username,
  @RequestParam(value = "password", defaultValue = "123abc") String password) {
  	System.out.println("用户名：" + username);
  	System.out.println("密码：" + password);
  	return null;
  }
  }
  ```


## @PathVariable注解

- @PathVariable注解用于获取URL中的动态参数，它支持动态URL访问并可以将请求URL中的动态参数映射到功能处理方法的形参上。


- @PathVariable注解提供有value、required属性，其中，value属性是最常使用的，它用于指定将要映射的参数名称；required属性用于指定参数是否为必须绑定的参数。

```java
@Controller
@RequestMapping(value = "/claList/{cid}")
public class MyController05 {
	@RequestMapping(value = "/stuList/{sid}")
	public String findStudnt(@PathVariable(value = "cid") Integer cid, @PathVariable(value = "sid") Integer sid) {
		System.out.println("班级ID为：" + cid);
		System.out.println("学生ID为：" + sid);
		return null;
	}
}
```




## @CookieValue注解

- @CookieValue注解用于获取Cookie数据，它可以将Cookie数据映射到功能处理方法的形参上。


- @CookieValue注解提供了若干属性，具体如表所示。

![图片 1](/Users/xuelin/Pictures/图片 1.png)

- 表中列举了@CookieValue注解支持的属性，在实际开发中，开发人员可根据具体需求选择使用。

- ```java
  @Controller
  public class MyController06{
  	@RequestMapping(value="/testCookie")
  	public String testCookie(@CookieValue(value="JSESSIONID")String cookie){
  		System.out.println("JSESSIONID:"+cookie);
  		return null;
  	}
  }
  ```

  


## @RequestHeader注解 

- @RequestHeader注解用于获取请求头中的数据，它可以将请求头中的数据映射到功能处理方法的形参上。

@RequestHeader注解提供的属性和@CookieValue相同

```java
@Controller
public class MyController07 {
	@RequestMapping(value = "/testRequestHeader")
	public String testRequestHeader(@RequestHeader(value = "Host") String host,
			@RequestHeader(value = "Connection") String connection) {
		System.out.println("Host:" + host);
		System.out.println("Connection:" + connection);
		return null;
	}
}

```

# 其他重要操作

## 1. 转发和重定向

转发: forward

```java
@RequestMapping("/user")
@Controller
public class UserContoller {
	
    @RequestMapping("/index")
	public String index(Integer size){
    	System.out.println("index method 被调用!"+size);
		return "forward:/user/result";
	}
    
    @RequestMapping("/result")
    public String result(){
    	return "result";
    }
	

```

重定向语法:

```java
@RequestMapping("/user")
@Controller
public class UserContoller {
	
    @RequestMapping("/index")
	public String index(Integer size){
    	System.out.println("index method 被调用!"+size);
		return "redirect:/user/result";
	}
    
    @RequestMapping("/result")
    public String result(){
    	
    	return "result";
    }
}

```

 

 2. 解决参数乱码问题

  Spring MVC中 GET方式不会乱码!

  在web.xml配置文件中添加spring自带的Filter设置编码格式

```java
  <filter>
    <filter-name>characterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>UTF-8</param-value>
    </init-param>
    <init-param>
      <param-name>forceEncoding</param-name>
      <param-value>true</param-value>
    </init-param>
  </filter>

  <filter-mapping>
    <filter-name>characterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>

```

