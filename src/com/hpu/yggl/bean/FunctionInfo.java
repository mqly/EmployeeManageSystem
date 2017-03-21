package com.hpu.yggl.bean;

import java.io.Serializable;

public class FunctionInfo implements Serializable {

	private String id;
	private String func;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

}
