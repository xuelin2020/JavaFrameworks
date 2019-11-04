- Spring 的 **TransactionManager** 接口

  - 在不同平台，操作事务的代码各不相同，因此spring提供了一个 TransactionManager 接口：
    -  DateSourceTransactionManager 用于 JDBC 的事务管理
    -  HibernateTransactionManager 用于 Hibernate 的事务管理
    -  JpaTransactionManager 用于 Jpa 的事务管理

- 事务的属性介绍：这里定义了传播行为、隔离级别、超时时间、是否只读

- ```java
  package org.springframework.transaction;
  public interface TransactionDefinition {
      int PROPAGATION_REQUIRED = 0; //支持当前事务，如果不存在，就新建一个
      int PROPAGATION_SUPPORTS = 1; //支持当前事务，如果不存在，就不使用事务
      int PROPAGATION_MANDATORY = 2; //支持当前事务，如果不存在，就抛出异常
      int PROPAGATION_REQUIRES_NEW = 3;//如果有事务存在，挂起当前事务，创建一个新的事物
      int PROPAGATION_NOT_SUPPORTED = 4;//以非事务方式运行，如果有事务存在，挂起当前事务
      int PROPAGATION_NEVER = 5;//以非事务方式运行，如果有事务存在，就抛出异常
      int PROPAGATION_NESTED = 6;//如果有事务存在，则嵌套事务执行
      
      int ISOLATION_DEFAULT = -1;//默认级别，MYSQL: 默认为REPEATABLE_READ级别 SQLSERVER: 默认为READ_COMMITTED
      int ISOLATION_READ_UNCOMMITTED = 1;//读取未提交数据(会出现脏读, 不可重复读) 基本不使用
      int ISOLATION_READ_COMMITTED = 2;//读取已提交数据(会出现不可重复读和幻读)
      int ISOLATION_REPEATABLE_READ = 4;//可重复读(会出现幻读)
      int ISOLATION_SERIALIZABLE = 8;//串行化
      
      int TIMEOUT_DEFAULT = -1;//默认是-1，不超时，单位是秒
  
      //事务的传播行为
      int getPropagationBehavior();
      //事务的隔离级别
      int getIsolationLevel();
      //事务超时时间
      int getTimeout();
      //是否只读
      boolean isReadOnly();
      String getName();
  }
  
  ```

  