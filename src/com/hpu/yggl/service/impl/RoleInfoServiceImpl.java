package com.hpu.yggl.service.impl;

import java.util.List;

import com.hpu.yggl.bean.RoleInfo;
import com.hpu.yggl.dao.RoleInfoDao;
import com.hpu.yggl.service.RoleInfoService;

public class RoleInfoServiceImpl implements RoleInfoService {

	private RoleInfoDao roleInfoDao;

	public RoleInfoDao getRoleInfoDao() {
		return roleInfoDao;
	}

	public void setRoleInfoDao(RoleInfoDao roleInfoDao) {
		this.roleInfoDao = roleInfoDao;
	}

	@Override
	public RoleInfo getRoleInfo(String id) {
		// TODO Auto-generated method stub
		return this.getRoleInfoDao().getRole(id);
	}

	@Override
	public void updateRoleInfo(RoleInfo roleInfo) {
		// TODO Auto-generated method stub
		this.getRoleInfoDao().updateRole(roleInfo);
	}

	@Override
	public List<RoleInfo> getAllRole() {
		// TODO Auto-generated method stub
		return this.getRoleInfoDao().getAllRole();
	}

	@Override
	public void deleteRoleInfo(String id) {
		// TODO Auto-generated method stub
		this.getRoleInfoDao().deleteRole(id);
	}

	@Override
	public void addRoleInfo(RoleInfo roleInfo) {
		// TODO Auto-generated method stub
		this.getRoleInfoDao().addRoleInfo(roleInfo);
	}

	@Override
	public List<RoleInfo> findPage(int start, int number) {
		// TODO Auto-generated method stub
		return this.getRoleInfoDao().findPage(start, number);
	}
}
