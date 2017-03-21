package com.hpu.yggl.dao;

import java.util.List;

import com.hpu.yggl.bean.DeptInfo;

public interface DeptInfoDao {

	DeptInfo getDept(String id);

	void updateDept(DeptInfo deptInfo);

	List<DeptInfo> getAllDept();

	void deleteDept(String id);

	List<DeptInfo> findPage(int start, int number);

	void addDeptInfo(DeptInfo deptInfo);

}
