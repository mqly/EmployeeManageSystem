package com.hpu.yggl.bean;

import java.io.Serializable;

public class UserDetail implements Serializable {

	private String id;
	private String username;
	private String password;
	private String realname;
	private String role;
	private String dept;
	private String office;

	public UserDetail() {

	}

	public UserDetail(String id, String username, String password, String realname, String role, String dept,
			String office) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.role = role;
		this.dept = dept;
		this.office = office;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

}
