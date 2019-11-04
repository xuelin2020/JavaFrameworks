- 添加名称空间

- 配置事务管理器

- ```xml
  <!-- 事务管理器 -->
  	<bean name="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
  			<!-- 数据源 -->
  			<property name="dataSource" ref="dataSource"></property>	
  	</bean>
  
  ```

- 配置通知

- ```XML
  	<!-- 通知 -->
  	<tx:advice id="txAdvice" transaction-manager="transactionManager" >
  		<tx:attributes >
  			<!-- 传播行为 -->
  			<!-- 支持当前事务，如果不存在，就创建一个 -->
  			<tx:method name="transfer" propagation="REQUIRED"/>
  			<tx:method name="save*" propagation="REQUIRED"/>
  			<tx:method name="add*" propagation="REQUIRED"/>
  			<tx:method name="insert*" propagation="REQUIRED"/>
  			<tx:method name="create*" propagation="REQUIRED"/>
  			<tx:method name="update*" propagation="REQUIRED"/>
  			<tx:method name="modify*" propagation="REQUIRED"/>
  			<tx:method name="edit*" propagation="REQUIRED"/>
  			<tx:method name="delete*" propagation="REQUIRED"/>
  			<tx:method name="remove*" propagation="REQUIRED"/>
  			<tx:method name="drop*" propagation="REQUIRED"/>
  			<tx:method name="select*" propagation="SUPPORTS"/>
  			<tx:method name="get*" propagation="SUPPORTS"/>
  			<tx:method name="find*" propagation="SUPPORTS"/>
  			<tx:method name="query*" propagation="SUPPORTS"/>
  			<tx:method name="search*" propagation="SUPPORTS"/>
  		</tx:attributes>
  	</tx:advice>
  ```

- 配置切入点

- ```xml
  <!-- 切面（将通知织入切入点） -->
  	<aop:config>
  		<!-- 切入点 -->
  		<aop:pointcut expression="execution(* com.spring.service..*.*(..))" id="txPointcut"/>
  		<!-- 切面 -->
  		<aop:advisor advice-ref="txAdvice" pointcut-ref="txPointcut"/>
  	</aop:config>
  ```

  

