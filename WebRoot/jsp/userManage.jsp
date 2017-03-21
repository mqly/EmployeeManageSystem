<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>用户管理</title>
<link rel="stylesheet" type="text/css"
	href="../js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../js/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../js/demo/demo.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/locale/easyui-lang-zh_CN.js"></script>
</head>
<body onload="check()">
	<table id="dg" class="easyui-datagrid" title="用户信息"
		style="width:100%;height:auto" rownumbers="true" pagination="true"
		data-options="toolbar: '#tb',iconCls:'icon-edit',singleSelect:true,url:'/EmployeeManage/getSomeUsersDetail',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'id',width:300,halign:'center'">用户ID</th>
				<th data-options="field:'username',width:120,halign:'center'">用户名</th>
				<th data-options="field:'password',width:120,halign:'center'">密码</th>
				<th data-options="field:'realname',width:120,halign:'center'">姓名</th>
				<th data-options="field:'role',width:120,halign:'center'">角色</th>
				<th data-options="field:'dept',width:120,halign:'center'">部门</th>
				<th data-options="field:'office',width:120,halign:'center'">职位</th>
			</tr>
		</thead>
	</table>

	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true"
			onclick="deleteUser()">删除</a> <a href="javascript:void(0)"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true" onclick="edit()">修改</a>
	</div>


	<div id="addWin" class="easyui-window" closed="true"
		minimizable="false" maximizable="false" collapsible="false"
		modal="true" title="添加用户"
		style="top: 100px; left: 200px; width: 580px; height: 330px;"
		align="center">
		<form id="userForm" method="get" style="padding-top: 8px;"
			enctype="multipart/form-data">
			<div style="padding-top: 8px; padding-bottom: 3px;">
				<tt>用户名:</tt>
				<input class="easyui-validatebox" type="text" id="yhm"
					style="width: 240px" name="yhm" maxlength="200" />
			</div>
			<div style="padding-top: 8px; padding-bottom: 3px;">
				<tt>密</tt>
				&nbsp;&nbsp;
				<tt> 码:</tt>
				<input class="easyui-validatebox" type="text" id="mm"
					style="width: 240px" name="mm" maxlength="200" />
			</div>
			<div style="padding-top: 8px; padding-bottom: 3px;">
				<tt>姓</tt>
				&nbsp;&nbsp;
				<tt> 名:</tt>
				<input class="easyui-validatebox" type="text" id="xm"
					style="width: 240px" name="xm" maxlength="200" />
			</div>
			<div style="padding-top: 8px; padding-bottom: 3px;">
				<tt>角</tt>
				&nbsp;&nbsp;
				<tt> 色:</tt>
				<input id="js" name="js" class="easyui-combobox"
					style="width: 240px" maxlength="200"
					data-options="valueField:'id',textField:'role',url:'/EmployeeManage/getRoleList',multiple:false">
			</div>
			<div style="padding-top: 8px; padding-bottom: 3px;">
				<tt>部</tt>
				&nbsp;&nbsp;
				<tt> 门:</tt>
				<input id="bm" name="bm" class="easyui-combobox"
					style="width: 240px" maxlength="200"
					data-options="valueField:'id',textField:'dept',url:'/EmployeeManage/getDeptList',multiple:false">
			</div>
			<div style="padding-top: 8px; padding-bottom: 3px;">
				<tt>职</tt>
				&nbsp;&nbsp;
				<tt> 位:</tt>
				<input id="zw" name="zw" class="easyui-combobox"
					style="width: 240px" maxlength="200"
					data-options="valueField:'id',textField:'office',url:'/EmployeeManage/getOfficeList',multiple:false">
			</div>
		</form>
		<div id="addDiv" style="text-align: center;">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)" onclick="saveUser()">保存</a> &nbsp;&nbsp; <a
				class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)" onclick="closeAddWin()">取消</a>
		</div>
	</div>


	<div id="updWin" class="easyui-window" closed="true"
		minimizable="false" maximizable="false" collapsible="false"
		modal="true" title="用户管理"
		style="top: 100px; left: 200px; width: 580px; height: 330px;"
		align="center">
		<form id="updForm" method="get" style="padding-top: 10px;"
			enctype="multipart/form-data">
			<div style="padding-top: 8px; padding-bottom: 3px;">
				<tt>用户名:</tt>
				<input class="easyui-validatebox" type="text" id="uyhm"
					style="width: 240px" name="uyhm" maxlength="200" />
			</div>
			<div style="padding-top: 8px; padding-bottom: 3px;">
				<tt>密</tt>
				&nbsp;&nbsp;
				<tt> 码:</tt>
				<input class="easyui-validatebox" type="text" id="umm"
					style="width: 240px" name="umm" maxlength="200" />
			</div>
			<div style="padding-top: 8px; padding-bottom: 3px;">
				<tt>姓</tt>
				&nbsp;&nbsp;
				<tt> 名:</tt>
				<input class="easyui-validatebox" type="text" id="uxm"
					style="width: 240px" name="uxm" maxlength="200" />
			</div>
			<div style="padding-top: 8px; padding-bottom: 3px;">
				<tt>角</tt>
				&nbsp;&nbsp;
				<tt> 色:</tt>
				<input id="ujs" name="ujs" class="easyui-combobox"
					style="width: 240px" maxlength="200"
					data-options="valueField:'id',textField:'role',url:'/EmployeeManage/getRoleList',multiple:false">
			</div>
			<div style="padding-top: 8px; padding-bottom: 3px;">
				<tt>部</tt>
				&nbsp;&nbsp;
				<tt> 门:</tt>
				<input id="ubm" name="ubm" class="easyui-combobox"
					style="width: 240px" maxlength="200"
					data-options="valueField:'id',textField:'dept',url:'/EmployeeManage/getDeptList',multiple:false">
			</div>
			<div style="padding-top: 8px; padding-bottom: 3px;">
				<tt>职</tt>
				&nbsp;&nbsp;
				<tt> 位:</tt>
				<input id="uzw" name="uzw" class="easyui-combobox"
					style="width: 240px" maxlength="200"
					data-options="valueField:'id',textField:'office',url:'/EmployeeManage/getOfficeList',multiple:false">
			</div>
		</form>
		<div id="updDiv" style="text-align: center;">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)" onclick="updateUser()">保存</a> &nbsp;&nbsp;
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)" onclick="closeAddWin()">取消</a>
		</div>
	</div>

	<script type="text/javascript">
	
		function append() {
			$('#addWin').window('open');
		}
		function deleteUser() {
			//alert("del")
			var selected = $('#dg').datagrid('getSelected');
			if (selected) {
				//alert(selected.id);
				$.post("/EmployeeManage/deleteUserDetail?userId=" + selected.id);
				$('#dg').datagrid('reload');
			} else {
				alert("请先选中数据");
				return
			}
			$('#dg').datagrid('reload');
		}
	
		var beforeId;
		function edit() {
			var selected = $('#dg').datagrid('getSelected');
			if (selected) {
				//alert(selected.id);
				beforeId = selected.id;
				document.getElementById("uyhm").value = selected.username;
				document.getElementById("umm").value = selected.password;
				document.getElementById("uxm").value = selected.realname;
				$('#updWin').window('open');
	
			} else {
				alert("请先选中数据");
				return
			}
	
		}
		function closeAddWin() {
			$('#addWin').window('close');
			$('#updWin').window('close');
		}
	
		function saveUser() {
			$('#userForm').form('submit', {
				url : "/EmployeeManage/addUserDetail",
				onSubmit : function(param) {
					param.yhm = userForm.yhm.value;
					param.mm = userForm.mm.value;
					param.xm = userForm.xm.value;
					param.jsid = $('#js').combobox("getValue");
					param.bmid = $('#bm').combobox("getValue");
					param.zwid = $('#zw').combobox("getValue");
				},
				success : function(data) {
					$('#addWin').window('close');
					$('#dg').datagrid('reload');
				}
			});
		}
	
		function updateUser() {
			$('#updForm').form('submit', {
				url : "/EmployeeManage/updateUserDetail",
				onSubmit : function(param) {
					//alert(beforeId);
					param.uid = beforeId;
					param.uyhm = updForm.uyhm.value;
					param.umm = updForm.umm.value;
					param.uxm = updForm.uxm.value;
					param.ujsid = $('#ujs').combobox("getValue");
					param.ubmid = $('#ubm').combobox("getValue");
					param.uzwid = $('#uzw').combobox("getValue");
				},
				success : function(data) {
					$('#updWin').window('close');
					$('#dg').datagrid('reload');
				}
			});
		}
	
		function check() {
			var flag = '<%=session.getAttribute("flag")%>';
			if (flag == "" || flag != "alt") {
				alert("非法访问！");
				top.location = '/EmployeeManage/jsp/login.jsp';
			}
		}
	</script>
</body>
</html>
