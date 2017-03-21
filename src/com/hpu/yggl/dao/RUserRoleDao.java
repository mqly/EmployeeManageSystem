package com.hpu.yggl.dao;

import java.util.List;
import com.hpu.yggl.bean.RUserRole;

public interface RUserRoleDao {

	List<RUserRole> getRURByUserId(String userId);

	void saveRUR(RUserRole rur);

	void deleteRUR(RUserRole rur);

	List<RUserRole> getRURByRoleId(String roleId);

}
