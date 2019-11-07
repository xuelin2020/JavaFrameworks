# MyBatis的工作原理

- 

# 应用MyBatis增删改查

- 

认识mybatis
   是Apache下的一个开源项目，前身ibatis，半自动化的ORM框架
   基于SQL的ORM框架
   特点：基于SQL
         简单易学

4、mybatis的搭建
   1、新建动态web项目，拷贝jar到lib文件夹下
   2、在项目下 新建资源目录  在新建db.properties文件
      编写数据库的连接信息
   3、在resource下，创建mybatis的配置文件
      名字任意  xml
      mybatis-3-config.dtd   dtd 文档类型声明：规定了mybatis的配置文件
      里面必须包含哪些标签，标签必须有哪些属性

```
  记住：mybatis配置的信息都有哪些、配置顺序
```

   4、封装mybatis操作数据库的工具类
      mybatis的工作原理：
      1、通过mybatis封装的流读取mybatis的配置文件
      2、创建SqlSessionFactoryBuilder 对象
         生命周期：局部变量级别
         作用：创建SqlSessionFactory
      3、通过步骤2得到SqlSessionFactory
         生命周期：和程序一致，只要程序运行SqlSessionFactory就存在
         作用：创建访问数据库的session
      4、通过SqlSessionFactory 得到session对象
         作用：通过session来完成数据库的增删改查操作
         声明周期：当session的关闭方法调用后，session消失，
         【说明】在session关闭之前可以进行多个SQL操作
                 session是线程级别

```
  ORM框架
```

   5、创建实体类
      别名注解   属性名最好和列名一致

   6、编写dao
      【说明】 1、mybatis基于接口编程
               2、mybatis基于SQL  
                  编写实体类和表映射的文件

```
              在映射文件中  通过namespace把映射文件和接口进行关联
```

  作业：新建项目，重新配置
        实现添加 页面实现  修改  删除功能