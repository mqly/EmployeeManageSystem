<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>部门管理</title>
<link rel="stylesheet" type="text/css"
	href="../js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../js/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../js/demo/demo.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/locale/easyui-lang-zh_CN.js"></script>
</head>
<body onload="check()">
	<table id="dg" class="easyui-datagrid" title="部门信息"
		style="width:100%;height:auto" rownumbers="true" pagination="true"
		data-options="toolbar: '#tb',iconCls:'icon-edit',singleSelect:true,url:'/EmployeeManage/getSomeDepts',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'id',width:300,halign:'center'">部门 ID</th>
				<th data-options="field:'dept',width:200,halign:'center'">部门名称</th>
			</tr>
		</thead>
	</table>

	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true"
			onclick="deleteDept()">删除</a> <a href="javascript:void(0)"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true" onclick="edit()">修改</a>
	</div>


	<div id="addWin" class="easyui-window" closed="true"
		minimizable="false" maximizable="false" collapsible="false"
		modal="true" title="添加部门"
		style="top: 100px; left: 200px; width: 500px; height: 300px;"
		align="center">
		<form id="deptForm" method="get" style="padding-top: 10px;"
			enctype="multipart/form-data">
			<div style="padding-top: 15px; padding-bottom: 5px;">
				<label for="bmmc">部门名称:</label> <input class="easyui-validatebox"
					type="text" id="bmmc" style="width: 240px" name="bmmc"
					maxlength="200" />
			</div>
		</form>
		<div id="addDiv" style="text-align: center;">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)" onclick="saveDept()">保存</a> &nbsp;&nbsp; <a
				class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)" onclick="closeAddWin()">取消</a>
		</div>
	</div>


	<div id="updWin" class="easyui-window" closed="true"
		minimizable="false" maximizable="false" collapsible="false"
		modal="true" title="修改部门"
		style="top: 100px; left: 200px; width: 500px; height: 300px;"
		align="center">
		<form id="updForm" method="get" style="padding-top: 10px;"
			enctype="multipart/form-data">
			<div style="padding-top: 15px; padding-bottom: 5px;">
				<label for="updbmmc">部门名称:</label> <input class="easyui-validatebox"
					type="text" id="iupdbmmc" style="width: 240px" name="updbmmc"
					maxlength="200" />
			</div>
		</form>
		<div id="updDiv" style="text-align: center;">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)" onclick="updateDept()">保存</a> &nbsp;&nbsp;
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)" onclick="closeAddWin()">取消</a>
		</div>
	</div>

	<script type="text/javascript">
	
		function append() {
			$('#addWin').window('open');
		}
		function deleteDept() {
			var selected = $('#dg').datagrid('getSelected');
			if (selected) {
				//alert(selected.id);
				if (selected.id == "895acfeabb5c476b9df826ad58bf1282") {
					alert("暂无部门为预留项不可删除");
					return;
				}
				$.post("/EmployeeManage/deleteDept?id=" + selected.id);
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
				document.getElementById("iupdbmmc").value = selected.dept;
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
	
		function saveDept() {
			$('#deptForm').form('submit', {
				url : "/EmployeeManage/addDept",
				onSubmit : function(param) {
					param.bmmc = deptForm.bmmc.value;
				},
				success : function(data) {
					$('#addWin').window('close');
					$('#dg').datagrid('reload');
				}
			});
		}
	
		function updateDept() {
			$('#updForm').form('submit', {
				url : "/EmployeeManage/updateDept",
				onSubmit : function(param) {
					param.id = beforeId;
					param.bmmc = updForm.updbmmc.value;
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
