<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>角色管理</title>
<link rel="stylesheet" type="text/css"
	href="../js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../js/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../js/demo/demo.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/locale/easyui-lang-zh_CN.js"></script>
</head>
<body onload="check()">
	<table id="dg" class="easyui-datagrid" title="角色信息"
		style="width:100%;height:auto" rownumbers="true" pagination="true"
		data-options="toolbar: '#tb',iconCls:'icon-edit',singleSelect:true,url:'/EmployeeManage/getSomeRoles',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'id',width:300,halign:'center'">角色 ID</th>
				<th data-options="field:'role',width:200,halign:'center'">角色名称</th>
				<th data-options="field:'func',width:200,halign:'center'">权限名称</th>
			</tr>
		</thead>
	</table>

	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
		<!--  角色不提供删除功能
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true"
			onclick="deleteRole()">删除</a> 
		-->
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true" onclick="edit()">修改</a>
	</div>


	<div id="addWin" class="easyui-window" closed="true"
		minimizable="false" maximizable="false" collapsible="false"
		modal="true" title="添加角色"
		style="top: 100px; left: 200px; width: 500px; height: 300px;"
		align="center">
		<form id="roleForm" method="get" style="padding-top: 10px;"
			enctype="multipart/form-data">
			<div style="padding-top: 15px; padding-bottom: 5px;">
				<label for="jsmc">角色名称:</label> <input class="easyui-validatebox"
					type="text" id="jsmc" style="width: 240px" name="jsmc"
					maxlength="200" />
			</div>
			<div style="padding-top: 15px; padding-bottom: 5px;">
				<label for="qxmc">权限名称:</label> <input id="qx" name="qx"
					class="easyui-combobox" style="width: 240px" maxlength="200"
					data-options="valueField:'id',textField:'func',url:'/EmployeeManage/getFunctionList',multiple:true">
			</div>

		</form>
		<div id="addDiv" style="text-align: center;">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)" onclick="saveRole()">保存</a> &nbsp;&nbsp; <a
				class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)" onclick="closeAddWin()">取消</a>
		</div>
	</div>


	<div id="updWin" class="easyui-window" closed="true"
		minimizable="false" maximizable="false" collapsible="false"
		modal="true" title="修改角色"
		style="top: 100px; left: 200px; width: 500px; height: 300px;"
		align="center">
		<form id="updForm" method="get" style="padding-top: 10px;"
			enctype="multipart/form-data">

			<!--	修改角色时显示角色id
				<div style="padding-top: 15px; padding-bottom: 5px;">	
				<label for="updjsid">角 色  ID:</label> <input
					class="easyui-validatebox" type="text" id="iupdjsid"
					style="width: 240px" name="iupdjsid" maxlength="200"
					readonly="readonly" />
			    </div>
			-->
			<div style="padding-top: 15px; padding-bottom: 5px;">
				<label for="updjsmc">角色名称:</label> <input class="easyui-validatebox"
					type="text" id="iupdjsmc" style="width: 240px" name="iupdjsmc"
					maxlength="200" />
			</div>
			<div style="padding-top: 15px; padding-bottom: 5px;">
				<label for="uqxmc">权限名称:</label> <input id="uqx" name="uqx"
					class="easyui-combobox" style="width: 240px" maxlength="200"
					data-options="valueField:'id',textField:'func',url:'/EmployeeManage/getFunctionList',multiple:true">
			</div>
		</form>
		<div id="updDiv" style="text-align: center;">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)" onclick="updateRole()">保存</a> &nbsp;&nbsp;
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)" onclick="closeAddWin()">取消</a>
		</div>
	</div>

	<script type="text/javascript">
	
		function append() {
			$('#addWin').window('open');
		}
		/* 角色不提供删除功能
			function deleteRole() {
				var selected = $('#dg').datagrid('getSelected');
				if (selected) {
					//alert(selected.id);
					$.post("/EmployeeManage/deleteRole?id=" + selected.id);
					$('#dg').datagrid('reload');
				} else {
					alert("请先选中数据");
					return
				}
				$('#dg').datagrid('reload');
			}
			*/
	
		var beforeId;
		function edit() {
			var selected = $('#dg').datagrid('getSelected');
			if (selected) {
				//alert(selected.id);
				beforeId = selected.id;
				//修改角色时显示角色id
				//document.getElementById("iupdjsid").value = selected.id;
				document.getElementById("iupdjsmc").value = selected.role;
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
	
		function saveRole() {
			//获取选中value
			//var ids = $('#qx').combobox("getValues");
			//获取选中text
			//var tts = $('#qx').combobox("getText");
			$('#roleForm').form('submit', {
				url : "/EmployeeManage/addRole",
				onSubmit : function(param) {
					param.jsmc = roleForm.jsmc.value;
					param.funcs = $('#qx').combobox("getValues");
				},
				success : function(data) {
					$('#qx').combobox("clear");
					$('#addWin').window('close');
					$('#dg').datagrid('reload');
				}
			});
		}
	
		function updateRole() {
			$('#updForm').form('submit', {
				url : "/EmployeeManage/updateRole",
				onSubmit : function(param) {
					param.id = beforeId;
					param.jsmc = updForm.iupdjsmc.value;
					param.qxs = $('#uqx').combobox("getValues");
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
