package com.hpu.yggl.service.impl;

import java.util.List;
import com.hpu.yggl.bean.RUserDept;
import com.hpu.yggl.dao.RUserDeptDao;
import com.hpu.yggl.service.RUserDeptService;

public class RUseDeptServiceImpl implements RUserDeptService {

	private RUserDeptDao rUserDeptDao;

	public RUserDeptDao getRUserDeptDao() {
		return rUserDeptDao;
	}

	public void setRUserDeptDao(RUserDeptDao rUserDeptDao) {
		this.rUserDeptDao = rUserDeptDao;
	}

	@Override
	public List<RUserDept> getRUDByUserId(String userId) {
		// TODO Auto-generated method stub
		return this.getRUserDeptDao().getRUDByUserId(userId);
	}

	@Override
	public void saveRUD(RUserDept rud) {
		// TODO Auto-generated method stub
		this.getRUserDeptDao().saveRUD(rud);
	}

	@Override
	public void deleteRUD(RUserDept rud) {
		// TODO Auto-generated method stub
		this.getRUserDeptDao().deleteRUD(rud);
	}

	@Override
	public List<RUserDept> getRUDByDeptId(String deptId) {
		// TODO Auto-generated method stub
		return this.getRUserDeptDao().getRUDByDeptId(deptId);
	}

	@Override
	public void updateRUD(RUserDept rUserDept) {
		// TODO Auto-generated method stub
		this.getRUserDeptDao().updateRUD(rUserDept);
	}

}
