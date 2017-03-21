package com.hpu.yggl.bean;

import java.io.Serializable;

public class RoleDetail implements Serializable {

	private String id;
	private String role;
	private String func;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

}
