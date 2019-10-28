## mybatis中接口的使用

标签：mybatis

****

**Contents**

  - [配置文件拓展](#)

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

![image-20191028210953781](/Users/xerxes/Documents/GitHub/JavaFrameworks/notes/img/image-20191028210953781.png)

- 如果不指定外部文件，可以依次在 `<properties>` 标签中配置

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

- 指定外部属性文件  `db.properties `，文件通常和配置文件放在同一目录下

```
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://127.0.0.1:3306/j1903
jdbc.username=root
jdbc.password=root
```

