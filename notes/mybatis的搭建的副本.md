## mybatis的搭建

标签：mybatis

****

1、Mybatis开发步骤
1）导入jar（Mybatis.jar Mybatis依赖jar 数据库驱动jar）
2）src导入Mybatis配置文件
3）pojo类
4）dao包中导入Mapper映射文件，编写映射文件

```xml
<mapper namespace="自己定义">
   <xxxxx id="sql唯一标识" resultType="返回类型" paramterType="参数类型" >
     sql  where xxx = #{参数名/属性名}
   </xxxxx>
```

5）Mapper映射文件配置到Mybatis配置文件

5）Mapper映射文件配置到Mybatis配置文件

```xml
  <mappers>
  	 <mapper resource="com/mybatis/dao/DeptMapper.xml"/>
  </mappers>
```

6)编写测试代码

```xml
 //读取Mybatis配置文件
  InputStream in = Resources.getResourceAsStream("Sqlconfig.xml");
  //创建session工厂
  SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(in);
  //获得session
  SqlSession session = fac.openSession();
  //执行mapper文件中的sql
  session.selectOne("namespace.id", 传值)
  session.selectList()
  session.insert()
  session.update()
  session.delete()
  //事务结束
  session.commit();
  //关闭session
  session.close()
```


