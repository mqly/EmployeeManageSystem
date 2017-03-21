package com.hpu.yggl.service;

import java.util.List;
import com.hpu.yggl.bean.RoleInfo;

public interface RoleInfoService {

	RoleInfo getRoleInfo(String id);

	void updateRoleInfo(RoleInfo roleInfo);

	List<RoleInfo> getAllRole();

	void deleteRoleInfo(String id);

	void addRoleInfo(RoleInfo roleInfo);

	List<RoleInfo> findPage(int start, int number);
}
