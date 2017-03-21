package com.hpu.yggl.action;

import java.io.PrintWriter;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hpu.yggl.bean.DeptInfo;
import com.hpu.yggl.bean.OfficeInfo;
import com.hpu.yggl.bean.RUserDept;
import com.hpu.yggl.bean.RUserOffice;
import com.hpu.yggl.service.OfficeInfoService;
import com.hpu.yggl.service.RUserOfficeService;
import com.hpu.yggl.util.UUIDBuilder;

public class OfficeInfoAction extends BaseAction {

	private OfficeInfoService officeInfoService;
	private RUserOfficeService rUserOfficeService;

	public OfficeInfoService getOfficeInfoService() {
		return officeInfoService;
	}

	public void setOfficeInfoService(OfficeInfoService officeInfoService) {
		this.officeInfoService = officeInfoService;
	}

	public RUserOfficeService getrUserOfficeService() {
		return rUserOfficeService;
	}

	public void setrUserOfficeService(RUserOfficeService rUserOfficeService) {
		this.rUserOfficeService = rUserOfficeService;
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

	public void addOffice() {
		String zwmc = request.getParameter("zwmc");
		System.out.println("zwmc-----------" + zwmc);
		OfficeInfo officeInfo = new OfficeInfo();
		officeInfo.setId(UUIDBuilder.getUUID());
		officeInfo.setOffice(zwmc);
		officeInfoService.addOfficeInfo(officeInfo);
	}

	public void updateOffice() {
		String id = request.getParameter("id");
		String zwmc = request.getParameter("zwmc");
		OfficeInfo officeInfo = new OfficeInfo();
		officeInfo.setId(id);
		officeInfo.setOffice(zwmc);
		officeInfoService.updateOfficeInfo(officeInfo);
	}

	public void deleteOffice() {
		String id = request.getParameter("id");
		List<RUserOffice> ruoList = rUserOfficeService.getRUOByOfficeId(id) ;
		if (ruoList != null & ruoList.size() > 0) {
			for (RUserOffice rUserOffice : ruoList) {
				// 删除部门时，与角色关联的部门设置位暂无部门
				rUserOffice.setOfficeId("dd807ca2e5d74aec8fa18eea110ac04b");
				rUserOfficeService.updateRUO(rUserOffice);
			}

		}
		officeInfoService.deleteOfficeInfo(id);
	}

	public void getAllOffice() {
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 */
		response.setContentType("text/html;charset=utf-8");
		// response.setCharacterEncoding("UTF-8");
		List<OfficeInfo> recList = officeInfoService.getAllOffice();
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

	public void getSomeOffices() {
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
		List<OfficeInfo> recList = officeInfoService.findPage(start, number);
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
				result.addProperty("total", officeInfoService.getAllOffice().size());
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

	public void getOfficeList() {
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 */
		response.setContentType("text/html;charset=utf-8");
		// response.setCharacterEncoding("UTF-8");
		List<OfficeInfo> fitList = officeInfoService.getAllOffice();
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
