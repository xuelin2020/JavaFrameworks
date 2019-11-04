package com.spring.pojo;

public class Role {
	
	private Integer rid;
	private String rname;
	private String alias;
	
	public Role() {
		super();
	}
	
	
	
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	@Override
	public String toString() {
		return "Role [rid=" + rid + ", rname=" + rname + ", alias=" + alias + "]";
	}
	
	
	
}
