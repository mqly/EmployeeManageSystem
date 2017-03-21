package com.hpu.yggl.service.impl;

import java.util.List;
import com.hpu.yggl.bean.FunctionInfo;
import com.hpu.yggl.dao.FunctionInfoDao;
import com.hpu.yggl.service.FunctionInfoService;

public class FunctionInfoServiceImpl implements FunctionInfoService {

	private FunctionInfoDao functionInfoDao;

	public FunctionInfoDao getFunctionInfoDao() {
		return functionInfoDao;
	}

	public void setFunctionInfoDao(FunctionInfoDao functionInfoDao) {
		this.functionInfoDao = functionInfoDao;
	}

	@Override
	public FunctionInfo getFunction(String id) {
		// TODO Auto-generated method stub
		return this.getFunctionInfoDao().getFunction(id);
	}

	@Override
	public void updateFunction(FunctionInfo functionInfo) {
		// TODO Auto-generated method stub
		this.getFunctionInfoDao().updateFunction(functionInfo);
	}

	@Override
	public List<FunctionInfo> getAllFunction() {
		// TODO Auto-generated method stub
		return this.getFunctionInfoDao().getAllFunction();
	}

	@Override
	public void deleteFunction(String id) {
		// TODO Auto-generated method stub
		this.getFunctionInfoDao().deleteFunction(id);
	}

	@Override
	public List<FunctionInfo> findPage(int start, int number) {
		// TODO Auto-generated method stub
		return this.getFunctionInfoDao().findPage(start, number);
	}

	@Override
	public void addFunction(FunctionInfo functionInfo) {
		// TODO Auto-generated method stub
		this.getFunctionInfoDao().addFunction(functionInfo);
	}
}
