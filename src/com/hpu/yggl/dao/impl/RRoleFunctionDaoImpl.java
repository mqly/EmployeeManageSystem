package com.hpu.yggl.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.hpu.yggl.bean.RRoleFunction;
import com.hpu.yggl.bean.RoleInfo;
import com.hpu.yggl.bean.UserInfo;
import com.hpu.yggl.dao.RRoleFunctionDao;

public class RRoleFunctionDaoImpl extends HibernateDaoSupport implements RRoleFunctionDao {

	@Override
	public List<RRoleFunction> getRRFByFunctionId(String functionId) {
		// TODO Auto-generated method stub
		String hql = "from RRoleFunction rrf where rrf.functionId='" + functionId + "'";
		return (List<RRoleFunction>) this.getHibernateTemplate().find(hql);

	}

	@Override
	public void saveRRF(RRoleFunction rrf) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(rrf);
	}

	@Override
	public void deleteRRF(RRoleFunction rrf) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(rrf);
	}

	@Override
	public List<RRoleFunction> getRRFByRoleId(String roleId) {
		// TODO Auto-generated method stub
		String hql = " from RRoleFunction rrf where rrf.roleId='" + roleId + "'";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public List<RRoleFunction> getAllRRF() {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().loadAll(RRoleFunction.class);
	}

	@Override
	public List<RRoleFunction> findPage(final int start, final int number) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuffer sbSQL = new StringBuffer("from com.hpu.yggl.bean.RRoleFunction order by roleId");
				Query query = session.createQuery(sbSQL.toString());
				query.setFirstResult(start);
				query.setMaxResults(number);
				System.out.println("size-------------" + query.list().size());
				return query.list();
			}
		});
	}

	@Override
	public void udpateRRF(RRoleFunction rRoleFunction) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(rRoleFunction);
	}
}
