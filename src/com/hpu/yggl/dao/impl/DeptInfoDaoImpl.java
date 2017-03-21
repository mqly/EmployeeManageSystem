package com.hpu.yggl.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.hpu.yggl.bean.DeptInfo;
import com.hpu.yggl.bean.UserInfo;
import com.hpu.yggl.dao.DeptInfoDao;

public class DeptInfoDaoImpl extends HibernateDaoSupport implements DeptInfoDao {

	@Override
	public DeptInfo getDept(String id) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(DeptInfo.class, id);
	}

	@Override
	public void updateDept(DeptInfo deptInfo) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(deptInfo);
	}

	@Override
	public List<DeptInfo> getAllDept() {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().loadAll(DeptInfo.class);
	}

	@Override
	public void deleteDept(String id) {
		// TODO Auto-generated method stub
		DeptInfo role = getDept(id);
		this.getHibernateTemplate().delete(role);
	}

	@Override
	public List<DeptInfo> findPage(final int start, final int number) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuffer sbSQL = new StringBuffer("from com.hpu.yggl.bean.DeptInfo");
				Query query = session.createQuery(sbSQL.toString());
				query.setFirstResult(start);
				query.setMaxResults(number);
				System.out.println("size-------------" + query.list().size());
				return query.list();
			}
		});
	}

	@Override
	public void addDeptInfo(DeptInfo deptInfo) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(deptInfo);
	}

}
