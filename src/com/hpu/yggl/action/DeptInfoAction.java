package com.hpu.yggl.action;

import java.io.PrintWriter;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hpu.yggl.bean.DeptInfo;
import com.hpu.yggl.bean.RUserDept;
import com.hpu.yggl.bean.RoleInfo;
import com.hpu.yggl.service.DeptInfoService;
import com.hpu.yggl.service.RUserDeptService;
import com.hpu.yggl.service.UserInfoService;
import com.hpu.yggl.util.UUIDBuilder;

public class DeptInfoAction extends BaseAction {

	private DeptInfoService deptInfoService;
	private RUserDeptService rUserDeptService;

	public DeptInfoService getDeptInfoService() {
		return deptInfoService;
	}

	public void setDeptInfoService(DeptInfoService deptInfoService) {
		this.deptInfoService = deptInfoService;
	}

	public RUserDeptService getrUserDeptService() {
		return rUserDeptService;
	}

	public void setrUserDeptService(RUserDeptService rUserDeptService) {
		this.rUserDeptService = rUserDeptService;
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

	public void addDept() {
		String bmmc = request.getParameter("bmmc");
		System.out.println("bmmc-----------" + bmmc);
		DeptInfo deptInfo = new DeptInfo();
		deptInfo.setId(UUIDBuilder.getUUID());
		deptInfo.setDept(bmmc);
		deptInfoService.addDeptInfo(deptInfo);
	}

	public void updateDept() {
		String id = request.getParameter("id");
		String bmmc = request.getParameter("bmmc");
		DeptInfo deptInfo = new DeptInfo();
		deptInfo.setId(id);
		deptInfo.setDept(bmmc);
		deptInfoService.updateDeptInfo(deptInfo);
	}

	public void deleteDept() {
		String id = request.getParameter("id");
		List<RUserDept> rudList = rUserDeptService.getRUDByDeptId(id);
		if (rudList != null & rudList.size() > 0) {
			for (RUserDept rUserDept : rudList) {
				// 删除部门时，与角色关联的部门设置位暂无部门
				rUserDept.setDeptId("895acfeabb5c476b9df826ad58bf1282");
				rUserDeptService.updateRUD(rUserDept);
			}

		}
		deptInfoService.deleteDeptInfo(id);
	}

	public void getAllDept() {
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 */
		response.setContentType("text/html;charset=utf-8");
		// response.setCharacterEncoding("UTF-8");
		List<DeptInfo> recList = deptInfoService.getAllDept();
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

	public void getSomeDepts() {
		System.out.println("---getsomedepts--");
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
		List<DeptInfo> recList = deptInfoService.findPage(start, number);
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
				result.addProperty("total", deptInfoService.getAllDept().size());
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

	public void getDeptList() {
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 */
		response.setContentType("text/html;charset=utf-8");
		// response.setCharacterEncoding("UTF-8");
		List<DeptInfo> fitList = deptInfoService.getAllDept();
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
				out.println(gson.toJson(fitList));
				out.flush();
				out.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
