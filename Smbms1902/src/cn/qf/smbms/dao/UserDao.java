package cn.qf.smbms.dao;

import cn.qf.smbms.pojo.User;

//关于smbms_user表的增删改查方法
public interface UserDao {
	//添加方法
	public int addUser(User user);
	//修改方法
	public int updateUser(User user);
	//删除方法
	public int deleteUser(int id);
}
