package com.hpu.yggl.service.impl;

import java.util.List;
import com.hpu.yggl.bean.RUserOffice;
import com.hpu.yggl.dao.RUserOfficeDao;
import com.hpu.yggl.service.RUserOfficeService;

public class RUserOfficeServiceImpl implements RUserOfficeService {

	private RUserOfficeDao rUserOfficeDao;

	public RUserOfficeDao getRUserOfficeDao() {
		return rUserOfficeDao;
	}

	public void setRUserOfficeDao(RUserOfficeDao rUserOfficeDao) {
		this.rUserOfficeDao = rUserOfficeDao;
	}

	@Override
	public List<RUserOffice> getRUOByUserId(String userId) {
		// TODO Auto-generated method stub
		return this.getRUserOfficeDao().getRUOByUserId(userId);
	}

	@Override
	public void saveRUO(RUserOffice ruo) {
		// TODO Auto-generated method stub
		this.getRUserOfficeDao().saveRUO(ruo);
	}

	@Override
	public void deleteRUO(RUserOffice ruo) {
		// TODO Auto-generated method stub
		this.getRUserOfficeDao().deleteRUO(ruo);
	}

	@Override
	public List<RUserOffice> getRUOByOfficeId(String officeId) {
		// TODO Auto-generated method stub
		return this.getRUserOfficeDao().getRUOByOfficeId(officeId);
	}

	@Override
	public void updateRUO(RUserOffice rUserOffice) {
		// TODO Auto-generated method stub
		this.getRUserOfficeDao().updateRUO(rUserOffice);
	}

}
