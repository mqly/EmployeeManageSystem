package com.hpu.yggl.dao.impl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.hpu.yggl.bean.RoleInfo;
import com.hpu.yggl.dao.RoleInfoDao;

public class RoleInfoDaoImpl extends HibernateDaoSupport implements RoleInfoDao {

	@Override
	public RoleInfo getRole(String id) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(RoleInfo.class, id);
	}

	@Override
	public void updateRole(RoleInfo roleInfo) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(roleInfo);
	}

	@Override
	public List<RoleInfo> getAllRole() {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().loadAll(RoleInfo.class);
	}

	@Override
	public void deleteRole(String id) {
		// TODO Auto-generated method stub
		RoleInfo role = getRole(id);
		this.getHibernateTemplate().delete(role);
	}

	@Override
	public void addRoleInfo(RoleInfo roleInfo) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(roleInfo);
	}

	@Override
	public List<RoleInfo> findPage(final int start, final int number) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuffer sbSQL = new StringBuffer("from com.hpu.yggl.bean.RoleInfo");
				Query query = session.createQuery(sbSQL.toString());
				query.setFirstResult(start);
				query.setMaxResults(number);
				System.out.println("size-------------" + query.list().size());
				return query.list();
			}
		});
	}

}
