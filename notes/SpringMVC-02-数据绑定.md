# controller向页面传递数据

- 配置springMVC配置文件，前后缀

- ```
  <
  ```

- Controller 普通类

- ```java
  @Controller
  @RequestMapping("/data")
  //此注解可将数据保存在Session中
  @sessionAttributes({"string","user","list"})
  public class DataController{
    //通过map向jsp传递字符串
    @RequestMapping("/string")
    public String sendString(Map<String,String> map){
  		map.put("string","hello String");
      return "show";
    }
    @RequestMapping("/user")
    public String sendString(Map<String,String> map){
  		User u = new User(101,"tom","123456");
      map.put("user",u);
      return "show";
    }
    @RequestMapping("/list")
     public String sendString(Map<String,List<User>> map){
  		User u1 = new User(101,"tom","123456");
   		User u2 = new User(102,"Mary","223456");
      List<User> list = new ArrayList<User>();
      List.add(u1);
      List.add(u2);
      map.put("user",u);
      return "show";
    }
    
    
     @RequestMapping("/all")
     public String sendString(Map<String,Object> map){
  		User u1 = new User(101,"tom","123456");
   		User u2 = new User(102,"Mary","223456");
      List<User> list = new ArrayList<User>();
       
      map.put("","");
      map.put("","");
      List.add(u1);
      List.add(u2);
      map.put("user",u);
      return "show";
    }
  }
  ```

- opjo 类

- ```
  
  ```

- 页面 show.jsp

- ```jsp
  <%@taglib prefix="c" uri="http://java.sun.com/"@>
    
    
    
    <h2>string = ${string}</h2>
    <h2>user = ${user.uid}-${user.uname}-${user.upwd}</h2>
    <h2>list=
    	<c:forEach items="${list}" var="user" varther
    ${user.uid}-${user.uname}-${user.upwd}
    </h2>
  
    
    
  ```

- Mvc 配置文件，扫描，视图

- 测试

- ```
  项目名/data/list
  项目名/data/list
  项目名/data/list
  //获取全部信息
  项目名/data/all
  ```

- 新建 Controller 测试 第三种 参数绑定

- ```java
  @Controller
  @RequestMapping("/model")
  //此注解可将数据保存在Session中
  @sessionAttributes({"user"})
  public class DataController2{
  @RequestMapping("int")
    public String sendInt(Model model){
      model.addAttribute("string,1000");
      return "show";
    }
    @RequestMapping("user")
     public String sendInt(Model model){   
       User u = new User();
       
       model.addAttribute("string,1000");   
                                        
       return "show";  }
  ```

  

# Spring MVC数据绑定

- 新建 GetDataController.java

- ```java
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
  	}
  }
  ```
  
- jsp

- ```jsp
  <form action="getdata/request" method="post">
  <p>部门编号：<input type="text" name="deptno"></p>
  <p>部门名称：<input type="text" name="dname"></p>
  <p>所在地：<input type="text" name="loc"></p>
<button>添加部门request</button>
  </form>
  ```
  
- 测试

- ```
  /项目名/
  ```

- ## 第二种 绑定简单数据类型

- ```java
  @RequestMapping("/simple")
  public String simpleType(int deptno,String dname,String loc,Model model){
  	Dept d = new Dept();
    
    d.setDeptno(deptno)
      d.
      d.
      
      model.addAttribute("dept",d);
    return "showDept";
  }
  ```

- 测试

- ```html
  /getdata/simple
  ```

- ## 绑定POJO类型

- ```
  
  ```

- jsp

- ```jsp
  <form action="getdata/pojo2" method="post">
    <p>
      
    </p>
  </form>
  ```

  