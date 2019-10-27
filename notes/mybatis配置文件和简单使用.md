 ***第三阶段 MyBatis-01-mybatis配置文件和简单使用***

 本节任务

1. MyBatis框架介绍
2. MyBatis的基本数据交互方式
3. MyBatis基础配置
4. MyBatis环境搭建
5. MyBatis的基础CRUD操作

##  一、mybatis配置文件

### 1、概述

#### 1.1传统JDBC的劣势

JDBC是Java程序实现数据访问的基础，它提供了一套操作数据库的API，一般通过加载驱动、获取连接、获取执行者对象、发送SQL语句等步骤实现数据库操作。但是，传统的JDBC编程存在一定的局限性，具体如下。

1）代码繁琐

使用JDBC编程时，代码量较大，尤其是当数据表字段较多时，代码显得烦琐、累赘并且使开发人员的工作量增加。

2）表关系维护复杂

数据表之间存在各种关系，包括一对一、一对多、多对多、级联等。如果采用JDBC编程的方式维护数据表之间的关系，过程较为复杂并且容易出错。

3）硬编码

当使用JDBC编程时，SQL语句都是硬编码到Java程序中，如果改变SQL语句，那么需要重新编译Java代码，不利于系统后期的维护。

4）性能问题

在批量处理数据时，JDBC编程存在效率低下的问题，此时，程序将向数据库发送大批量的同类SQL语句请求，浪费数据库资源，影响运行效率。 

由于JDBC存在的缺陷，企业中通常使用ORM框架来完成数据库的操作。

 

#### 1.2ORM简介

 

ORM的全称是Object-Relation Mapping，即对象-关系映射。ORM是一种规范，它是将简单Java对象（POJO）和数据库表进行映射，使数据库表中的记录和POJO对象一一对应，如图所示。

![img](/notes/img/wpsOKu9wu.jpg) 

在当今企业级应用的开发环境中，对象和关系数据是业务实体的两种表现形式，业务实体在内存中表现为对象，在数据库中表现为关系数据。当采用面向对象的方法开发程序时，一旦需要操作数据库，就必须回到关系数据库的访问方式，这就给开发人员编程带来一系列的困扰。于是，ORM应运而生。ORM可以把关系数据库包装成为面向对象的模型，并成为应用程序和数据库交互的桥梁

 

目前，在Java应用领域流行的ORM框架有Hibernate、MyBatis等，下面对这两种ORM框架做简要介绍。

1）Hibernate

Hibernate是较为优秀的ORM框架之一，已被选为服务器JBoss的持久层解决方案。

Hibernate建立在POJO和数据库表记录的直接映射关系之上，它通过XML映射文件（或注解）提供的规则实现关系映射，提供一种全表映射的模型，程序可以通过POJO直接操作数据库中的数据。

Hibernate封装性较高，开发人员通过XML映射文件（或注解）定义好映射规则后，Hibernate会根据映射规则自动生成SQl语句并调用JDBC的API执行，这减少了开发人员编写SQL语句的烦琐，大大提升开发效率。但是，由于全表映射的特性， Hibernate也存在一些局限，例如，无法根据不同的条件组装不同的SQL，对多表关联和复杂SQL查询支持较差，不能有效支持存储过程和SQL语句优化等。随着互联网行业的发展，Hibernate的局限性逐渐显现并影响其市场份额，目前，MyBatis已成为互联网企业的首选。

 

2）MyBatis

 MyBatis是一种“半自动化”的ORM框架，和Hibernate不同，MyBatis需要手动提供POJO、SQL语句并匹配映射关系，正因为此，它可以更加灵活的生成映射关系。MyBatis充分允许开发人员利用数据库的各项功能，例如存储过程、视图、复杂查询等，具有高度灵活、可优化、易维护等优点。与Hibernate相比，使用MyBatis的编码量较大，但这并不影响它在一些复杂的和需要优化性能的项目中使用。

 

