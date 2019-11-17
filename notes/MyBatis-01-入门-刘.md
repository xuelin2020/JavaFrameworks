# 认识mybatis

-    是Apache下的一个开源项目，前身ibatis，半自动化的ORM框架
-    基于SQL的ORM框架
-    特点：基于SQL
- ​         简单易学

# mybatis的搭建

- 新建动态web项目，拷贝jar到lib文件夹下

- 在项目下 新建资源目录  在新建db.properties文件

- 编写数据库的连接信息

- 在resource下，创建mybatis的配置文件

- 名字任意  xml

  - ```xml
    <!-- 固定表头 -->
    <?xml version="1.0" encoding="utf-8"?>
    <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" 
    "http://mybatis.org/dtd/mybatis-3-config.dtd">
    ```

- **mybatis-3-config.dtd：**

  - dtd 文档类型声明：
    - 规定了mybatis的配置文件
    - 里面必须包含哪些标签
    - 标签必须有哪些属性
    - 规定根节点、子节点
    - 用于检测配置语法是否规范

- **需要记忆：mybatis配置的信息都有哪些、配置顺序**

- 官方中文文档https://mybatis.org/mybatis-3/zh/configuration.html

- ![截屏2019-11-07下午11.41.53](/Users/xuelin/Documents/Repositories/JavaFrameworks/notes/img/截屏2019-11-07下午11.41.53.png)

- 创建并配置 mybatis-config.xml

  - ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" 
    "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>
    	<!--1、 引入数据库连接信息的存放文件 -->
    	<properties resource="database.properties"></properties>
    	<!--2、 给实体类命名别名：可以通过别名来使用类，否则需要使用类的完全限定名 -->
    	<typeAliases>
    		<!-- 命名别名方式1 -->
    		<!--
    		<typeAlias type="cn.qf.smbms.pojo.UserRole" alias="UserRole"/>
    		  -->
    		<!-- 方式2： 告知mybatis实体类的位置即可 -->
    		<package name="cn.qf.smbms.pojo"/>
    	</typeAliases>
      
    	<!--3、配置数据库的连接信息  -->
    	<environments default="mysqldb">
    		<environment id="mysqldb">
    			<!-- 
    			配置事务管理机制
    			JDBC:使用JDBC来管理事务   官方推荐
    			MANAGED:由mybatis进行事务管理
    			 -->
    			<transactionManager type="JDBC">
    				<!-- 设置是否自动提交事务: true  自动提交事务      false 非自动提交 -->
    				<property name="autoCommit" value="true"></property>
    			</transactionManager>
    			<!-- 
    			POOLED:JDBC默认的连接管理
    			 -->
    			<dataSource type="POOLED">
    				<!-- 配置mybatis连接数据库信息 -->
    				<!-- 获取数据库连接信息方式1：通过读取配置文件获取 -->
    				<property name="driver" value="${driverClass}"/>
    				<property name="url" value="${url}"/>
    				<property name="username" value="${user}"/>
    				<property name="password" value="${pwd}"/>
    			</dataSource>
    		</environment>
    	</environments>
      
    	<!--4、 配置dao接口的映射文件位置 -->
    	<mappers>
    		<mapper resource="cn/qf/smbms/dao/UserMapper.xml"></mapper>		
    	</mappers>
    </configuration>
    ```

## 封装mybatis操作数据库的工具类

```java
package cn.qf.smbms.util;
//mybatis操作数据库的工具类

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	private static SqlSessionFactory factory;
	//通过静态代码块读取配置文件
	static{
		try {
			//通过流读取配置文件
			InputStream is=Resources.getResourceAsStream("mybatis-config.xml");
			factory=new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//获取session的方法
	public static SqlSession getSqlSession(){
		return MyBatisUtil.factory.openSession();
	}
	//关闭session的方法
	public static void closeSqlSession(SqlSession session){
		if(session!=null){
			session.close();
		}
	}
}
```



### mybatis的工作原理：

1. 通过 mybatis 封装的流读取 mybatis 的配置文件

2. 创建 **SqlSessionFactoryBuilder** 对象

   - 生命周期：局部变量级别
   - 作用：创建 **SqlSessionFactory**

3. 通过步骤2得到 **SqlSessionFactory**

   - 生命周期：和程序一致，只要程序运行 **SqlSessionFactory** 就存在
   - 作用：创建访问数据库的 **session**

4. 通过 **SqlSessionFactory** 得到 **session** 对象

   - 作用：通过 **session** 来完成数据库的增删改查操作
   - 生命周期：当 **session** 的关闭方法调用后，**session** 消失，

   【说明】在session关闭之前可以进行多个SQL操作
                    session是线程级别

###   ORM框架

1. 创建实体类
   - 别名注解   属性名最好和列名一致
2. 编写dao
         【说明】 1、mybatis基于接口编程
                  2、mybatis基于SQL  
                     编写实体类和表映射的文件

​              在映射文件中  通过 namespace 把映射文件和接口进行关联

  作业：新建项目，重新配置
        实现添加 页面实现  修改  删除功能

