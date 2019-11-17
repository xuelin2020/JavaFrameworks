package cn.qf.smbms.util;
//mybatis操作数据库的工具类

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	private static SqlSessionFactory factory;
	//通过静态代码块读取配置文件
	static{
		try {
			//通过流读取配置文件
			InputStream is=Resources.getResourceAsStream("mybatis-config.xml");
			factory=new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//获取session的方法
	public static SqlSession getSqlSession(){
		return MyBatisUtil.factory.openSession();
	}
	//关闭session的方法
	public static void closeSqlSession(SqlSession session){
		if(session!=null){
			session.close();
		}
	}
}










