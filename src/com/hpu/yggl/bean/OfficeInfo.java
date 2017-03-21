package com.hpu.yggl.bean;

import java.io.Serializable;

public class OfficeInfo implements Serializable {

	private String id;
	private String office;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

}
