package com.hpu.yggl.service;

import java.util.List;
import com.hpu.yggl.bean.RUserRole;

public interface RUserRoleService {

	List<RUserRole> getRURByUserId(String userId);

	void saveRUR(RUserRole rur);

	void deleteRUR(RUserRole rur);

	List<RUserRole> getRURByRoleId(String roleId);
}
