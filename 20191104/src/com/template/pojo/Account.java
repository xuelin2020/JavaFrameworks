package com.template.pojo;

public class Account {
	private int aid;
	private String aname;
	private double money;
	
	public Account() {}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "Account [aid=" + aid + ", aname=" + aname + ", money=" + money + "]";
	}
	
	
}
