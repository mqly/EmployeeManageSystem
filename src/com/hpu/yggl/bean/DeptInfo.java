package com.hpu.yggl.bean;

import java.io.Serializable;

public class DeptInfo implements Serializable {

	private String id;
	private String dept;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

}
