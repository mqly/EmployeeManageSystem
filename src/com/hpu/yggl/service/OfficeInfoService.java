package com.hpu.yggl.service;

import java.util.List;
import com.hpu.yggl.bean.OfficeInfo;

public interface OfficeInfoService {

	OfficeInfo getOfficeInfo(String id);

	void updateOfficeInfo(OfficeInfo officeInfo);

	List<OfficeInfo> getAllOffice();

	void deleteOfficeInfo(String id);

	void addOfficeInfo(OfficeInfo officeInfo);

	List<OfficeInfo> findPage(int start, int number);
}
