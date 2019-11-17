package com.xl.pojo;

public class Dept {
	
	private int deptno;
	private String dname;
	private String lco;
	
	public void User() {}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLco() {
		return lco;
	}

	public void setLco(String lco) {
		this.lco = lco;
	}

	@Override
	public String toString() {
		return "User [deptno=" + deptno + ", dname=" + dname + ", lco=" + lco + "]";
	}
	
	

}
