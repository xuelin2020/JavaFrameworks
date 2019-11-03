使用注解的方式实现IOC

创建一个Student.java	

```java
@Comonpent("student1")
@Scope("prototy")
```

application-anno.xml配置文件

```xml
<!-- 选择context命名空间 设组件的位置 -->
<context:component-scan base-package="com.anno.pojo"></context>
```

测试类

```java
ApplicationContext app = new ClassPathXmlApplicationContext("applcation-anno.xml")
  
  Student stu1 = (student)app.getBean("stdent1")
  Student stu2=(student)app.getBean("stdent2")
```

加不加scope的区别



销毁之前执行的操作或者对应的方法都有对应的注解



文档3.7



装配

book类

```java
@Component("book1")
public class Book{
  @Value("Spring注解使用")
private String bname;
  @Value
private Sting author;

public Book(){}

get set 
tostring
}
```

学生类中使学生有一本书

用@resuoure



```java
@Autowired //自动装配
@Qualifier（"book1"）

```

类名如果和起的名字一样，会装配

不起名系统会自动给个名字为 首字母小写类名



使用自动装配更简单



其他

```java
@comporent（"student1"）
```

生命当前类型要有soring容器管理，后边的是唯一表示

用value复制

引用的话 

@resource 必须给名字 起名



@autowired 自动装配 不用器皿



两个对象不一致

@Q



与IOC相关的注解



对自动装配的对象不一定要提供get set方法

一般要写的 ，不写的话可能也装配上



auto除了能放在属性上，还能放在构造方法上边

