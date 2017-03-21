package com.hpu.yggl.service.impl;

import java.util.List;
import com.hpu.yggl.bean.RUserRole;
import com.hpu.yggl.dao.RUserRoleDao;
import com.hpu.yggl.service.RUserRoleService;

public class RUserRoleServiceImpl implements RUserRoleService {

	private RUserRoleDao rUserRoleDao;

	public RUserRoleDao getRUserRoleDao() {
		return rUserRoleDao;
	}

	public void setRUserRoleDao(RUserRoleDao rUserRoleDao) {
		this.rUserRoleDao = rUserRoleDao;
	}

	@Override
	public List<RUserRole> getRURByUserId(String userId) {
		// TODO Auto-generated method stub
		return this.getRUserRoleDao().getRURByUserId(userId);
	}

	@Override
	public void saveRUR(RUserRole rur) {
		// TODO Auto-generated method stub
		this.getRUserRoleDao().saveRUR(rur);
	}

	@Override
	public void deleteRUR(RUserRole rur) {
		// TODO Auto-generated method stub
		this.getRUserRoleDao().deleteRUR(rur);
	}

	@Override
	public List<RUserRole> getRURByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return this.getRUserRoleDao().getRURByRoleId(roleId);
	}

}
