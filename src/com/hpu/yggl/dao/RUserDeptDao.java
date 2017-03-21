package com.hpu.yggl.dao;

import java.util.List;
import com.hpu.yggl.bean.RUserDept;

public interface RUserDeptDao {

	List<RUserDept> getRUDByUserId(String userId);

	void saveRUD(RUserDept rud);

	void deleteRUD(RUserDept rud);

	List<RUserDept> getRUDByDeptId(String deptId);

	void updateRUD(RUserDept rUserDept);

}
