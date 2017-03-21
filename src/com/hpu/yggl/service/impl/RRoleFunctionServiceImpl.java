package com.hpu.yggl.service.impl;

import java.util.List;
import com.hpu.yggl.bean.RRoleFunction;
import com.hpu.yggl.bean.RoleInfo;
import com.hpu.yggl.dao.RRoleFunctionDao;
import com.hpu.yggl.service.RRoleFunctionService;

public class RRoleFunctionServiceImpl implements RRoleFunctionService {

	private RRoleFunctionDao rRoleFunctionDao;

	public RRoleFunctionDao getRRoleFunctionDao() {
		return rRoleFunctionDao;
	}

	public void setRRoleFunctionDao(RRoleFunctionDao rRoleFunctionDao) {
		this.rRoleFunctionDao = rRoleFunctionDao;
	}

	@Override
	public List<RRoleFunction> getRRFByFunctionId(String functionId) {
		// TODO Auto-generated method stub
		return this.getRRoleFunctionDao().getRRFByFunctionId(functionId);
	}

	@Override
	public void saveRRF(RRoleFunction rrf) {
		// TODO Auto-generated method stub
		this.getRRoleFunctionDao().saveRRF(rrf);
	}

	@Override
	public void deleteRRF(RRoleFunction rrf) {
		// TODO Auto-generated method stub
		this.getRRoleFunctionDao().deleteRRF(rrf);
	}

	@Override
	public List<RRoleFunction> getRRFByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return this.getRRoleFunctionDao().getRRFByRoleId(roleId);
	}

	@Override
	public List<RRoleFunction> getAllRRF() {
		// TODO Auto-generated method stub
		return this.getRRoleFunctionDao().getAllRRF();
	}

	@Override
	public List<RRoleFunction> findPage(int start, int number) {
		// TODO Auto-generated method stub
		return this.getRRoleFunctionDao().findPage(start, number);
	}

	@Override
	public void updateRRF(RRoleFunction rRoleFunction) {
		// TODO Auto-generated method stub
		this.getRRoleFunctionDao().udpateRRF(rRoleFunction);
	}

}
