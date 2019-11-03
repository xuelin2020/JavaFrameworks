 # id 和 name 的区别

- name：可重复性，可以有特殊字符

- id：不可重复，不可有特殊字符

  - ```xml
    <bean name="p1" id="p2" class="com.spring.pojo.Person"></bean>
    ```

    ```java
    	@Test
    	public void testIdAndName() {
    		
    		//创建容器
    		ApplicationContext context =	new ClassPathXmlApplicationContext("applicationContext2.xml");
    		//查找对象
    		Person p1 = (Person)context.getBean("p1");
    		Person p2 = (Person)context.getBean("p2");
    		System.out.println(p1);
    		System.out.println(p2);
    	}
    ```

    ![截屏2019-11-03下午5.55.23](/Users/xuelin/Documents/Repositories/JavaFrameworks/notes/img/截屏2019-11-03下午5.55.23.png)

 # 工厂的类型

- 常见有两种类型的工厂

  - ClassPathXmlApplicationContext（从类路径获取文件）

  - FileSystemXmlApplicationContext（从系统磁盘路径获取文件）

    

# 单例和多例

- 配置 scope 属性

  - 默认情况下 bean 是**单例的，scope = “singeton”，最常用**

  - 多例配置，scope="prototye"

  - ```xml
    	<bean name="p1" id="p2" class="com.spring.pojo.Person" scope="prototype" ></bean>
    
    ```

    ```java
    @Test
    	public void testScpoe() {
    		
    		//创建容器
    		ApplicationContext context =	new ClassPathXmlApplicationContext("applicationContext3.xml");
    		//查找对象
    		Person p1 = (Person)context.getBean("p1");
    		Person p2 = (Person)context.getBean("p2");
    		//scope="singleton"或默认时	值是true
    		//scope="prototype"时	值是false
    		System.out.println(p1==p2);
    	}
    ```

    - 测试结果
    - ![截屏2019-11-03下午6.22.11](/Users/xuelin/Documents/Repositories/JavaFrameworks/notes/img/截屏2019-11-03下午6.22.11-2776563.png)

  - scope="request"（一个request范围内是单例，不常用）

  - scope="session"（一个session范围内是单例，不常用）

# 类到底是什么时候创建

- scope="prototype" 时，在容器启动时不创建对象，当获取对象时才创建
- scope="singleton" 时，在容器启动时创建对象，而且只创建一个

# 是否延迟创建

注意：这个配置支队单例模式有效

- lazy-init="true"，延迟创建对象，容器启动时不创建，获取时再创建

- lazy-init="false"，默认值，不延迟创建对象，容器启动时创建

- ```xml
  	<bean name="p3" class="com.spring.pojo.Person" scope="prototype" lazy-init="true"></bean>
  ```

  

# 对象的初始化和销毁方法

- bean的两个属性，如下，方法名自定义无特殊要求

- ```xml
  	<bean name="p3" class="com.spring.pojo.Person" init-method="init" destroy-method="destroy"></bean>
  ```

  - pojo类中添加初始化和销毁方法

  ```java
  	public void init() {
  		System.out.println("Person被初始化！");
  	}
  	
  	public void destroy() {
  		System.out.println("Person被销毁");
  	}
  	
  ```

  - 测试类，在创建容器时初始化对象，调用 init 了方法

  ```java
  @Test
  	public void testInitAndDestroy() {
  		
  		//创建容器
  		ApplicationContext context =	new ClassPathXmlApplicationContext("applicationContext5.xml");
  		
  	}
  ```

  - 测试结果 “Person被初始化”

  - 销毁方法需要主动调用

  - ```java
    @Test
    	public void testInitAndDestroy() {
    		
    		//创建容器
    		AbstractApplicationContext context =	new ClassPathXmlApplicationContext("applicationContext5.xml");
    		//主动调用destory方法执行销毁
    		//context.destroy();
    		//或者调用close方法触发销毁
    		context.close();
    	}
    ```

    <img src="/Users/xuelin/Documents/Repositories/JavaFrameworks/notes/img/截屏2019-11-03下午6.22.11-2778303.png" alt="截屏2019-11-03下午6.22.11" style="zoom:50%;" />