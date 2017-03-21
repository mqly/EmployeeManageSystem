package com.hpu.yggl.service;

import java.util.List;
import com.hpu.yggl.bean.DeptInfo;
import com.hpu.yggl.bean.UserInfo;

public interface DeptInfoService {

	DeptInfo getDeptInfo(String id);

	void updateDeptInfo(DeptInfo deptInfo);

	List<DeptInfo> getAllDept();

	void deleteDeptInfo(String id);

	List<DeptInfo> findPage(int start, int number);

	void addDeptInfo(DeptInfo deptInfo);
}
