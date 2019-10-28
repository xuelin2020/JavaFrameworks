## MyBatis-02-映射关系

标签：mybatis

****

# 一、MyBatis关系映射

## 1、主键映射

### 1.1、主键映射的作用

- 当数据插入操作不关心插入后数据的主键（唯一标识），那么建议使用**不返回自增主键**的方式来配置插入语句，这样可以避免额外的SQL开销。

- 当执行插入操作后需要立即获取插入的自增主键值，比如一次操作中保存一对多这种关系的数据，那么就要使用**插入后获取自增主键值**的方式配置

**mybatis 进行插入操作是，如果标的主键是自增的，针对不同的数据库相应的操作也不同。基本上经常会遇到的就是Oracle Sequece 和 MySQL 自增主键，解释如下：**

### 1.2、自动递增

一对多的那种表结构，在插入多端数据时，需要获取刚刚保存了的一段的主键。那么这个时候上述的配置就无法满足需求了。为此我们需要使用 mybatis 提供的 `selectKey`来单独配置针对自增逐渐的处理。

#### 1.2.1、Oracle Sequence 配置

```xml
<sql id='TABLE_NAME'>TEST_USER</sql> 
<sql id='TABLE_SEQUENCE'>SEQ_TEST_USER_ID.nextval</sql>
 <!-- 注意这里需要先查询自增主键值 --> 
<insert id="insert" parameterType="User">     
<selectKey keyProperty="id" resultType="int" order="BEFORE">         select <include refid="TABLE_SEQUENCE" /> from dual     
</selectKey>     
	insert into <include refid="TABLE_NAME" /> (ID,NAME,AGE)         values ( #{id}, #{name}, #{age} ) 
</insert>
```



当使用了 `selecKey` 后，在实际的插入操作时，mybatis 会执行一下两句SQL：

```sql
selec SEQ_TEST_USER_ID.nextval from dual //语句1
inster into(ID,NAME,AGE)value(?,?,?);//语句2
```

在执行插入 **语句2**之前，会先执行语句1以及获取当前的ID值，然后 mybatis 使用反射调用 `User` 对象的 `setId` 方法，将语句1查询出的值保存在 `User`对象中，然后才执行语句2，这样就保证了执行完插入后 `user.id` 不为空

```java
 User user = new User(); 
 user.setName("test"); 
 user.setAge(24);
 userMapper.insert(user); 
 System.out.println(user.id); // user.id 不为空
```

#### 1.2.2、MySQL自增主键配置

针对于 MySQL 这种自己维护主键的数据库，可以直接使用一下配置在插入后获取插入主键，

```mysql
<sql id='TABLE_NAME'>TEST_USER</sql>
<insert id="insert" useGeneratedKeys="true" keyProperty="id" parameterType="User">     
insert into <include refid="TABLE_NAME" /> ( NAME, AGE )         values ( #{name}, #{age} ) 
</insert>
```

当然，由于 MySQL 的自增主键可以通过 SQL 语句`selecLAST_INSERT_ID();`来获取的。因此针对 MySQL ， mybatis 也可配置如下：

```xml
<sql id='TABLE_NAME'>TEST_USER</sql>
<insert id="insert" useGeneratedKeys="true" keyProperty="id" parameterType="User">     
insert into <include refid="TABLE_NAME" /> ( NAME, AGE )         values ( #{name}, #{age} ) 
</insert>
```

只不过该种配置需要额外的一条查询SQL~

### 1.3、非自动递增

如果考虑到插入数据的主键不作为其他表插入数据的外键使用，name可以考虑使用这种方式。

## 2、关系映射

在学习 mybatis 的关系映射前，首先要了解表与表之间的关系。表与表之间的关系主要包括一对一、一对多和多对多等，接下来本节将对这几种关系作详细讲解。

在一对一关系中，一方数据表中的一条记录最多可以和另一方数据表中的一条记录相关。例如，现实生活中学生与校园卡就属于一对一的关系，一个学生只能拥有一张校园卡，一张校园卡只能属于一个学生，如图所示。

![img](/Users/xerxes/Documents/GitHub/JavaFrameworks/notes/img/wpswirjIF.png)

 

在一对多关系中，主键数据表中的一条记录可以和另外一张数据表的多条记录相关，例如，现实生活中班级与学生的关系就属于一对多的关系，一个班级可以有很多学生，一个学生只能属于一个班级，如图所示。

![img](/Users/xerxes/Documents/GitHub/JavaFrameworks/notes/img/wpsQjfZ5Q.png)

 

 

在多对多关系中，两个数据表里的每条记录都可以和另一方数据表里任意数量的记录相关，例如，现实生活中学生与教师就属于多对多的关系，一名学生可以由多名教师授课，一名教师可以为多名学生授课，具体如图所示。

![img](/Users/xerxes/Documents/GitHub/JavaFrameworks/notes/img/wpsWIVzQa.jpg) 

 

