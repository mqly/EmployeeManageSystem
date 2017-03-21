package com.hpu.yggl.dao.impl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hpu.yggl.bean.RUserDept;
import com.hpu.yggl.bean.RUserOffice;
import com.hpu.yggl.bean.RUserRole;
import com.hpu.yggl.bean.UserInfo;
import com.hpu.yggl.dao.UserInfoDao;
import com.hpu.yggl.util.UUIDBuilder;

public class UserInfoDaoImpl extends HibernateDaoSupport implements UserInfoDao {

	@Override
	public UserInfo getUser(String id) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(UserInfo.class, id);
	}

	@Override
	public void updateUser(UserInfo userInfo) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(userInfo);
	}

	@Override
	public List<UserInfo> getAllUser() {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().loadAll(UserInfo.class);
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		UserInfo user = getUser(id);
		this.getHibernateTemplate().delete(user);
	}

	@Override
	public UserInfo login(String username, String password) {
		// TODO Auto-generated method stub
		String hql = "from UserInfo u where u.username='" + username + "' and u.password='" + password + "'";
		List<UserInfo> uList = this.getHibernateTemplate().find(hql);
		if (uList.size() == 0) {
			return null;
		} else {
			return uList.get(0);
		}
	}

	@Override
	public List<UserInfo> findPage(final int start, final int number) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuffer sbSQL = new StringBuffer("from com.hpu.yggl.bean.UserInfo");
				Query query = session.createQuery(sbSQL.toString());
				query.setFirstResult(start);
				query.setMaxResults(number);
				System.out.println("size-------------" + query.list().size());
				return query.list();
			}
		});
	}

	@Override
	public void addUser(UserInfo userInfo) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(userInfo);
	}

	@Override
	public void addUserDetail(String userId, String username, String password, String realname, String roleId,
			String deptId, String officeId) {
		// TODO Auto-generated method stub
		UserInfo userInfo = new UserInfo();
		userInfo.setId(userId);
		userInfo.setUsername(username);
		userInfo.setPassword(password);
		userInfo.setRealname(realname);
		RUserRole rur = new RUserRole();
		rur.setId(UUIDBuilder.getUUID());
		rur.setUserId(userId);
		rur.setRoleId(roleId);
		RUserDept rud = new RUserDept();
		rud.setId(UUIDBuilder.getUUID());
		rud.setUserId(userId);
		rud.setDeptId(deptId);
		RUserOffice ruo = new RUserOffice();
		ruo.setId(UUIDBuilder.getUUID());
		ruo.setUserId(userId);
		ruo.setOfficeId(officeId);
		this.getHibernateTemplate().save(userInfo);
		this.getHibernateTemplate().save(rur);
		this.getHibernateTemplate().save(rud);
		this.getHibernateTemplate().save(ruo);
	}

	@Override
	public void updateUserDetail(String userId, String username, String password, String realname, String roleId,
			String deptId, String officeId) {
		// TODO Auto-generated method stub
		UserInfo userInfo = new UserInfo();
		userInfo.setId(userId);
		userInfo.setUsername(username);
		userInfo.setPassword(password);
		userInfo.setRealname(realname);
		RUserRole rur = new RUserRole();
		rur.setId(UUIDBuilder.getUUID());
		rur.setUserId(userId);
		rur.setRoleId(roleId);
		RUserDept rud = new RUserDept();
		rud.setId(UUIDBuilder.getUUID());
		rud.setUserId(userId);
		rud.setDeptId(deptId);
		RUserOffice ruo = new RUserOffice();
		ruo.setId(UUIDBuilder.getUUID());
		ruo.setUserId(userId);
		ruo.setOfficeId(officeId);
		this.getHibernateTemplate().update(userInfo);
		this.getHibernateTemplate().save(rur);
		this.getHibernateTemplate().save(rud);
		this.getHibernateTemplate().save(ruo);
	}

}
