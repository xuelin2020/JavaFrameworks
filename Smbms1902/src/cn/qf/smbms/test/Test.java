package cn.qf.smbms.test;

import cn.qf.smbms.pojo.User;
import cn.qf.smbms.service.UserService;

public class Test {
	UserService us=new UserService();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Test.add();
		Test test=new Test();
		test.update();
	}
	public  void update(){
		User u=new User();
		u.setId(16);
		u.setUserCode("huanhuan");
		u.setUserName("欢欢");
		boolean ret=us.updateUser(u);
		if(ret){
			System.out.println("修改成功！");
		}else{
			System.out.println("修改失败！");
		}
	}
	public void add(){
		
		User  u=new User();
		u.setUserCode("wangcai");
		u.setUserName("某旺财");
		u.setUserPassword("123456");
		boolean ret=us.addUser(u);
		if(ret){
			System.out.println("添加成功");
		}else{
			System.out.println("添加失败");
		}
	}

}
