package com.hpu.yggl.service;

import java.util.List;
import com.hpu.yggl.bean.UserInfo;

public interface UserInfoService {

	UserInfo getUser(String id);

	void updateUser(UserInfo userInfo);

	List<UserInfo> getAllUser();

	void deleteUser(String id);

	UserInfo login(String username, String password);

	List<UserInfo> findPage(int start, int number);

	void addUser(UserInfo userInfo);

	void addUserDetail(String userId, String username, String password, String realname, String roleId, String deptId,
			String officeId);

	void updateUserDetail(String userId, String username, String password, String realname, String roleId,
			String deptId, String officeId);

}
