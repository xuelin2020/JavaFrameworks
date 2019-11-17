package cn.qf.smbms.service;

import org.apache.ibatis.session.SqlSession;

import cn.qf.smbms.dao.UserDao;
import cn.qf.smbms.pojo.User;
import cn.qf.smbms.util.MyBatisUtil;

public class UserService {
	public boolean addUser(User user){
		//调用dao中的方法
		//mybatis把dao中的接口及接口的关联文件自动生成实现类，且实现接口中的方法
		//1、通过工具类得到SqlSession
		SqlSession session=null;
		int ret=0;
		try{
			session=MyBatisUtil.getSqlSession();
			ret=session.getMapper(UserDao.class).addUser(user);
			//提交事务
			session.commit();
		}catch(Exception ex){
			//打印异常信息
			ex.printStackTrace();
			//回滚事务
			session.rollback();
		}finally{
			//关闭session
			MyBatisUtil.closeSqlSession(session);
		}
		if(ret==1){
			return true;
		}else{
			return false;
		}
	}
	public boolean updateUser(User user){
		SqlSession session=null;
		int ret=0;
		try{
			session=MyBatisUtil.getSqlSession();
			ret=session.getMapper(UserDao.class).updateUser(user);
			session.commit();
		}catch(Exception ex){
			ex.printStackTrace();
			session.rollback();
		}finally{
			MyBatisUtil.closeSqlSession(session);
		}
		if(ret==1){
			return true;
		}else{
			return false;
		}
	}
}
















