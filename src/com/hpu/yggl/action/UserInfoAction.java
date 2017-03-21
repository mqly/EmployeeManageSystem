package com.hpu.yggl.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.New;
import javax.servlet.ServletInputStream;

import org.apache.commons.io.FileUtils;
import org.omg.CORBA.PUBLIC_MEMBER;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hpu.yggl.bean.RUserRole;
import com.hpu.yggl.bean.RoleInfo;
import com.hpu.yggl.bean.UserDetail;
import com.hpu.yggl.bean.UserInfo;
import com.hpu.yggl.bean.RUserDept;
import com.hpu.yggl.bean.RUserOffice;
import com.hpu.yggl.bean.DeptInfo;
import com.hpu.yggl.bean.FunctionInfo;
import com.hpu.yggl.bean.OfficeInfo;
import com.hpu.yggl.service.DeptInfoService;
import com.hpu.yggl.service.OfficeInfoService;
import com.hpu.yggl.service.RUserDeptService;
import com.hpu.yggl.service.RUserOfficeService;
import com.hpu.yggl.service.RUserRoleService;
import com.hpu.yggl.service.RoleInfoService;
import com.hpu.yggl.service.UserInfoService;
import com.hpu.yggl.util.UUIDBuilder;

public class UserInfoAction extends BaseAction {

