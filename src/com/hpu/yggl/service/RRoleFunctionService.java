package com.hpu.yggl.service;

import java.util.List;
import com.hpu.yggl.bean.RRoleFunction;
import com.hpu.yggl.bean.RoleInfo;

public interface RRoleFunctionService {

	List<RRoleFunction> getRRFByFunctionId(String functionId);

	void saveRRF(RRoleFunction rrf);

	void deleteRRF(RRoleFunction rrf);

	List<RRoleFunction> getRRFByRoleId(String roleId);

	List<RRoleFunction> getAllRRF();

	List<RRoleFunction> findPage(int start, int number);

	void updateRRF(RRoleFunction rRoleFunction);


}
