package com.hpu.yggl.service.impl;

import java.util.List;
import com.hpu.yggl.bean.UserInfo;
import com.hpu.yggl.dao.UserInfoDao;
import com.hpu.yggl.service.UserInfoService;

public class UserInfoServiceImpl implements UserInfoService {

	private UserInfoDao userInfoDao;

	public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	@Override
	public UserInfo getUser(String id) {
		// TODO Auto-generated method stub
		return this.getUserInfoDao().getUser(id);
	}

	@Override
	public void updateUser(UserInfo userInfo) {
		// TODO Auto-generated method stub
		this.getUserInfoDao().updateUser(userInfo);
	}

	@Override
	public List<UserInfo> getAllUser() {
		// TODO Auto-generated method stub
		return this.getUserInfoDao().getAllUser();
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		this.getUserInfoDao().deleteUser(id);
	}

	@Override
	public UserInfo login(String username, String password) {
		// TODO Auto-generated method stub
		return this.getUserInfoDao().login(username, password);
	}

	@Override
	public List<UserInfo> findPage(int start, int number) {
		// TODO Auto-generated method stub
		return this.getUserInfoDao().findPage(start, number);
	}

	@Override
	public void addUser(UserInfo userInfo) {
		// TODO Auto-generated method stub
		this.getUserInfoDao().addUser(userInfo);
	}

	@Override
	public void addUserDetail(String userId, String username, String password, String realname, String roleId,
			String deptId, String officeId) {
		// TODO Auto-generated method stub
		this.getUserInfoDao().addUserDetail(userId, username, password, realname, roleId, deptId, officeId);
	}

	@Override
	public void updateUserDetail(String userId, String username, String password, String realname, String roleId,
			String deptId, String officeId) {
		// TODO Auto-generated method stub
		this.getUserInfoDao().updateUserDetail(userId, username, password, realname, roleId, deptId, officeId);
	}
}