	private UserInfoService userInfoService;
	private DeptInfoService deptInfoService;
	private OfficeInfoService officeInfoService;
	private RoleInfoService roleInfoService;
	private RUserRoleService rUserRoleService;
	private RUserDeptService rUserDeptService;
	private RUserOfficeService rUserOfficeService;
	private String rows;
	private String page;
	// 必须是public，private修饰会获取不到数据
	public String username;
	public String password;
	public File img;
	public String imgFileName;

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

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		// System.out.println("set UserINfoService");
		this.userInfoService = userInfoService;
	}

	public RUserRoleService getrUserRoleService() {
		return rUserRoleService;
	}

	public void setrUserRoleService(RUserRoleService rUserRoleService) {
		this.rUserRoleService = rUserRoleService;
	}

	public RUserDeptService getrUserDeptService() {
		return rUserDeptService;
	}

	public void setrUserDeptService(RUserDeptService rUserDeptService) {
		this.rUserDeptService = rUserDeptService;
	}

	public RUserOfficeService getrUserOfficeService() {
		return rUserOfficeService;
	}

	public void setrUserOfficeService(RUserOfficeService rUserOfficeService) {
		this.rUserOfficeService = rUserOfficeService;
	}

	public DeptInfoService getDeptInfoService() {
		return deptInfoService;
	}

	public void setDeptInfoService(DeptInfoService deptInfoService) {
		this.deptInfoService = deptInfoService;
	}

	public OfficeInfoService getOfficeInfoService() {
		return officeInfoService;
	}

	public void setOfficeInfoService(OfficeInfoService officeInfoService) {
		this.officeInfoService = officeInfoService;
	}

	public RoleInfoService getRoleInfoService() {
		return roleInfoService;
	}

	public void setRoleInfoService(RoleInfoService roleInfoService) {
		this.roleInfoService = roleInfoService;
	}

	public void login() throws IOException {
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 */
		// System.out.println("----------login----------");
		response.setContentType("text/html;charset=utf-8");
		// response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserInfo u = userInfoService.login(username, password);
		JsonObject result = new JsonObject();
		if (u == null) {
			System.out.println("用户不存在");
			result.addProperty("flag", "false");
			result.addProperty("message", "用户不存在");
		} else {
			System.out.println(u.getId());
			result.addProperty("flag", "success");
			result.addProperty("message", u.getId());
			session.setAttribute("userId", u.getId());
			session.setAttribute("realName", u.getRealname());
			// 登陆成功标记，防止未登录直接操作权限
			session.setAttribute("flag", "alt");
		}
		out.println(result);
		out.flush();
		out.close();
		return;
	}

	public void getUser() throws IOException {
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 */
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String userId = (String) session.getAttribute("userId");
		String dept = new String();
		if (rUserDeptService.getRUDByUserId(userId) != null & rUserDeptService.getRUDByUserId(userId).size() > 0) {
			String deptId = (String) rUserDeptService.getRUDByUserId(userId).get(0).getDeptId();
			dept = deptInfoService.getDeptInfo(deptId).getDept();
		}
		String office = new String();
		if (rUserDeptService.getRUDByUserId(userId) != null & rUserOfficeService.getRUOByUserId(userId).size() > 0) {
			String officeId = (String) rUserOfficeService.getRUOByUserId(userId).get(0).getOfficeId();
			office = officeInfoService.getOfficeInfo(officeId).getOffice();
		}

		UserInfo u = userInfoService.getUser(userId);
		JsonObject result = new JsonObject();
		if (u == null) {
			System.out.println("加载用户信息失败");
			result.addProperty("flag", "false");
			result.addProperty("message", "加载用户信息失败");
			result.addProperty("username", "");
			result.addProperty("password", "");
		} else {
			System.out.println(u.getId());
			result.addProperty("flag", "success");
			result.addProperty("message", "加载成功");
			result.addProperty("username", u.getUsername());
			result.addProperty("password", u.getPassword());
			result.addProperty("realname", u.getRealname());
			result.addProperty("dept", dept);
			result.addProperty("office", office);
			session.setAttribute("userId", u.getId());
		}
		System.out.println(result);
		out.println(result);
		out.flush();
		out.close();
		return;
	}

	public void basicUpdateUser() {
		response.setContentType("text/html;charset=utf-8");
		String id = (String) session.getAttribute("userId");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String realname = request.getParameter("realname");
		// System.out.println("realname-----------" + realname);
		UserInfo userInfo = new UserInfo();
		userInfo.setId(id);
		userInfo.setUsername(username);
		userInfo.setPassword(password);
		userInfo.setRealname(realname);
		userInfoService.updateUser(userInfo);
	}

	public void updateUser() {
		String id = request.getParameter("userId");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String realname = request.getParameter("realname");
		String roleId = request.getParameter("roleId");
		UserInfo userInfo = new UserInfo();
		userInfo.setId(id);
		userInfo.setUsername(username);
		userInfo.setPassword(password);
		userInfo.setRealname(realname);
		userInfoService.updateUser(userInfo);
		List<RUserRole> rurList = rUserRoleService.getRURByUserId(id);
		for (RUserRole rUserRole : rurList) {
			rUserRoleService.deleteRUR(rUserRole);
		}
		String newId = UUIDBuilder.getUUID();
		RUserRole rur = new RUserRole();
		rur.setId(newId);
		rur.setUserId(id);
		rur.setRoleId(roleId);
		rUserRoleService.saveRUR(rur);
	}

	public void getAllUser() {
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 */
		response.setContentType("text/html;charset=utf-8");
		// response.setCharacterEncoding("UTF-8");
		List<UserInfo> fitList = userInfoService.getAllUser();
		JsonObject result = new JsonObject();
		PrintWriter out = null;
		Gson gson = new Gson();
		try {
			out = response.getWriter();
			if (fitList == null) {
				result.addProperty("total", 0);
				out.println(result);
				out.flush();
				out.close();
				return;
			} else {
				System.out.println();
				JsonElement fitELe = gson.toJsonTree(fitList);
				result.addProperty("total", fitList.size());
				result.add("rows", fitELe);
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

	public void getSomeUsers() {
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
		// 每页显示条数
		int number = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
		// 每页的开始记录 第一页为1 第二页为number +1
		int start = (intPage - 1) * number;
		response.setContentType("text/html;charset=utf-8");
		// response.setCharacterEncoding("UTF-8");
		List<UserInfo> fitList = userInfoService.findPage(start, number);
		JsonObject result = new JsonObject();
		PrintWriter out = null;
		Gson gson = new Gson();
		try {
			out = response.getWriter();
			if (fitList == null) {
				result.addProperty("total", 0);
				out.println(result);
				out.flush();
				out.close();
				return;
			} else {
				System.out.println();
				JsonElement fitELe = gson.toJsonTree(fitList);
				result.addProperty("total", userInfoService.getAllUser().size());
				result.add("rows", fitELe);
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

	public void addUser() {
		String userId = UUIDBuilder.getUUID();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String roleId = request.getParameter("roleId");
		UserInfo userInfo = new UserInfo();
		userInfo.setId(userId);
		userInfo.setUsername(username);
		userInfo.setPassword(password);
		userInfoService.addUser(userInfo);
		RUserRole rur = new RUserRole();
		String id = UUIDBuilder.getUUID();
		rur.setId(id);
		rur.setUserId(userId);
		rur.setRoleId(roleId);
		rUserRoleService.saveRUR(rur);
	}

	public void deleteUser() {
		String id = request.getParameter("id");
		List<RUserRole> rurList = rUserRoleService.getRURByUserId(id);
		for (RUserRole rUserRole : rurList) {
			rUserRoleService.deleteRUR(rUserRole);
		}
		userInfoService.deleteUser(id);
	}

	public void getSomeUsersDetail() {
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
		List<UserDetail> fitList = new ArrayList<UserDetail>();
		// response.setCharacterEncoding("UTF-8");
		List<UserInfo> usersList = userInfoService.findPage(start, number);
		JsonObject result = new JsonObject();
		PrintWriter out = null;
		Gson gson = new Gson();
		try {
			out = response.getWriter();
			if (usersList == null) {
				result.addProperty("total", 0);
				out.println(result);
				out.flush();
				out.close();
				return;
			} else {
				UserDetail uDetail;
				for (UserInfo u : usersList) {
					// 效率低，占用内存大，应从联表查询时处理
					uDetail = new UserDetail();
					String uID = u.getId();
					uDetail.setId(uID);
					uDetail.setUsername(u.getUsername());
					uDetail.setPassword(u.getPassword());
					uDetail.setRealname(u.getRealname());
					if (rUserDeptService.getRUDByUserId(uID) != null
							& rUserDeptService.getRUDByUserId(uID).size() > 0) {
						uDetail.setDept(deptInfoService
								.getDeptInfo(rUserDeptService.getRUDByUserId(uID).get(0).getDeptId()).getDept());
					} else {
						uDetail.setDept("暂无部门");
					}
					if (rUserRoleService.getRURByUserId(uID) != null
							& rUserRoleService.getRURByUserId(uID).size() > 0) {
						uDetail.setRole(roleInfoService
								.getRoleInfo(rUserRoleService.getRURByUserId(uID).get(0).getRoleId()).getRole());
					} else {
						uDetail.setRole("暂无角色");
					}
					if (rUserOfficeService.getRUOByUserId(uID) != null
							& rUserOfficeService.getRUOByUserId(uID).size() > 0) {
						uDetail.setOffice(officeInfoService
								.getOfficeInfo(rUserOfficeService.getRUOByUserId(uID).get(0).getOfficeId())
								.getOffice());
					} else {
						uDetail.setOffice("暂无职位");
					}
					fitList.add(uDetail);
				}
				JsonElement fitELe = gson.toJsonTree(fitList);
				result.addProperty("total", userInfoService.getAllUser().size());
				result.add("rows", fitELe);
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

	public void addUserDetail() {
		String userId = UUIDBuilder.getUUID();
		String username = request.getParameter("yhm");
		String password = request.getParameter("mm");
		String realname = request.getParameter("xm");
		String roleId = request.getParameter("jsid");
		String deptId = request.getParameter("bmid");
		String officeId = request.getParameter("zwid");
		userInfoService.addUserDetail(userId, username, password, realname, roleId, deptId, officeId);
	}

	public void deleteUserDetail() {
		String userId = request.getParameter("userId");
		// System.out.println(userId);
		// 删除用户前，先删除用户角色，用户部门，用户职位关联表中数据
		rUserRoleService.deleteRUR(rUserRoleService.getRURByUserId(userId).get(0));
		rUserDeptService.deleteRUD(rUserDeptService.getRUDByUserId(userId).get(0));
		rUserOfficeService.deleteRUO(rUserOfficeService.getRUOByUserId(userId).get(0));
		userInfoService.deleteUser(userId);
	}

	public void updateUserDetail() {
		String userId = request.getParameter("uid");
		String username = request.getParameter("uyhm");
		String password = request.getParameter("umm");
		String realname = request.getParameter("uxm");
		String roleId = request.getParameter("ujsid");
		String deptId = request.getParameter("ubmid");
		String officeId = request.getParameter("uzwid");
		rUserRoleService.deleteRUR(rUserRoleService.getRURByUserId(userId).get(0));
		rUserDeptService.deleteRUD(rUserDeptService.getRUDByUserId(userId).get(0));
		rUserOfficeService.deleteRUO(rUserOfficeService.getRUOByUserId(userId).get(0));
		userInfoService.updateUserDetail(userId, username, password, realname, roleId, deptId, officeId);
	}

	// test android okhttp3
	public void androidLoginTest() throws IOException {
		System.out.println("=====------------++++++++-----------======");
		System.out.println("android login");
		System.out.println(postString());
		response.setContentType("text/html;charset=utf-8");
		// response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username:" + username);
		System.out.println("password:" + password);
		String result = "";
		if (username.equals("yyl") && password.equals("111")) {
			result = "login success";
		} else {
			result = "login faile";
		}
		out.println(result);
		out.flush();
		out.close();
		return;
	}

	public void postfile() throws IOException {
		System.out.println("start post file");
		postFile();
	}

	public void upload() throws IOException {
		System.out.println("upload:" + "username:" + username + ",password:" + password);
		if (img == null) {
			System.out.println(imgFileName + " is null");
		}
		String dir = application.getRealPath("files");
		File f = new File(dir, imgFileName);
		FileUtils.copyFile(img, f);
	}

	public String postString() throws IOException {
		ServletInputStream is = request.getInputStream();
		StringBuilder sb = new StringBuilder();
		int len = 0;
		byte[] buff = new byte[1024];
		while ((len = is.read(buff)) != -1) {
			sb.append(new String(buff, 0, len));
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	public void postFile() throws IOException {
		ServletInputStream is = request.getInputStream();
		String dir = application.getRealPath("files");
		File file = new File(dir, "bm2.jpg");
		FileOutputStream fos = new FileOutputStream(file);
		int len = 0;
		byte[] buff = new byte[1024];
		while ((len = is.read(buff)) != -1) {
			fos.write(buff, 0, len);
		}
		fos.flush();
		fos.close();
	}

}
