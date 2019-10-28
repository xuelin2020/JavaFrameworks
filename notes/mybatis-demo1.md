## Demo1

标签：

****



### 创建Pojo类

手写pojo类

配置deptMapper



无条件删除客户消息记录

selec id = "getAllDept"" resuType

#### 映射PojoMapper.xml





#### Demo1

```java
package com.mybatis.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mybatis.pojo.Dept;

public class Demo1 {

	public static void main(String[] args) throws IOException {
		//读取mybatis配置文件
		InputStream in = Resources.getResourceAsStream("Sqlconfig.xml");
		//创建session工厂
		SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(in);
		//创建session
		SqlSession session = fac.openSession();
		
		//获取mapper中一个sql语句，并执行获得结果
//		List<Dept> list = session.selectList("com.mybatis.dao.getAllDept");
//		System.out.println(list.size());
//		for(Dept d : list) {
//			System.out.println(d);
//		}
		
//		Dept  d = session.selectOne("com.mybatis.dao.getDeptByNo", 999);
//		System.out.println(d);
		
		Dept d = new Dept("研发部","北京");
		int cnt = session.insert("com.mybatis.dao.addDept", d);
		System.out.println(cnt);
		
		//提交
		session.commit();
		//关闭会话
		session.close();
		

	}

}

```

