package com.hpu.yggl.service;

import java.util.List;

import com.hpu.yggl.bean.FunctionInfo;

public interface FunctionInfoService {

	FunctionInfo getFunction(String id);

	void updateFunction(FunctionInfo functionInfo);

	List<FunctionInfo> getAllFunction();

	void deleteFunction(String id);

	List<FunctionInfo> findPage(int start, int number);

	void addFunction(FunctionInfo functionInfo);
}
