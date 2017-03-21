package com.hpu.yggl.dao;

import java.util.List;

import com.hpu.yggl.bean.FunctionInfo;

public interface FunctionInfoDao {

	FunctionInfo getFunction(String id);

	void updateFunction(FunctionInfo functionInfo);

	List<FunctionInfo> getAllFunction();

	void deleteFunction(String id);

	List<FunctionInfo> findPage(int start, int number);

	void addFunction(FunctionInfo functionInfo);

}
