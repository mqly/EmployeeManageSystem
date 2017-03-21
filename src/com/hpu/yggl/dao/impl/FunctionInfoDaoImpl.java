package com.hpu.yggl.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.hpu.yggl.bean.FunctionInfo;
import com.hpu.yggl.dao.FunctionInfoDao;

public class FunctionInfoDaoImpl extends HibernateDaoSupport implements FunctionInfoDao {

	@Override
	public FunctionInfo getFunction(String id) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(FunctionInfo.class, id);
	}

	@Override
	public void updateFunction(FunctionInfo functionInfo) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(functionInfo);
	}

	@Override
	public List<FunctionInfo> getAllFunction() {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().loadAll(FunctionInfo.class);
	}

	@Override
	public void deleteFunction(String id) {
		// TODO Auto-generated method stub
		FunctionInfo function = getFunction(id);
		this.getHibernateTemplate().delete(function);
	}

	@Override
	public List<FunctionInfo> findPage(final int start, final int number) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuffer sbSQL = new StringBuffer("from com.hpu.yggl.bean.FunctionInfo");
				Query query = session.createQuery(sbSQL.toString());
				query.setFirstResult(start);
				query.setMaxResults(number);
				// System.out.println("size-------------" +
				// query.list().size());
				return query.list();
			}
		});
	}

	@Override
	public void addFunction(FunctionInfo functionInfo) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(functionInfo);
	}

}
