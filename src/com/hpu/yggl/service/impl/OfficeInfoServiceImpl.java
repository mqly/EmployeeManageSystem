package com.hpu.yggl.service.impl;

import java.util.List;
import com.hpu.yggl.bean.OfficeInfo;
import com.hpu.yggl.dao.OfficeInfoDao;
import com.hpu.yggl.service.OfficeInfoService;

public class OfficeInfoServiceImpl implements OfficeInfoService {

	private OfficeInfoDao officeInfoDao;

	public OfficeInfoDao getOfficeInfoDao() {
		return officeInfoDao;
	}

	public void setOfficeInfoDao(OfficeInfoDao officeInfoDao) {
		this.officeInfoDao = officeInfoDao;
	}

	@Override
	public OfficeInfo getOfficeInfo(String id) {
		// TODO Auto-generated method stub
		return this.getOfficeInfoDao().getOffice(id);
	}

	@Override
	public void updateOfficeInfo(OfficeInfo officeInfo) {
		// TODO Auto-generated method stub
		this.getOfficeInfoDao().updateOffice(officeInfo);
	}

	@Override
	public List<OfficeInfo> getAllOffice() {
		// TODO Auto-generated method stub
		return this.getOfficeInfoDao().getAllOffice();
	}

	@Override
	public void deleteOfficeInfo(String id) {
		// TODO Auto-generated method stub
		this.getOfficeInfoDao().deleteOffice(id);
	}

	@Override
	public void addOfficeInfo(OfficeInfo officeInfo) {
		// TODO Auto-generated method stub
		this.getOfficeInfoDao().addOfficeInfo(officeInfo);
	}

	@Override
	public List<OfficeInfo> findPage(int start, int number) {
		// TODO Auto-generated method stub
		return this.getOfficeInfoDao().findPage(start, number);
	}
}
