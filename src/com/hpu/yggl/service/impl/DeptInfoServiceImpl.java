package com.hpu.yggl.service.impl;

import java.util.List;

import com.hpu.yggl.bean.DeptInfo;
import com.hpu.yggl.bean.UserInfo;
import com.hpu.yggl.dao.DeptInfoDao;
import com.hpu.yggl.service.DeptInfoService;

public class DeptInfoServiceImpl implements DeptInfoService {

	private DeptInfoDao deptInfoDao;

	public DeptInfoDao getDeptInfoDao() {
		return deptInfoDao;
	}

	public void setDeptInfoDao(DeptInfoDao deptInfoDao) {
		this.deptInfoDao = deptInfoDao;
	}

	@Override
	public DeptInfo getDeptInfo(String id) {
		// TODO Auto-generated method stub
		return this.getDeptInfoDao().getDept(id);
	}

	@Override
	public void updateDeptInfo(DeptInfo deptInfoDao) {
		// TODO Auto-generated method stub
		this.getDeptInfoDao().updateDept(deptInfoDao);
	}

	@Override
	public List<DeptInfo> getAllDept() {
		// TODO Auto-generated method stub
		return this.getDeptInfoDao().getAllDept();
	}

	@Override
	public void deleteDeptInfo(String id) {
		// TODO Auto-generated method stub
		this.getDeptInfoDao().deleteDept(id);
	}

	@Override
	public List<DeptInfo> findPage(int start, int number) {
		// TODO Auto-generated method stub
		return this.getDeptInfoDao().findPage(start, number);
	}

	@Override
	public void addDeptInfo(DeptInfo deptInfo) {
		// TODO Auto-generated method stub
		this.getDeptInfoDao().addDeptInfo(deptInfo);
	}
}
