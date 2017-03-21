package com.hpu.yggl.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.hpu.yggl.bean.RUserOffice;
import com.hpu.yggl.dao.RUserOfficeDao;

public class RUserOfficeDaoImpl extends HibernateDaoSupport implements RUserOfficeDao {

	@Override
	public List<RUserOffice> getRUOByUserId(String userId) {
		// TODO Auto-generated method stub
		String hql = " from RUserOffice ruo where ruo.userId='" + userId + "'";
		return (List<RUserOffice>) this.getHibernateTemplate().find(hql);

	}

	@Override
	public void saveRUO(RUserOffice ruo) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(ruo);
	}

	@Override
	public void deleteRUO(RUserOffice ruo) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(ruo);
	}

	@Override
	public List<RUserOffice> getRUOByOfficeId(String officeId) {
		// TODO Auto-generated method stub
		String hql = " from RUserOffice ruo where ruo.officeId='" + officeId + "'";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public void updateRUO(RUserOffice rUserOffice) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(rUserOffice);
	}

}
