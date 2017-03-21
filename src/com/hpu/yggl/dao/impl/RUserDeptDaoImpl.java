package com.hpu.yggl.dao.impl;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.hpu.yggl.bean.RUserDept;
import com.hpu.yggl.dao.RUserDeptDao;

public class RUserDeptDaoImpl extends HibernateDaoSupport implements RUserDeptDao {

	@Override
	public List<RUserDept> getRUDByUserId(String userId) {
		// TODO Auto-generated method stub
		String hql = " from RUserDept rud where rud.userId='" + userId + "'";
		return (List<RUserDept>) this.getHibernateTemplate().find(hql);

	}

	@Override
	public void saveRUD(RUserDept rud) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(rud);
	}

	@Override
	public void deleteRUD(RUserDept rud) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(rud);
	}

	@Override
	public List<RUserDept> getRUDByDeptId(String deptId) {
		// TODO Auto-generated method stub
		String hql = " from RUserDept rud where rud.deptId='" + deptId + "'";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public void updateRUD(RUserDept rUserDept) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(rUserDept);
	}

}
