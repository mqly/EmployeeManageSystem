package com.hpu.yggl.dao;

import java.util.List;

import com.hpu.yggl.bean.OfficeInfo;

public interface OfficeInfoDao {

	OfficeInfo getOffice(String id);

	void updateOffice(OfficeInfo officeInfo);

	List<OfficeInfo> getAllOffice();

	void deleteOffice(String id);

	void addOfficeInfo(OfficeInfo officeInfo);

	List<OfficeInfo> findPage(int start, int number);

}
