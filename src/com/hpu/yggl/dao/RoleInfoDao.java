package com.hpu.yggl.dao;

import java.util.List;

import com.hpu.yggl.bean.RoleInfo;

public interface RoleInfoDao {

	RoleInfo getRole(String id);

	void updateRole(RoleInfo roleInfo);

	List<RoleInfo> getAllRole();

	void deleteRole(String id);

	void addRoleInfo(RoleInfo roleInfo);

	List<RoleInfo> findPage(int start, int number);

}
