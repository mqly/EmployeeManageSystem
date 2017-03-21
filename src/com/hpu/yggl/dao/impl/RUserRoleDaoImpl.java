package com.hpu.yggl.dao.impl;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.hpu.yggl.bean.RUserRole;
import com.hpu.yggl.dao.RUserRoleDao;

public class RUserRoleDaoImpl extends HibernateDaoSupport implements RUserRoleDao {

	@Override
	public List<RUserRole> getRURByUserId(String userId) {
		// TODO Auto-generated method stub
		String hql = " from RUserRole rur where rur.userId='" + userId + "'";
		return (List<RUserRole>) this.getHibernateTemplate().find(hql);

	}

	@Override
	public void saveRUR(RUserRole rur) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(rur);
	}

	@Override
	public void deleteRUR(RUserRole rur) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(rur);
	}

	@Override
	public List<RUserRole> getRURByRoleId(String roleId) {
		// TODO Auto-generated method stub
		String hql = " from RUserRole rur where rur.roleId='" + roleId + "'";
		return this.getHibernateTemplate().find(hql);
	}

}
