package com.hpu.yggl.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hpu.yggl.bean.RRoleFunction;
import com.hpu.yggl.bean.RoleDetail;
import com.hpu.yggl.bean.RoleInfo;
import com.hpu.yggl.bean.UserDetail;
import com.hpu.yggl.bean.UserInfo;
import com.hpu.yggl.service.FunctionInfoService;
import com.hpu.yggl.service.RRoleFunctionService;
import com.hpu.yggl.service.RoleInfoService;
import com.hpu.yggl.util.UUIDBuilder;

public class RoleInfoAction extends BaseAction {

	private RoleInfoService roleInfoService;
	private RRoleFunctionService rRoleFunctionService;
	private FunctionInfoService functionInfoService;

	public RoleInfoService getRoleInfoService() {
		return roleInfoService;
	}

	public void setRoleInfoService(RoleInfoService roleInfoService) {
		this.roleInfoService = roleInfoService;
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

	public void addRole() {
		String jsmc = request.getParameter("jsmc");
		String funcs = request.getParameter("funcs");
		// System.out.println("------------添加角色的权限列表-------------");
		// System.out.println(funcs);
		String[] f = funcs.split(",");
		RRoleFunction rrf;
		RoleInfo roleInfo = new RoleInfo();
		String roleId = UUIDBuilder.getUUID();
		roleInfo.setId(roleId);
		roleInfo.setRole(jsmc);
		for (int i = 0; i < f.length; i++) {
			// System.out.println(f[i]);
			rrf = new RRoleFunction();
			rrf.setId(UUIDBuilder.getUUID());
			rrf.setRoleId(roleId);
			rrf.setFunctionId(f[i]);
			rRoleFunctionService.saveRRF(rrf);
		}
		roleInfoService.addRoleInfo(roleInfo);
	}

	public void updateRole() {
		String id = request.getParameter("id");
		String jsmc = request.getParameter("jsmc");
		String qxs = request.getParameter("qxs");
		String[] f = qxs.split(",");
		RoleInfo roleInfo = new RoleInfo();
		roleInfo.setId(id);
		roleInfo.setRole(jsmc);
		roleInfoService.updateRoleInfo(roleInfo);
		List<RRoleFunction> rrfList = rRoleFunctionService.getRRFByRoleId(id);
		for (RRoleFunction rRoleFunction : rrfList) {
			rRoleFunctionService.deleteRRF(rRoleFunction);
		}
		RRoleFunction rrf;
		for (int i = 0; i < f.length; i++) {
			// System.out.println(f[i]);
			rrf = new RRoleFunction();
			rrf.setId(UUIDBuilder.getUUID());
			rrf.setRoleId(id);
			rrf.setFunctionId(f[i]);
			rRoleFunctionService.saveRRF(rrf);
		}
	}

	public void deleteRole() {
		String id = request.getParameter("id");
		// 删除角色时先删除角色权限关联表中的数据
		rRoleFunctionService.deleteRRF(rRoleFunctionService.getRRFByRoleId(id).get(0));
		roleInfoService.deleteRoleInfo(id);
	}

	public void getAllRole() {
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 */
		response.setContentType("text/html;charset=utf-8");
		// response.setCharacterEncoding("UTF-8");
		List<RoleInfo> recList = roleInfoService.getAllRole();
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
				result.addProperty("total", recList.size());
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

	public void getSomeRoles() {
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
		List<RoleDetail> fitList = new ArrayList<RoleDetail>();
		// bug
		List<RRoleFunction> recList = rRoleFunctionService.findPage(start, number);
		// List<RoleInfo> recList = roleInfoService.findPage(start, number);
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
				RoleDetail rDetail;
				for (RRoleFunction rrf : recList) {
					String rId = rrf.getRoleId();
					// 效率低，占用内存大，应从联表查询时处理
					rDetail = new RoleDetail();
					rDetail.setId(rId);
					rDetail.setRole(roleInfoService.getRoleInfo(rId).getRole());
					rDetail.setFunc(functionInfoService.getFunction(rrf.getFunctionId()).getFunc());
					fitList.add(rDetail);
				}
				JsonElement recELe = gson.toJsonTree(fitList);
				result.addProperty("total", rRoleFunctionService.getAllRRF().size());
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

	public void getRoleList() {
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 */
		response.setContentType("text/html;charset=utf-8");
		// response.setCharacterEncoding("UTF-8");
		List<RoleInfo> fitList = roleInfoService.getAllRole();
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
				System.out.println("----------打印所有角色列表------------");
				System.out.println(gson.toJson(fitList));
				out.println(gson.toJson(fitList));
				out.flush();
				out.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
