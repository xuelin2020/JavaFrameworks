package cn.qf.smbms.pojo;

import java.util.Date;

public class User {
	private int id;
	private String userCode;
	private String userName;
	private String userPassword;
	private int gender;
	private Date birthday;
	private String phone;
	private String address;//扩展   地址是集合类型  一对多
	private int userRole; //扩展  UserRole类型   一对一
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getUserRole() {
		return userRole;
	}
	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}
}
