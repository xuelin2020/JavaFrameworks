# 创建项目

- 创建web项目

- 导包

- 日志文件

- 创建数据库

- 创建Dao

- ```java
  package com.spring.dao;
  
  import javax.annotation.Resource;
  
  import org.springframework.jdbc.core.JdbcTemplate;
  import org.springframework.stereotype.Repository;
  
  
  @Repository("accountDao")
  public class AccountDaoImpl implements AccountDao{
  
  	
  	@Resource(name="jdbcTemplate")
  	private JdbcTemplate jt;
  	
  	
  	@Override
  	public void addMoney(Integer id, Double money) {
  		String sql = "update ar_account set money = money + ? where id = ? ";
  		jt.update(sql,money,id);
  		
  	}
  
  	@Override
  	public void subMoney(Integer id, Double money) {
  		String sql = "update ar_account set money = money - ? where id = ? ";
  		jt.update(sql,money,id);
  		
  	}
  
  }
  
  ```

  

- 创建Service

- ```java
  package com.spring.service;
  
  import javax.annotation.Resource;
  
  import org.springframework.stereotype.Service;
  
  import com.spring.dao.AccountDao;
  
  @Service("accountService")
  public class AccountServiceImpl implements AccountService{
  	
  	@Resource(name = "accountDao")
  	private AccountDao accountDao;
  
  	@Override
  	public void transfer(Integer from, Integer to, Double money) {
  		
  		accountDao.subMoney(from, money);
  		accountDao.subMoney(to, money);
  		
  	}
  
  }
  
  ```

  

- 创建Spring配置文件

- ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
  	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  	xmlns:aop="http://www.springframework.org/schema/aop"
  	xmlns:context="http://www.springframework.org/schema/context"
  	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
  		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.1.xsd
  		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.1.xsd">
  	
  	<context:component-scan base-package="com.spring.dao"></context:component-scan>
  	<context:component-scan base-package="com.spring.service"></context:component-scan>
  	
  	<context:property-placeholder location="classpath:db.properties" />
  
  	<!-- 连接池 -->
  	<bean name="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
  		<property name="driverClass" value="${jdbc.driver}"></property>
  		<property name="jdbcUrl" value="${jdbc.url}"></property>
  		<property name="user" value="${jdbc.username}"></property>
  		<property name="password" value="${jdbc.password}"></property>
  	</bean>
  	
  	<!-- JdbcTemplate -->
  	<bean name="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
  		<property name="dataSource" ref="dataSource"></property>
  	</bean>
  	
  
  </beans>
  
  ```

- 创建测试用例

- ```java
  package com.spring.service;
  
  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration("classpath:applicationContext.xml")
  public class TxTest {
  
  	@Resource(name="accountService")
  	private AccountService accountService;
  	
  	@Test
  	public void testTransfer() {
  		
  		accountService.transfer(1, 2, 100.00);
  		
  	}
  }
  
  ```

- 测试异常中转账无法成功执行，拟定异常