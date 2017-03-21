package com.hpu.yggl.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hpu.yggl.bean.DeptInfo;
import com.hpu.yggl.bean.OfficeInfo;
import com.hpu.yggl.dao.OfficeInfoDao;

public class OfficeInfoDaoImpl extends HibernateDaoSupport implements OfficeInfoDao {

	@Override
	public OfficeInfo getOffice(String id) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(OfficeInfo.class, id);
	}

	@Override
	public void updateOffice(OfficeInfo officeInfo) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(officeInfo);
	}

	@Override
	public List<OfficeInfo> getAllOffice() {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().loadAll(OfficeInfo.class);
	}

	@Override
	public void deleteOffice(String id) {
		// TODO Auto-generated method stub
		OfficeInfo office = getOffice(id);
		this.getHibernateTemplate().delete(office);
	}

	@Override
	public void addOfficeInfo(OfficeInfo officeInfo) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(officeInfo);
	}

	@Override
	public List<OfficeInfo> findPage(final int start, final int number) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuffer sbSQL = new StringBuffer("from com.hpu.yggl.bean.OfficeInfo");
				Query query = session.createQuery(sbSQL.toString());
				query.setFirstResult(start);
				query.setMaxResults(number);
				System.out.println("size-------------" + query.list().size());
				return query.list();
			}
		});
	}

}