如果直接通过SQL语句维护数据表，在维护一对一的表关系时，通常采用在任意一方引入对方的主键作为外键的方式；在维护一对多的表关系时，通常采用在“多”方加入“一”方主键作为外键的方式；在维护多对多的表关系时，通常采用中间表的方式。

2.1、关系映射作用

在现实的项目中进行数据库建模时，我们要遵循数据库设计范式的要求，会对现实中的业务模型进行拆分，封装在不同的数据表中，表与表之间存在着**一对多**或是**多对多**的对应关系。进而，我们对数据库的增删改查操作的主体，也就从单表变成了多表。那么Mybatis中是如何实现这种多表关系的映射呢？

**查询结果集 ResultMap**

​	resultMap 元素是 MyBatis 中最重要最强大的元素。它就是让你远离 90%的需要从结果 集中取出数据的 JDBC 代码的那个东西，而且在一些情形下允许你做一些 JDBC 不支持的事 情。 事实上, 编写相似于对复杂语句联合映射这些等同的代码，也许可以跨过上千行的代码。

#### 2.1.1一对一

在完成数据表设计之后，如果使用MyBatis处理一对一的表关系，需要在MyBatis的映射文件中添加`<association>`元素。    

`<association>`元素是映射文件`<resultMap>`元素的子元素，其配置代码如下所示。

![image-20191029020954578](/Users/xerxes/Documents/GitHub/JavaFrameworks/notes/img/image-20191029020954578.png)

`<association>`元素提供了一系列属性用于维护数据表关系，具体如表所示。

![image-20191029021022780](/Users/xerxes/Documents/GitHub/JavaFrameworks/notes/img/image-20191029021022780.png)

```xml
<resultMap type="stu" id="stuResultMap">
		<!-- id 映射主键 -->
		<id column="sid" property="sid"/>
		<!-- result 映射其他字段 -->
		<result column="sname" property="sname"/>
		<result column="age" property="age"/>
		<result column="course" property="course"/>
		<!-- 一对一关系 -->
		
		<association property="sc" javaType="stuCard">
			<id column="cid" property="cid"/>
			<result column="balance" property="balance"/>
		</association>
	</resultMap>
```

#### 2.1.2、一对多

相比于一对一，一对多的表关系在开发中更为常见，处理过程也略微复杂。通常情况下，如果使用MyBatis处理一对多的关系，需要在MyBatis的映射文件中添加`<collection>`元素。    

`<collection>`元素是映射文件`<resultMap>`元素的子元素，其配置代码如下所示

![img](/Users/xerxes/Documents/GitHub/JavaFrameworks/notes/img/wpsmQN6Es.png) 
在以上配置代码中，<id>元素用于映射主键，<column>元素用于映射普通字段，<collection>元素用于映射一对多的关系。在<collection>的属性中，property属性用于指定POJO对象的成员属性，column属性用于指定对应的字段。

```xml
	<resultMap type="classInfo" id="classResultMap">
		<id column="cid" property="cid"/>
		<result column="cname" property="cname"/>
		<result column="sum" property="sum"/>
		<collection property="stuInfoList" ofType="stuInfo">
			<id column="sid" property="sid"></id>
			<result column="sname" property="sname"/>
			<result column="age" property="age"/>
			<result column="course" property="course"/>
		</collection>
	</resultMap>
```

#### 2.1.3、多对多

通常情况下，多对多表关系要转化为一对多的形式进行处理，这种转化是通过一张中间表来实现的。在使用MyBatis处理多对多关系时，需要先将多对多关系转化为一对多关系，然后使用<collection>元素完成映射。

 

 ***练习案例:***

  我们在数据库中额外创建三张数据表，分别表示销售人员、客户，以及销售和客户多对多的对应关系。每个销售、客户都有一个登录帐号。

```mysql

CREATE TABLE `customer` (
  `customer_id` int(10) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(200) NOT NULL,
  `user_id` int(10) DEFAULT NULL,
  `is_valid` tinyint(4) NOT NULL DEFAULT '1',
  `created_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`customer_id`),
  KEY `customer_name` (`customer_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8；

CREATE TABLE `salesman` (
  `sales_id` int(10) NOT NULL AUTO_INCREMENT,
  `sales_name` varchar(64) NOT NULL,
  `sales_phone` varchar(32) DEFAULT NULL,
  `sales_fax` varchar(32) DEFAULT NULL,
  `sales_email` varchar(100) DEFAULT NULL,
  `user_id` int(10) DEFAULT NULL,
  `report_to` int(10) DEFAULT '0',
  `is_valid` tinyint(4) NOT NULL DEFAULT '1',
  `created_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`sales_id`),
  KEY `sales_name` (`sales_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8；

CREATE TABLE `customer_sales` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `customer_id` int(10) NOT NULL,
  `sales_id` int(10) NOT NULL,
  `created_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `customer_id` (`customer_id`,`sales_id`) USING BTREE,
  KEY `sales_id` (`sales_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8；


​1)实现销售与登录用户一对一关系
2) 实现销售与客户的多对多关系
​


```

