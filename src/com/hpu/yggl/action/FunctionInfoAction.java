package com.hpu.yggl.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hpu.yggl.bean.DeptInfo;
import com.hpu.yggl.bean.FunctionInfo;
import com.hpu.yggl.bean.RRoleFunction;
import com.hpu.yggl.bean.RUserDept;
import com.hpu.yggl.bean.RUserRole;
import com.hpu.yggl.bean.UserInfo;
import com.hpu.yggl.service.FunctionInfoService;
import com.hpu.yggl.service.RRoleFunctionService;
import com.hpu.yggl.service.RUserRoleService;
import com.hpu.yggl.util.UUIDBuilder;

public class FunctionInfoAction extends BaseAction {

	private FunctionInfoService functionInfoService;
	private RUserRoleService rUserRoleService;
	private RRoleFunctionService rRoleFunctionService;

	public RUserRoleService getrUserRoleService() {
		return rUserRoleService;
	}

	public void setrUserRoleService(RUserRoleService rUserRoleService) {
		this.rUserRoleService = rUserRoleService;
	}

	public RRoleFunctionService getrRoleFunctionService() {
		return rRoleFunctionService;
	}

	public void setrRoleFunctionService(RRoleFunctionService rRoleFunctionService) {
		this.rRoleFunctionService = rRoleFunctionService;
	}

	public FunctionInfoService getFunctionInfoService() {
		return functionInfoService;
	}

	public void setFunctionInfoService(FunctionInfoService functionInfoService) {
		this.functionInfoService = functionInfoService;
	}

	private String rows;
	private String page;

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public void getFunctionByUserId() {
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 */
		response.setContentType("text/html;charset=utf-8");
		// response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		JsonObject result = new JsonObject();
		try {
			out = response.getWriter();
			String userId = (String) session.getAttribute("userId");
			// String userId = "611fb1534536492fbf955d1b3f73b791"; //test
			System.out.println(userId);
			if (userId == null || userId == "") {
				result.addProperty("flag", "false");
				out.println(result);
				out.flush();
				out.close();
				return;
			}
			Gson gson = new Gson();
			List<FunctionInfo> funList = new ArrayList<FunctionInfo>();
			result.addProperty("flag", "success");
			List<RUserRole> rurList = rUserRoleService.getRURByUserId(userId);
			for (RUserRole rUserRole : rurList) {
				String roleId = rUserRole.getRoleId();
				List<RRoleFunction> rrfList = rRoleFunctionService.getRRFByRoleId(roleId);
				for (RRoleFunction rRoleFunction : rrfList) {
					String functionId = rRoleFunction.getFunctionId();
					FunctionInfo function = functionInfoService.getFunction(functionId);
					funList.add(function);
				}
			}
			JsonElement funELe = gson.toJsonTree(funList);
			result.add("functions", funELe);
			System.out.println(result.toString());
			session.setAttribute("functionList", funList);
			List<String> ids = new ArrayList<String>();
			for (FunctionInfo func : funList) {
				ids.add(func.getId());
			}
			session.setAttribute("ids", ids);
			session.setAttribute("userId", userId);
			out.println(result);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getFunctionList() {
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 */
		System.out.println("开始查询所有权限");
		response.setContentType("text/html;charset=utf-8");
		// response.setCharacterEncoding("UTF-8");
		List<FunctionInfo> fitList = functionInfoService.getAllFunction();
		PrintWriter out = null;
		Gson gson = new Gson();
		try {
			out = response.getWriter();
			if (fitList == null) {
				out.println("{}");
				out.flush();
				out.close();
				return;
			} else {
				// JsonElement fitELe = gson.toJsonTree(fitList);
				System.out.println("--------------all function---------------");
				System.out.println(gson.toJson(fitList));
				// session.setAttribute("functionList", funList);
				out.println(gson.toJson(fitList));
				out.flush();
				out.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getSomeFuncs() {
		String flag = (String) session.getAttribute("flag");
		if (flag == "" || flag != "alt") {
			return;
		}
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
		// 每页显示条数
		int number = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
		// 每页的开始记录 第一页为1 第二页为number +1
		int start = (intPage - 1) * number;
		response.setContentType("text/html;charset=utf-8");
		List<FunctionInfo> recList = functionInfoService.findPage(start, number);
		JsonObject result = new JsonObject();
		PrintWriter out = null;
		Gson gson = new Gson();
		try {
			out = response.getWriter();
			if (recList == null) {
				result.addProperty("total", 0);
				out.println(result);
				out.flush();
				out.close();
				return;
			} else {
				System.out.println();
				JsonElement recELe = gson.toJsonTree(recList);
				result.addProperty("total", functionInfoService.getAllFunction().size());
				result.add("rows", recELe);
				System.out.println(result.toString());
				// session.setAttribute("functionList", funList);
				out.println(result);
				out.flush();
				out.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteFunc() {
		String id = request.getParameter("id");
		List<RRoleFunction> rrfList = rRoleFunctionService.getRRFByFunctionId(id);
		if (rrfList != null & rrfList.size() > 0) {
			for (RRoleFunction rRoleFunction : rrfList) {
				// 删除部门时，与角色关联的部门设置位暂无部门
				List<RRoleFunction> tempList = rRoleFunctionService.getRRFByRoleId(rRoleFunction.getRoleId());
				// 如果关联要删除的权限的角色只有这一个权限则设置为 暂无权限，如果有多个权限则删除和要删除权限关联的角色权限数据
				if (tempList.size() == 1) {
					rRoleFunction.setFunctionId("1b20a88d6e754791bfa3dce322e7b5e0");
					rRoleFunctionService.updateRRF(rRoleFunction);
				} else if (tempList.size() > 1) {
					rRoleFunctionService.deleteRRF(rRoleFunction);
				}

			}
		}
		functionInfoService.deleteFunction(id);
	}

	public void addFunction() {
		String qxmc = request.getParameter("qxmc");
		System.out.println("qxmc-----------" + qxmc);
		FunctionInfo functionInfo = new FunctionInfo();
		functionInfo.setId(UUIDBuilder.getUUID());
		functionInfo.setFunc(qxmc);
		functionInfoService.addFunction(functionInfo);
	}

	public void updateFunc() {
		String id = request.getParameter("id");
		String qxmc = request.getParameter("qxmc");
		FunctionInfo functionInfo = new FunctionInfo();
		functionInfo.setId(id);
		functionInfo.setFunc(qxmc);
		functionInfoService.updateFunction(functionInfo);
	}
}
