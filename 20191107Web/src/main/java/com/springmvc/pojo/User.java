package com.springmvc.pojo;

public class User {
	private int uid;
	private String username;
	private String password;
	private String nick;
	
	public User() {}
	
	public User(int uid, String username, String password, String nick) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.nick = nick;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", nick=" + nick + "]";
	}
	
}
