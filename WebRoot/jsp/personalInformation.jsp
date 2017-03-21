<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>个人信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
	href="../js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../js/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../js/demo/demo.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/locale/easyui-lang-zh_CN.js"></script>
</head>

<body onload="loadGRXX()">
	<div class="easyui-panel"
		style="width:100%;max-width:300px;padding:30px 60px;">
		<div id="username" style="font-size: 20px;"></div>
		<br>
		<div id="password" style="font-size: 20px;"></div>
		<br>
		<div id="realname" style="font-size: 20px;"></div>
		<br>
		<div id="dept" style="font-size: 20px;"></div>
		<br>
		<div id="office" style="font-size: 20px;"></div>
		<br>
		<div id="role"></div>
		<br> <a
			style="right: true;margin-right: 20px;padding-left: 7px;padding-right: 7px;padding-top: 3px;padding-bottom: 3px"
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="update()">修改信息</a>

		<div id="addWin" class="easyui-window" minimizable="false"
			maximizable="false" collapsible="false" modal="true" title="修改信息"
			closed="true"
			style="top: 100px; left: 200px; width: 500px;
	height: 300px;"
			align="center">
			<form id="userForm" method="get" style="padding-top: 10px;"
				enctype="multipart/form-data">

				<div style="padding-top: 10px; padding-bottom: 5px;margin-top: 5px">
					<label for="username" style="font-size: 15px">用户名:</label> <input
						class="easyui-validatebox" type="text" id="username"
						style="width: 220px" name="username" maxlength="200" />
				</div>
				<div style="padding-top: 5px; padding-bottom: 5px;margin-top: 5px">
					<label for="password" style="font-size: 15px">密&nbsp;&nbsp;&nbsp;码:</label>
					<input class="easyui-validatebox" type="text" id="password"
						style="width: 220px" name="password" />
				</div>
				<div style="padding-top: 5px; padding-bottom: 5px;margin-top: 5px">
					<label for="realname" style="font-size: 15px">姓&nbsp;&nbsp;&nbsp;名:</label>
					<input class="easyui-validatebox" type="text" id="realname"
						style="width: 220px" name="realname" />
				</div>
			</form>
			<div id="addDiv" style="text-align: center;">
				<a class="easyui-linkbutton" iconCls="icon-ok"
					href="javascript:void(0)"
					style="padding-left: 7px;padding-right: 7px;padding-top: 3px;padding-bottom: 3px"
					onclick="saveUser()">保存</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
					class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:void(0)"
					style="padding-left: 7px;padding-right: 7px;padding-top: 3px;padding-bottom: 3px"
					onclick="closeAddWin()">取消</a>
			</div>
		</div>
		<script type="text/javascript">
			function loadGRXX() {
				var flag = '<%=session.getAttribute("flag")%>';
				if (flag == "" || flag != "alt") {
					alert("非法访问！");
					top.location = '/EmployeeManage/jsp/login.jsp';
				}
				//alert("grxx");
				$.get("/EmployeeManage/getUserAction", function(data, status) {
					var obj = eval("(" + data + ")");
					if (obj.flag == "false") {
						alert("加载用户信息失败");
						return false;
					} else {
						userForm.username.value = obj.username;
						userForm.password.value = obj.password;
						userForm.realname.value = obj.realname;
						document.getElementById("username").innerHTML = "用户名: "
						+ obj.username;
						document.getElementById("password").innerHTML = "密&nbsp;&nbsp;&nbsp;码:  "
						+ obj.password;
						document.getElementById("realname").innerHTML = "姓&nbsp;&nbsp;&nbsp;名:  "
						+ obj.realname;
						document.getElementById("dept").innerHTML = "部&nbsp;&nbsp;&nbsp;门:  "
						+ obj.dept;
						document.getElementById("office").innerHTML = "职&nbsp;&nbsp;&nbsp;位:  "
						+ obj.office;
					}
				});
			}
		
			function update() {
				$('#addWin').window('open');
			}
		
			function closeAddWin() {
				$('#addWin').window('close');
			}
		
			function saveUser() {
				$('#userForm').form('submit', {
					url : "/EmployeeManage/basicUpdateUserAction",
					onSubmit : function(param) {
						param.username = userForm.username.value;
						param.password = userForm.password.value;
						param.realname = userForm.realname.value;
					},
					success : function(data) {
						$('#addWin').window('close');
						alert("更新成功，请重新登录");
						top.location = "/EmployeeManage/jsp/login.jsp";
					}
				});
			}
		</script>
	</div>
</body>
</html>
