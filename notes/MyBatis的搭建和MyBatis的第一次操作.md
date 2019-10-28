## MyBatis的搭建和MyBatis的第一次操作

标签：mybatis

****


**Contents**

- 官方中文文档

- [源码](JavaFrameworks/20191028)

****

MyBatis的工作流程
1. 新建动态web项目，拷贝jar到lib文件夹下
   1. 核心jar包
   2. 依赖jar包
   3. 数据库驱动jar包
2. 配置文件
   1. mybatis配置文件（Sqlconfig.xml）
      ```xml
      <?xml version="1.0" encoding="UTF-8" ?>
      <!DOCTYPE configuration
      PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
      "http://mybatis.org/dtd/mybatis-3-config.dtd">
      <configuration>
      	<!-- 环境 设置默认环境 -->
      	<environments default="j1903">
      		<environment id="j1903">
      			<!-- 事务 JDBC/ MANAGER-->
      			<transactionManager type="JDBC"></transactionManager>
      			<!-- 数据源  使用连接池POOLED / NOPOOLED/JNDI-->
      			<dataSource type="POOLED">
              <!-- 属性 连接驱动 -->
      				<property name="driver" value="com.mysql.jdbc.Driver"/>
              <!-- 属性 连接字符串 -->
      				<property name="url" value="jdbc:mysql://127.0.0.1:3306/j1903"/>
              <!-- 属性 数据库信息 -->
      				<property name="username" value="root"/>
      				<property name="password" value="123456"/>
      			</dataSource>
      		</environment>
      		
      	</environments>
      	
      	<!-- 映射文件 -->
        <mappers>
           <!-- 根据Mapper文件配置映射文件路径 -->
        	 <mapper resource="com/mybatis/dao/DeptMapper.xml"/>
        </mappers>
      </configuration>
      ```
      
   2. 第一个MyBatis操作
   
      1. SQL文件（导入）
      
      2. 根据表创建POJO类
      
   4. ***配置`POJO类名Mapper.xml`（对应表）***

      - 文件说明：mybatis如果是基于xml，则需把所有要执行的sql语句在此文件配置。
   
      ```xml
      <?xml version="1.0" encoding="UTF-8" ?>
         <!DOCTYPE mapper
         PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
         "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
         	<!-- 命名空间，可以随意些，只要不冲突 -->
         <mapper namespace="first">
         	<!-- 使用select查询标签查询表内所有信息 -->
           <!-- 如果自己写方法此方法返回一个集合为Dept类型的值，
         				resultType后边填写返回集合中的元素类型 -->
         	<select id="getAllDept" resultType="com.mybatis.pojo.Dept">
         		select * from dept
         	</select>
           
	      </mapper>
	   <!-- 最后要返回mybatis配置文件配置映射文件路径 -->
	   ```
	5.创建测试类
	   
	   ```java
	   public class Demo1 {
	   
	   	public static void main(String[] args) throws IOException {
	   		//读取mybatis配置文件
	   		InputStream in = Resources.getResourceAsStream("Sqlconfig.xml");
	   		//创建session工厂
	   		SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(in);
	   		//创建session对象
	   		SqlSession session = fac.openSession();
	   		
	   		//获取mapper中一个sql语句，并执行获得结果
	       //（“命名空间.语句id”）
	   		List<Dept> list = session.selectList("first.getAllDept");
	   		System.out.println(list.size());
	   		for(Dept d : list) {
	   			System.out.println(d);
	   		}
	   		//提交
	   		session.commit();
	   		//关闭会话
	   		session.close();
	   	}
	   }
	   
	   ```
	   
	
	6.在Java Application中运行测试类，打印结果
	
	![img](/notes/img/image-20191028200312149.png)
	
	   7.第一个MyBatis操作完成

注意：pojo类中的属性名一定要和表中类名一样，否则查出值为空