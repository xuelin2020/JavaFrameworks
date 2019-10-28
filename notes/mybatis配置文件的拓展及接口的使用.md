## mybatis配置文件的拓展及接口的使用

标签：mybatis

****

**Contents**

  - [配置文件拓展](#配置文件的拓展)
  - [小结](#小结)
  - [接口](#接口)
  - [测试类](#测试类)
  - [源码](JavaFrameworks/20191028-3)

****

## 配置文件的拓展

- 当前可以使用一个外部属性文件指定驱动：连接字符串、用户名和密码

```xml
<!--使用外部属性文件-->
	<properties resource="org/mybatis/example/config.properties">
		<property name="username" value="dev_user"/>
		<property name="password" value="F2Fa3!33TYyg"/>
	</properties>
```

- 鼠标放到`<configuration>`标签上按`F2`可看到配置文件的配置顺序

![img](/notes/img/image-20191028210953781.png)

- 不指定外部文件
  - 可以依次在 `<properties>` 标签中配置

```xml
<configuration>
     <!-- 不指定外部文件的编写方法 
					标签内可配置：连接池的初始大小、最大值、等待时间等 -->
	<properties >
		<property name="driver" value="com.mysql.jdbc.Driver"/>
	</properties>
	 
	<environments default="j1903">
		<environment id="j1903">
 			<transactionManager type="JDBC"></transactionManager>
 			<dataSource type="POOLED">
         <!-- value 值从属性中通过 ${} 获取 -->
 				<property name="driver" value="${driver}"/>
 ——————————————————————————————————————————————————————————————————————————————————————
 				<property name="url" value="jdbc:mysql://127.0.0.1:3306/j1903"/>
 				<property name="username" value="root"/>
				<property name="password" value="123456"/>
			</dataSource>
		</environment>
	</environments>
  <mappers>
  	 <mapper resource="com/mybatis/dao/DeptMapper.xml"/>
  </mappers>
</configuration>
```

- 指定外部属性文件  `db.properties `
  
  - 文件通常和配置文件放在同一目录下
  
- ```
  jdbc.driver=com.mysql.jdbc.Driver
  jdbc.url=jdbc:mysql://127.0.0.1:3306/j1903
  jdbc.username=root
  jdbc.password=root
  ```
  

mybatis配置文件

  ```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
  <!-- 配置路径 -->
	<properties resource="db.properties">
	<!--  <property name="driver" value="com.mysql.jdbc.Driver"/>-->	
	</properties>

	<settings>
	 <!-- 打印 mybatis 日志信息 -->
		<setting name="logImpl" value="STDOUT_LOGGING"/>
	</settings>
	
	<typeAliases>
		<!--给数据类型起别名-->
	<package name="com.mybatis.pojo"/>
	</typeAliases>
	
	<environments default="j1903">
		<environment id="j1903">
 			<transactionManager type="JDBC"></transactionManager>
 			<dataSource type="POOLED">
        	<!-- 根据配置文件内容配置属性 -->
				<property name="driver" value="${jdbc.driver}"/>
				<property name="url" value="${jdbc.url}"/>
				<property name="username" value="${jdbc.username}"/>
				<property name="password" value="${jdbc.password}"/>
			</dataSource>
		</environment>
	</environments>
  <mappers>
  <!--  	<mapper resource="com/mybatis/dao/DeptMapper.xml"/>-->
  <mapper class="com.mybatis.dao.DeptMapper"/>
 	<mapper class="com.mybatis.dao.IDeptDao"/>
  </mappers>
</configuration>
  ```

- mybatis设置别名后配置Mapper

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
     <!DOCTYPE mapper
     PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
     "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
     <mapper namespace="first">
     	<!-- 别名为 myDept -->
     	<!-- <typeAlias alias=“myDept” type="com.mybatis.pojo"/> -->
  	<!-- 别名改为了 dept -->
     	<select id="getAllDept" resultType="dept">
     		select * from dept
     	</select>
     </mapper>
  ```

  

## 小结：

- 打印 mybatis 日志信息
  
  - ![img](/notes/img/image-20191028223215815.png)
- 别名
  - `<typeAlias alias=“myDept” type="com.mybatis.pojo"/>` 表示给pojo包内所有类起别名为`myDept`
  - `<package name="com.mybatis.pojo.Dept"/>`表示给pojo包内 Dept 类起别名，为类名首字母小写

- 设置自定义类型转换器

  - ```xml
    	<!--设置自定义类型转换器-->
      	<typeHandlers>
      		<typeHandler handler="org.mybatis.example.ExampleTypeHandler"/>
      	</typeHandlers>
     ```

- 映射配置（两种方式）

  - ```xml
    <!--配置映射-->
    	<mappers>
    		<!--加载mapper文件-->
    		<mapper resource="org/mybatis/builder/AuthorMapper.xml"/>
    		<!--注册mapper接口-->
    		<mapper class="org.mybatis.builder.AuthorMapper"/>
    		<!--注册某个包下的所有接口-->
    		<package name="org.mybatis.builder"/>
    	</mappers>
    ```



## 接口

- 接口的名字与mapper文件名字一致

- 规范中接口和mapper文件放在同一路径中

- 接口中的方法名要和 Mapper 文件中的标签 id 一致 `getAllDept`

  - ```java
    public interface DeptMapper {
    	public List<Dept> getAllDept();
    }
    ```

    ```xml
    	<select id="getAllDept" resultType="dept">
       		select * from dept
     	</select>
    ```

- 映射配置的第二种方式，注册接口

  - ```xml
    <mappers>
    		<!--注册mapper接口-->
    		<mapper class="com.mybatis.dao.DeptMapper"/>
    	</mappers>
    ```

- mapper 文件的中的 namespace=" 接口的全路径" `"com.mybatis.dao.DeptMapper"` ，

  - ```xml
    <?xml version="1.0" encoding="UTF-8" ?>
       <!DOCTYPE mapper
       PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
       "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
       <mapper namespace="com.mybatis.dao.DeptMapper">
       	<!-- 别名为 myDept -->
       	<!-- <typeAlias alias=“myDept” type="com.mybatis.pojo"/> -->
    	<!-- 别名改为了 dept -->
       	<select id="getAllDept" resultType="dept">
       		select * from dept
       	</select>
       </mapper>
    ```

- mybatis配置文件中`<mapper class="接口的全路径"/>`
     也可以中`<package name="包路径"/>`将该包下的所有接口进行注册
     
- `session.getMapper(接口.class)` 返回接口的对象

## 测试类

- ```java
  public class Demo3 {
  
  	public static void main(String[] args) throws IOException {
  		InputStream in = Resources.getResourceAsStream("Sqlconfig.xml");
  		SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(in);
  		SqlSession session = fac.openSession();
  		
  		//获取接口类型的对象
  		DeptMapper  dm = session.getMapper(DeptMapper.class);	
  		List<Dept> list =  new ArrayList<Dept>();	
  		System.out.println(dm.getAllDept());
      
  		session.commit();
  		session.close();
  	}
  }
  
  ```

  