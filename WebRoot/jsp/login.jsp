<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>登录页面</title>
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

<body background="../image/loginbg.jpg"
	style="background-repeat: no-repeat;background-attachment: scroll;margin: auto;background-size:cover">
	<div class="easyui-panel" title="企业员工管理系统" style="width:400px;">
		<div style="padding:10px 60px 20px 60px">
			<form id="ff" method="post">
				<table cellpadding="5">
					<tr>
						<td>用户名:</td>
						<td><input class="easyui-textbox" type="text" name="username"
							data-options="required:true,iconCls:'icon-man',iconAlign:'right'"></input></td>
					</tr>
					<tr>
						<td>密&nbsp;&nbsp;&nbsp;码:</td>
						<td><input class="easyui-passwordbox" type="text"
							name="password"
							data-options="required:true,validType:'password',iconAlign:'right'"></input></td>
					</tr>
				</table>
			</form>
			<div
				style="text-align:center;padding:5px;margin-right: 50px;margin-left: 50px ">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					style="padding-left: 7px;padding-right: 7px;padding-top: 3px;padding-bottom: 3px"
					onclick="submitForm()">登录</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton"
					style="padding-left: 7px;padding-right: 7px;padding-top: 3px;padding-bottom: 3px"
					onclick="clearForm()">取消</a>
			</div>
		</div>
	</div>

	<script>
		function submitForm() {
			$('#ff').form('submit', {
				url : "/EmployeeManage/loginAction",
				onSubmit : function(param) {
					if (ff.username.value == "" || ff.password.value == "") {
						alert("请输入用户名密码");
						return false;
					}
					param.username = ff.username.value;
					param.password = ff.password.value;
				},
				success : function(data) {
					//alert(data);
					var obj = eval("(" + data + ")");
					if (obj.flag == "false") {
						alert("用户名密码错误");
						return false;
					} else {
						//alert("登录成功");
						//window.location.href = "/EmployeeManage/jsp/main.jsp?userid="+obj.message;
						window.location.href = "/EmployeeManage/jsp/main.jsp";
					}
				}
			});
		}
		function clearForm() {
			$('#ff').form('clear');
		}
	</script>
</body>
</html>
