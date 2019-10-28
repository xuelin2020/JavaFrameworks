## mybatis的搭建

标签：mybatis

****

1. MyBatis的工作流程
   1. 新建动态web项目，拷贝jar到lib文件夹下
      1. 核心jar
      2. 依赖jar
      3. 数据库驱动jar
   2. 配置文件
      1. mybatis配置文件
         1. 连接字符串
         2. 驱动类
         3. 字符串
         4. 密码
      2. ***配置xxx-Mapper.xml（对应表）***
         1. 文件说明：mybatis如果是基于xml，则需把所有要执行的sql语句在此文件配置。
         
         2. 对不同表进行操作要新建DAO层，现在对不同表进行操作要新建`表名Mapper.xml`文件。
         
         3. 配置***Mapper.xml
         
         4. ```xml
            <?xml version="1.0" encoding="UTF-8" ?>
            <!DOCTYPE configuration
            PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-config.dtd">
            <configuration>
            	<properties resource="db.properties"></properties>
            	<settings>
            		<setting name="mapUnderscoreToCamelCase" value="true"/>
            	</settings>
            	
            	<typeAliases>
            		<!--  <typeAlias type="com.mybites.pojo.Emp" alias="emp"/>-->
            		<package name="com.mybites.pojo"/>
            	</typeAliases>
            	<environments default="test">
            		<environment id="test">
            			<!-- 事务 JDBC/ MANAGER-->
            			<transactionManager type="JDBC"></transactionManager>
            			<!-- 数据源  POOLED /NOPOOLED/JNDI-->
            			<dataSource type="POOLED">
            				<property name="driver" value="${jdbc.driverClass}"/>
            				<property name="url" value="${jdbc.url}"/>
            				<property name="username" value="${jdbc.user}"/>
            				<property name="password" value="${jdbc.password}"/>
            			</dataSource>
            		</environment>
            		
            	</environments>
            	
            	<!-- 映射文件 -->
              <mappers>
              	 <mapper resource="com/mybites/mapper/EmpDao.xml"/>
              	<mapper class="com.mybites.mapper.EmpDaoAnnation"/>
              <!--  <package name="com.mybites.mapper"/>-->	
              </mappers>
            </configuration>
            ```
         
            
