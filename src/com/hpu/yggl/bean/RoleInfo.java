package com.hpu.yggl.bean;

import java.io.Serializable;

public class RoleInfo implements Serializable {

	private String id;
	private String role;

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

}
