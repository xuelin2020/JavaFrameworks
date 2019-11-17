package cn.qf.smbms.pojo;

import org.apache.ibatis.type.Alias;

@Alias("myaddress")  //通过注解给类命名别名，默认情况下别名和类名一致
public class Address {
	private int id;
	//联系人
	private String contact;
	//地址具体信息
	private String addressDesc;
	//邮编
	private String postCode;
	//电话
	private String tel;
	//所属用户
	private int userId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddressDesc() {
		return addressDesc;
	}
	public void setAddressDesc(String addressDesc) {
		this.addressDesc = addressDesc;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
