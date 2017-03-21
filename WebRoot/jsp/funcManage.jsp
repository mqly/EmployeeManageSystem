<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>权限管理</title>
<link rel="stylesheet" type="text/css"
	href="../js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../js/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../js/demo/demo.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/locale/easyui-lang-zh_CN.js"></script>
</head>
<body onload="check()">
	<table id="dg" class="easyui-datagrid" title="权限信息"
		style="width:100%;height:auto" rownumbers="true" pagination="true"
		data-options="toolbar: '#tb',iconCls:'icon-edit',singleSelect:true,url:'/EmployeeManage/getSomeFuncs',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'id',width:300,halign:'center'">权限 ID</th>
				<th data-options="field:'func',width:200,halign:'center'">权限名称</th>
			</tr>
		</thead>
	</table>

	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true"
			onclick="deleteFunction()">删除</a> <a href="javascript:void(0)"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true" onclick="edit()">修改</a>
	</div>


	<div id="addWin" class="easyui-window" closed="true"
		minimizable="false" maximizable="false" collapsible="false"
		modal="true" title="添加权限"
		style="top: 100px; left: 200px; width: 500px; height: 300px;"
		align="center">
		<form id="funcForm" method="get" style="padding-top: 10px;"
			enctype="multipart/form-data">
			<div style="padding-top: 15px; padding-bottom: 5px;">
				<label for="qxmc">权限名称:</label> <input class="easyui-validatebox"
					type="text" id="qxmc" style="width: 240px" name="qxmc"
					maxlength="200" />
			</div>
		</form>
		<div id="addDiv" style="text-align: center;">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)" onclick="saveFunction()">保存</a>
			&nbsp;&nbsp; <a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)" onclick="closeAddWin()">取消</a>
		</div>
	</div>


	<div id="updWin" class="easyui-window" closed="true"
		minimizable="false" maximizable="false" collapsible="false"
		modal="true" title="修改权限"
		style="top: 100px; left: 200px; width: 500px; height: 300px;"
		align="center">
		<form id="updForm" method="get" style="padding-top: 10px;"
			enctype="multipart/form-data">
			<div style="padding-top: 15px; padding-bottom: 5px;">
				<label for="updqxmc">权限名称:</label> <input class="easyui-validatebox"
					type="text" id="iupdqxmc" style="width: 240px" name="iupdqxmc"
					maxlength="200" />
			</div>
		</form>
		<div id="updDiv" style="text-align: center;">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)" onclick="updateFunction()">保存</a> &nbsp;&nbsp;
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)" onclick="closeAddWin()">取消</a>
		</div>
	</div>

	<script type="text/javascript">
	
		function append() {
			$('#addWin').window('open');
		}
		function deleteFunction() {
			var selected = $('#dg').datagrid('getSelected');
			if (selected) {
				//alert(selected.id);
				if (selected.id == "1b20a88d6e754791bfa3dce322e7b5e0") {
					alert("暂无权限为预留项不可删除");
					return;
				}
				$.post("/EmployeeManage/deleteFunc?id=" + selected.id);
				$('#dg').datagrid('reload');
			} else {
				alert("请先选中数据");
				return;
			}
			$('#dg').datagrid('reload');
		}
	
		var beforeId;
		function edit() {
			var selected = $('#dg').datagrid('getSelected');
			if (selected) {
				//alert(selected.id);
				beforeId = selected.id;
				document.getElementById("iupdqxmc").value = selected.func;
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
	
		function saveFunction() {
			$('#funcForm').form('submit', {
				url : "/EmployeeManage/addFunction",
				onSubmit : function(param) {
					param.qxmc = funcForm.qxmc.value;
				},
				success : function(data) {
					$('#addWin').window('close');
					$('#dg').datagrid('reload');
				}
			});
		}
	
		function updateFunction() {
			$('#updForm').form('submit', {
				url : "/EmployeeManage/updateFunc",
				onSubmit : function(param) {
					param.id = beforeId;
					param.qxmc = updForm.iupdqxmc.value;
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
