package com.hpu.yggl.dao;

import java.util.List;
import com.hpu.yggl.bean.RRoleFunction;
import com.hpu.yggl.bean.RoleInfo;

public interface RRoleFunctionDao {

	List<RRoleFunction> getRRFByFunctionId(String functionId);

	void saveRRF(RRoleFunction rrf);

	void deleteRRF(RRoleFunction rrf);

	List<RRoleFunction> getRRFByRoleId(String roleId);

	List<RRoleFunction> getAllRRF();

	List<RRoleFunction> findPage(int start, int number);

	void udpateRRF(RRoleFunction rRoleFunction);

}