#### 1.3Myatis简介


>  Myatis的前身是Apache组织的一个开源项目iBatis，2010年，iBatis由Apache Software Foundation迁移到了Google Code，并且改名为MyBatis。2013年11月迁移到Github，目前MyBatis由Github维护。

MyBatis 是一款优秀的ORM框架，它支持自定义SQL、存储过程以及高级映射。MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集，它使用XML 文件或注解进行配置和映射，将接口和 Java 的 POJO映射成数据库中的记录。

MyBatis作为ORM框架，其核心思想是剥离出程序中的大量SQL语句并将这些SQL语句配置到映射文件中。因此，使用MyBatis可以根据开发需要灵活编写SQL语句并指定映射规则，同时，程序也和SQL语句分离，实现在不修改程序代码的情况下变更SQL语句，提升了程序的扩展性。

此外，MyBatis支持动态列、动态表名、存储过程，同时提供了简易的日志、缓存和级联功能。

 

下载地址: [MyBatis下载地址](https://github.com/mybatis/mybatis-3/releases)

使用版本:3.4.5

 

### 2.框架特点

* 简单易学：身就很小且简单。没有任何第三方依赖，最简单安装只要两个jar文件+配置几个sql映射文件易于学习，易于使用，通过文档和源代码，可以比较完全的掌握它的设计思路和实现。

* 灵活：mybtis不会对应用程序或者数据库的现有设计强加任何影响。 sql写在xml里，便于统一管理和优化。通过sql基本上可以实现我们不使用数据访问框架可以实现的所有功能，或许更多。

* 解除sql与程代码的耦合：通过提供DAL层，将业务逻辑和数据访问逻辑分离，使系统的设计更清晰，更易维护，更易单元测试。sql和代码的分离，提高了可维护性。

* 提供映射标签，支对象与数据库的orm字段关系映射

* 提供对象关系映射标，支持对象关系组建维护

* 提供xml标签，支持写动态sql

 

### 3. 功能架构 

![img](/notes/img/wpsQAFYku.jpg)

从图中可以看出，MyBatis的功能架构由三层组成，包括API接口层、数据处理层、基础支撑层

 

 

| ****API接口层****   提供给外部使用接口API，开发人员通过这些API来操纵数据库 |
| ------------------------------------------------------------ |
| ****数据处理层****                                           |
| ****基础支撑层****                                           |

 

### 4.MyBatis的工作流程

![img](/notes/img/wpsaD6Qom.jpg) 

图中展示了MyBatis的工作流程，具体来说，可分为以下步骤。

（1）MyBatis读取配置文件和映射文件。其中，配置文件设置了数据源、事务等信息；映射文件设置了SQL执行相关的信息。映射文件要引入到配置文件中才能被执行。

（2）MyBatis根据配置信息和映射信息生成SqlSessionFactory对象，SqlSessionFactory对象的重要功能是创建MyBatis的核心类对象SqlSession。

（3）SqlSession中封装了操作数据库的所有方法，开发者一般通过调用SqlSession完成数据库操作，但实际上，SqlSession并没有直接操作数据库，它通过更底层的Executor执行器接口操作数据库。Executor接口有两个实现类，一个是普通执行器，另外一个是缓存执行器。

（4）Executor执行器将要处理的SQL信息封装到一个MappedStatement对象中。在执行SQL语句前，Executor执行器通过MappedStatement对象将输入的Java 数据映射到SQL语句，在执行SQL语句后，Executor执行器通过MappedStatement对象将SQL语句的执行结果映射为Java数据，其中，作为输入参数和输出结果的映射类型可以为Java基本数据类型，也可为List类型、Map类型或POJO类型。

 

### 5.MyBatis和数据交互的方式

MyBatis和数据库交互方式主要分两种:

 

* 使用传统的MyBatis提供的API;
* 使用Mapper接口;

 

####  5.1 使用传统的MyBatis API

 

   通过调用MyBatis中SqlSession对象的方法从而达到与数据库交互的方式,有一些类似DBUtils的操作!

 

![img](/notes/img/wpsDxNQhm.jpg) 

  上述使用**MyBatis**的方法，是创建一个和数据库打交道的**SqlSession**对象，然后根据Statement Id 和参数来操作数据库，这种方式固然很简单和实用，但是它不符合面向对象语言的概念和面向接口编程的编程习惯。由于面向接口的编程是面向对象的大趋势，**MyBatis**为了适应这一趋势，增加了第二种使用MyBatis

支持接口（**Interface**）调用方式。

####  5.2 使用Mapper接口

  MyBatis将核心配置文件中的每一个<mapper>节点抽象为一个 Mapper 接口，而这个接口中声明的方法和跟`<mapper>`节点中的`<select|update|delete|insert>`节点项对应，即 `<select|update|delete|insert>`

节点的 id 值为 Mapper 接口中的方法名称，parameterType 值表示 Mapper 对应方法的入参类型，而 resultMap 值则对应了Mapper接口表示的返回值类型或者返回结果集的元素类型。

![img](/notes/img/wpsSHxv1I.jpg) 

  根据**MyBatis**的配置规范配置好后，通过SqlSession.getMapper(XXXMapper.class) 方法，**MyBatis**会根据相应的接口声明的方法信息，通过动态代理机制生成一个**Mapper**实例，我们使用**Mapper**接口的某一个方法时，**MyBatis**会根据这个方法的方法名和参数类型，确定**Statement Id**，底层还是通过SqlSession.select("statementId",parameterObject);或者SqlSession.update("statementId",parameterObject);等等来实现对数据库的操作，

**MyBatis**引用**Mapper**接口这种调用方式，纯粹是为了满足面向接口编程的需要。（其实还有一个原因是在于，面向接口的编程，使得用户在接口上可以使用注解来配置SQL语句，这样就可以脱离XML配置文件，实现“0配置”）

### 6.MyBatis配置文件

#### 6.1核心配置文件

​	在classpath下，创建SqlMapConfig.xml文件，该文件为核心配置文件，可以配置当前环境信息，加载映射文件，加载properties文件，配置全局参数，定义别名等。

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<!-- 加载properties文件 
		先加载property子标签的内容，后加载properties文件
			如果名称相同，后边覆盖前边内容
	-->
	<properties resource="jdbc.properties">
		<property name="jdbc.password" value="12345"/>
	</properties>
	
	<!-- 全局参数配置：二级缓存，延迟加载 
	<settings></settings>
	-->
	
	<!-- 定义别名 -->
	<typeAliases>
		<!-- 给单个的类起别名 
		<typeAlias type="com.qf.domain.User" alias="user"/>
		-->
		
		<!-- 给指定包下的类起别名 
			别名的定义规则：类名首字母小写
		-->
		<package name="com.qf.domain"/>
	</typeAliases>
	
	<!-- 配置mybatis的环境信息 -->
	<environments default="development">
		<environment id="development">
			<!-- 配置JDBC事务控制，由mybatis进行管理 -->
			<transactionManager type="JDBC"></transactionManager>
			<!-- 配置数据源，采用mybatis连接池 -->
			<dataSource type="POOLED">
				<property name="driver" value="${jdbc.driver}" />
				<property name="url" value="${jdbc.url}" />
				<property name="username" value="${jdbc.username}" />
				<property name="password" value="${jdbc.password}" />
			</dataSource>
		</environment>
	</environments>
	
	<!-- 加载映射文件 -->
	<mappers>
		<!-- 使用资源的路径 -->
		<mapper resource="User.xml"/>
		<!-- <mapper resource="com/qf/mapper/UserMapper.xml"/> -->
		
		<!--
			使用资源的绝对路径
		 <mapper url=""/> -->
		 
		 <!-- 
		 	Mapper接口的全类名
		 	要求：Mapper接口的名称与映射文件名称一致
		 		必须在同一个目录下
		 <mapper class="com.qf.mapper.UserMapper"/>
		 -->
 
		 <!-- 加载某个包下的映射文件 （推荐）
		 	要求：Mapper接口的名称与映射文件名称一致
		 		必须在同一个目录下
		 -->
		 <package name="com.qf.mapper"/>
	</mappers>
</configuration>
```



#### 6.2映射文件

​	在指定的目录下创建映射文件，配置要执行的statement，即增删改查等语句。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper    
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"    
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- 
	namespace:配置名称空间，对配置的statement进行分类管理
		此时名称可以任意
		当使用Mapper代理时，namespace具有特殊的含义与功能
 -->
<mapper namespace="test">

	<!-- 
	根据id查询用户，User findById(int id)
		select：配置查询语句
			id：可以通过id找到执行的statement，statement唯一标识
			parameterType:输入参数类型
			resultType:输出结果类型
			
			#{}:相当于占位符
			#{id}：其中的id可以表示输入参数的名称，如果是简单类型名称可以任意
	 -->
	<select id="findById" parameterType="int" resultType="com.qf.domain.User" >
		select * from user where id=#{id}
	</select>
	
	<!-- 
	根据用户名称来模糊查询用户信息列表；
		${}:表示拼接sql语句
		${value}：表示输入参数的名称，如果参数是简单类型，参数名称必须是value
	 -->
	 
	 <select id="findByUsername" parameterType="java.lang.String"
	 	resultType="com.qf.domain.User">
	 	select * from user where username like '%${value}%'	
	 </select>
	 
	 <!-- 3、添加用户
	 	#{username}:名称与类中的属性名一致
	  -->
	  <insert id="addUser" parameterType="com.qf.domain.User">
	  	
	  	<!-- 
	  		selectKey:查询主键
	  			keyProperty:主键对应的属性名称
	  			resultType:结果类型，主键的类型
	  			order:在插入记录的之前或之后查询主键的值
	  			
	  			select last_insert_id()
	  				mysql提供 的函数，与insert语句搭配使用，查询主键
	  	 -->
	  	<selectKey keyProperty="id" resultType="int" order="AFTER">
	  		select last_insert_id()
	  	</selectKey>
	  
	  	insert into user(username,sex,birthday,address)
	  		values(#{username},#{sex},#{birthday},#{address})
	  </insert>
	  
	 
	 <!-- 
	 	删除用户
	  -->
	  <delete id="deleteUser" parameterType="int">
	  	delete from user where id=#{id}
	  </delete>
	  
	  
	  <!-- 
	  	修改用户
	   -->
	  <update id="updateUser" parameterType="com.qf.domain.User">
	  	update user set username=#{username},sex=#{sex},birthday=#{birthday},address=#{address}
			where id= #{id}
	  </update>
</mapper>
```

## 二、MyBatis基本应用

### 1、搭建MyBatis技术环境

#### 1.1、环境准备

 

 Jdk环境：jdk1.8

-Ide环境：eclipse oxygen

- 据库环境：MySQL 5.1

- Mbatis：3.4.5

 

####  1.2、下载MyBatis

 

mybaits的代码由github.com管理，下载地址：https://github.com/mybatis/mybatis-3/releases

Mybatis-3.4.5.jar：mybatis的核心包

Mybatis-3.4.5.pdf：mybatis的使用指南

 

#####  1.3、数据库准备

 

```mysql
mysql

CREATE TABLE items (

 id int(11) NOT NULL AUTO_INCREMENT,

 name varchar(32) NOT NULL COMMENT '商品名称',

 price float(10,1) NOT NULL COMMENT '商品定价',

 detail text COMMENT '商品描述',

 pic varchar(64) DEFAULT NULL COMMENT '商品图片',

 createtime datetime NOT NULL COMMENT '生产日期',

 PRIMARY KEY (id)

) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

 

CREATE TABLE user (

 id int(11) NOT NULL AUTO_INCREMENT,

 username varchar(32) NOT NULL COMMENT '用户名称',

 birthday date DEFAULT NULL COMMENT '生日',

 sex char(1) DEFAULT NULL COMMENT '性别',

 address varchar(256) DEFAULT NULL COMMENT '地址',

 PRIMARY KEY (id)

) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

 

CREATE TABLE orders (

 id int(11) NOT NULL AUTO_INCREMENT,

 user_id int(11) NOT NULL COMMENT '下单用户id',

 number varchar(32) NOT NULL COMMENT '订单号',

 createtime datetime NOT NULL COMMENT '创建订单时间',

 note varchar(100) DEFAULT NULL COMMENT '备注',

 PRIMARY KEY (id),

 KEY FK_orders_1 (user_id),

 CONSTRAINT FK_orders_id FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE NO ACTION ON UPDATE NO ACTION

) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

 

CREATE TABLE orderdetail (

 id int(11) NOT NULL AUTO_INCREMENT,

 orders_id int(11) NOT NULL COMMENT '订单id',

 items_id int(11) NOT NULL COMMENT '商品id',

 items_num int(11) DEFAULT NULL COMMENT '商品购买数量',

 PRIMARY KEY (id),

 KEY FK_orderdetail_1 (orders_id),

 KEY FK_orderdetail_2 (items_id),

 CONSTRAINT FK_orderdetail_1 FOREIGN KEY (orders_id) REFERENCES orders (id) ON DELETE NO ACTION ON UPDATE NO ACTION,

 CONSTRAINT FK_orderdetail_2 FOREIGN KEY (items_id) REFERENCES items (id) ON DELETE NO ACTION ON UPDATE NO ACTION

) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

```



#### 1.4、数据准备

```mysql
insert  into items(id,name,price,detail,pic,createtime) values (1,'台式机',3000.0,'该电脑质量非常好！！！！',NULL,'2015-02-03 13:22:53'),(2,'笔记本',6000.0,'笔记本性能好，质量好！！！！！',NULL,'2015-02-09 13:22:57'),(3,'背包',200.0,'名牌背包，容量大质量好！！！！',NULL,'2015-02-06 13:23:02');

insert  into orderdetail(id,orders_id,items_id,items_num) values (1,3,1,1),(2,3,2,3),(3,4,3,4),(4,4,2,3);

insert  into orders(id,user_id,number,createtime,note) values (3,1,'1000010','2015-02-04 13:22:35',NULL),(4,1,'1000011','2015-02-03 13:22:41',NULL),(5,10,'1000012','2015-02-12 16:13:23',NULL);

insert  into user(id,username,birthday,sex,address) values (1,'王五',NULL,'2',NULL),(10,'张三','2014-07-10','1','北京市'),(16,'张小明',NULL,'1','河南郑州'),(22,'陈小明',NULL,'1','河南郑州'),(24,'张三丰',NULL,'1','河南郑州'),(25,'陈小明',NULL,'1','河南郑州'),(26,'王五',NULL,NULL,NULL);
```

 

### 2、获取SqlSession对象

MyBatis框架中涉及到的几个API

| *SqlSssionFactoryBuilder* |该对象负责根据MyBatis配置文件SqlMapConfig.xml构建SqlSessionFactory实例 |
| ---------------------------------- | ------------------------------------------------------------ |
| ***SqlSesionFactory*** | 每一个MyBatis的应用程序都以一个SqlSessionFactory对象为核心。该对象负责创建SqlSession对象实例 |
| ***SqlSessin***        | 该对象包含了所有执行SQL操作的方法，用于执行已映射的SQL语句   |

```java
//1、读取配置文件
String resource = "SqlMapConfig.xml";
InputStream inputStream = Resources.getResourceAsStream(resource);
//2、根据配置文件创建SqlSessionFactory
SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//3、SqlSessionFactory创建SqlSession
SqlSession sqlSession = sqlSessionFactory.openSession();

```

### 3、利用SqlSssion实现CRUD操作

#### 3.1、新增操作

​	在映射文件配置`<insert>`标签，用于执行插入操作。

​	在插入操作完成之前或之后，可以配置`<selectKey>`标签获得生成的主键的值，获得插入之前还是之后的值，可以通过配置`order`属性来指定。

​	LAST_INSERT_ID：该函数是mysql的函数，获取自增主键的ID，它必须配合`insert`语句一起使用

```xml
<!-- 添加用户 -->
     <!-- selectKey：查询主键，在标签内需要输入查询主键的sql -->
     <!-- order：指定查询主键的sql和insert语句的执行顺序，相当于insert语句来说 -->
     <!-- LAST_INSERT_ID：该函数是mysql的函数，获取自增主键的ID，它必须配合insert语句一起使用 -->
     
<insert id="addUser" parameterType="com.qf.po.User”>

<selectKey keyProperty="id" resultType="int" order="AFTER">
	SELECT LAST_INSERT_ID() 
</selectKey>

INSERT INTO USER(username,birthday,sex,address)
        VALUES(#{username},#{birthday},#{sex},#{address})
</insert>	
```

​	测试代码如下，直接执行配置的statement，可以查看结果。
```java
java
SqlSession session = sqlSessionFactory.openSession();
session.insert("test.addUser",user);
session.commit();
```

####  3.2、删除操作

​	在映射文件文件中使用 `<delete>`标签配置删除的 statement。 

```xml
<delete id="deleteUser" parameterType="int">
	  	delete from user where id=#{id}
</delete>
```

 	测试代码如下，直接执行配置的statement，可以查看结果。

```java
java
SqlSession session = sqlSessionFactory.openSession();
session.delete("test.deleteUser",1);
session.commit();
```



####  3.3、修改操作

​	在映射文件使用`<update>`标签配置修改的statement。

```xml
<update id="updateUser" parameterType="com.qf.domain.User">
	  	update user set username=#{username},sex=#{sex},birthday=#{birthday},address=#{address}  where id= #{id}
</update>
```

​	测试代码如下，直接执行配置的statement，可以查看结果。


```java
java
 //构建user参数，没有赋值的属性采取默认值
    User user = new User();
    user.setId(20);
    user.setUsername("admin");
    user.setAddress("beijing");
      
    // 第一个参数：statement的id，建议：namespace.statementId（确保唯一）
    // 第二个参数：入参的值，它的类型要和映射文件中对应的statement的入参类型一致
    sqlSession.update("test.updateUser", user);

```



#### 3.4、查询操作 

​	在映射文件配置`<select>`标签执行查询操作。

​	注意：

- `{}`:相当于占位符	

 `{id}`：其中的 id 可以表示输入参数的名称，如果是简单类型名称可以任意

- `${}`:表示拼接sql句

 `${value}`：表示输入参数的名称，如果参数是简单类型，参数名称必须是 value	

```xml
<!-- 
	根据id查询用户，User findById(int id)
		select：配置查询语句
			id：可以通过id找到执行的statement，statement唯一标识
			parameterType:输入参数类型
			resultType:输出结果类型
			
			#{}:相当于占位符
			#{id}：其中的id可以表示输入参数的名称，如果是简单类型名称可以任意
	 -->
	<select id="findById" parameterType="int" resultType="com.qf.domain.User" >
		select * from user where id=#{id}
	</select>
	
	<!-- 
	根据用户名称来模糊查询用户信息列表；
		${}:表示拼接sql语句
		${value}：表示输入参数的名称，如果参数是简单类型，参数名称必须是value
	 -->
	 
	 <select id="findByUsername" parameterType="java.lang.String"
	 	resultType="com.qf.domain.User">
	 	select * from user where username like '%${value}%'	
	 </select>
	 
```

​	测试代码如下，直接执行配置的statement，可以查看结果。

```java
java
    public User findById(int id) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		return session.selectOne("test.findById", id);
	}

	@Override
	public List<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		return session.selectList("test.findByUsername", username);
	}
```

 

### 4、利用MyBatis现分页查询

#### 4.1、使用map

注意：map的key要和sql中的占位符保持名字一致

```xml
mapper：
<!-- 分页：map传参 -->
     <select id="selectAuthorByPage" resultMap="authorResultMap">
         SELECT * FROM AUTHOR LIMIT #{offset}, #{pagesize}
     </select>
```

接口：
```java
/**
     * 根据分页参数查询
     * @param paramList 分页参数
     * @return 分页后的用户列表
     */
    List<Author> selectAuthorByPage(Map<String, Object> paramList);
```
测试：
```
@Test
    public void testSelectAuthorByPage(){
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("offset", 0);
        map.put("pagesize", 2);
        
        AuthorMapper authorDao = session.getMapper(AuthorMapper.class);
        List<Author> authorList = authorDao.selectAuthorByPage(map);
        
        for (int i = 0; i < authorList.size(); i++) {
            System.out.println(authorList.get(i));
        }
    }
```



####  4.2、使用注解

注意：mapper文件中的参数占位符的名字一定要和接口中参数的注解保持一致


**mapper：**
```xml
<!-- 分页：注解传参 -->
    <select id="selectAuthorByPage2" resultMap="authorResultMap">
        SELECT * FROM AUTHOR LIMIT #{offset}, #{pagesize}
    </select>
```

**接口：**
```java
/**
     * 根据分页参数查询
     * @param offset 偏移量
     * @param pagesize 每页条数
     * @return 分页后的用户列表
     */
    List<Author> selectAuthorByPage2(
            @Param(value="offset")int offset, 
            @Param(value="pagesize")int pagesize
    );
```

**测试：**
```java
@Test
    public void testSelectAuthorByPage2(){
        
        AuthorMapper authorDao = session.getMapper(AuthorMapper.class);
        List<Author> authorList = authorDao.selectAuthorByPage2(0, 2);
        
        for (int i = 0; i < authorList.size(); i++) {
            System.out.println(authorList.get(i));
            System.out.println("----------------------");
        }
    }
```

 

####  4.3、使用序号

 

注意：mapper文件中参数占位符的位置编号一定要和接口中参数的顺序保持一致




**mapper：**
```xml
<!-- 分页：序号传参 -->
    <select id="selectAuthorByPage3" resultMap="authorResultMap">
        SELECT * FROM AUTHOR LIMIT #{0}, #{1}
    </select>
```

**接口：**
```java
/**
     * 根据分页参数查询
     * @param offset 偏移量
     * @param pagesize 每页条数
     * @return 分页后的用户列表
     */
    List<Author> selectAuthorByPage3(int offset, int pagesize);
```

**测试：**
```java
@Test
    public void testSelectAuthorByPage3(){
        
        AuthorMapper authorDao = session.getMapper(AuthorMapper.class);
        List<Author> authorList = authorDao.selectAuthorByPage3(1, 1);
        
        for (int i = 0; i < authorList.size(); i++) {
            System.out.println(authorList.get(i));
            System.out.println("----------------------");
        }
    }

```

  

### 5、使用Mapper映射

Mapper代理的开发方式，程序员只需要编写mapper接口（相当于dao接口）即可。Mybatis会自动的为mapper接口生成动态代理实现类。

不过要实现mapper代理的开发方式，需要遵循一些开发规范。

####  5.1、开发规范

- mapper接口的全限定名要mapper映射文件的namespace的值相同。

- mapper接口的方法名称要和apper映射文件中的statement的id相同；

- mapper接口的方法参数只能有个，且类型要和mapper映射文件中statement的parameterType的值保持一致。

- mapper接口的返回值类型要和mpper映射文件中statement的resultType值或resultMap中的type值保持一致；

 
