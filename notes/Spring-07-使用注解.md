- 创建web项目

- 引入jar包

- 日志文件

- 创建实体类

- 引入命名空间（插件）

- ```xml
  xmlns:context="http://www.springframework.org/schema/context"
  
  http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.1.xsd">
  ```

- 配置注解扫描

  - 指定要扫描的包

- ```
  	<context:component-scan base-package="com.spring.pojo"></context:component-scan>
  ```

- 在类中使用注解

- ```java
  @Component("person")
  //<bean name="Person" class="com.spring.pojo.Person"></bean>
  public class Person {
  ```

- 其他类别的注解

- ```java
  @Component("person")	//适用于所有组件
  @Service("person")		//适用于service
  @Repository("person")	//适用于持久层
  @Controller("person")	//适用于controller
  ```

- 指定对象的 scope 的属性

- ```java
  @Scope(value = "singleton")
  @Scope(value ="prototype")
  ```

- set 方式注入 value 值

  - @Value("小黄车") 等同于@Value(value = "小黄车")

  - 在私有成员变量中注入	

  - ```java
    @Value("小黄车")
    	private String name;
    ```

  - 在set方法中注入

  - ```java
    @Value("小黄车")
    public void setName(String name) {
    		this.name = name;
    }
    ```

- 自动装配

  - 使用@Autowired 进行自动装配，按照对象类型进行自动装配

- ```java
  @Component("car")
  public class Car {
  	
  	@Value("小黄车")
  	private String name;
  	@Value("blue")
  	private String color;
  ```

  ```java
  @Component("person")
  public class Person {
  	@Value(value = "Hello")
  	private String name;
    
  	@Value("11")
  	private Integer age;
  	
  	@Autowired//自动装配
  	private Car car;
  }
  ```

  - 自动装配存在的问题：如果一个类型有多个对象，那么可以采用以下的方式

    - 方式一：使用@Qualifier 指定具体的对象

    - ```java
      	@Autowired
      	@Qualifier("car2")
      	private Car car;
      ```

    - 方式二：使用 @Resource 指定具体的对象

      ```java
      	@Resource(name="car1")
      	private Car car;
      ```

- ​	初始化方法和销毁方法

- ```java
    @PostConstruct//初始化
  	public void init() {
  		System.out.println("Person被初始化！");
  	}
  	@PreDestroy//销毁
  	public void destroy() {
  		System.out.println("Person被销毁");
  	}
  ```

  