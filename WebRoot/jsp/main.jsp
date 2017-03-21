<%@ page language="java" import="java.util.*,com.hpu.yggl.*"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>企业员工管理系统</title>
<link rel="stylesheet" type="text/css"
	href="../js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../js/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../js/demo/demo.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/locale/easyui-lang-zh_CN.js"></script>
</head>
<%
	String userId = (String) session.getAttribute("userId");
	String realName = (String) session.getAttribute("realName");
	List<String> ids = (List<String>) session.getAttribute("ids");
%>
<body class="easyui-layout" onload="loadFunction()">
	<div data-options="region:'north',border:false"
		style="height:80px;background:#B3DFDA;padding:10px;padding-left: 35px;
	padding-top: 20px;font-size: 25px;align-content: center;font-weight: bold;">
		企业员工管理系统&nbsp; <span style="margin-left: 20px">欢迎你:<%=realName%></span>
		<a
			style="right: true;margin-right: 20px;margin-left: 10px;padding-left: 6px;padding-right: 6px"
			href="login.jsp" class="easyui-linkbutton">注销</a>
	</div>

	<div id="funlist" data-options="region:'west',split:true,title:'操作权限'"
		style="width:18%;padding:10px;padding-left: 25px;padding-top: 20px"></div>
	<div id="content" data-options="region:'center',title:'显示内容'">
		<div class="easyui-tabs" id="tabs"
			data-options="fit:true,border:false,plain:true"></div>
	</div>

	<script>
		var indexid;
		function loadFunction() {
			//alert("load finshed");
			//$('#funlist').load("/GRSBSystem/getFunctionsAction");
			var flag = '<%=session.getAttribute("flag")%>';
			if (flag == "" || flag != "alt") {
				alert("非法访问！");
				top.location = '/EmployeeManage/jsp/login.jsp';
				return;
			}
			$.get("/EmployeeManage/getFunctionsAction", function(data, status) {
				//alert("Data: " + data + "\nStatus: " + status);
				var obj = eval("(" + data + ")");
				if (obj.flag == "false") {
					alert("权限加载失败");
					return false;
				} else {
					//alert("权限加载成功");
					var res = "";
					//document.getElementById("funlist").innerHTML=obj.functions[0].name;
					//alert(obj.functions[0].func);
					for (var i = 0; i < obj.functions.length; i++) {
						var r = obj.functions[i].func;
						//alert(r);
						var indexid = obj.functions[i].id;
						var index = i + 1;
						//res = res + "<div id='" + indexid
						//	+ "' onclick='addTab(" + index + ")'><h2>" + r
						//+ "</h2></div></br></br>";
						var myurl = "";
						res = res + "<div id='" + indexid
							+ "' ><h2>"
							+ r + "</h2></div></br></br>";
						switch (indexid) {
						case "9f42403e96de44e8ba3cf86ddb6d83e9":
							myurl = "personalInformation.jsp";
							break;
						case "a4c96dafe68b4a149694fd38875595e4":
							myurl = "deptManage.jsp";
							break;
						case "dd34959ead604355aedb32b9a93aecbd":
							myurl = "roleManage.jsp";
							break;
						case "67925eeed0d744dc8f49f8fb0596d71a":
							myurl = "officeManage.jsp";
							break;
						case "deb9a8043bca453eb93557617b8e54db":
							myurl = "userManage.jsp";
							break;
						case "08c8b36d65744f0fa87a7ba6ee034ac7":
							myurl = "funcManage.jsp";
							break;
	
						}
	
						addTab(r, myurl);
					}
	
					document.getElementById("funlist").innerHTML = res;
					//alert(res);
					addTab('帮助', 'help.html');
					addTab('关于', 'about.html');
				}
			});
		}
	
		function addTab(title, url) {
			//var url = "about.html";
			if ($('#tabs').tabs('exists', title)) {
				$('#tabs').tabs('select', title);
			} else {
				var content = '<iframe scrolling="auto" frameborder="0"  src="'
					+ url + '" style="width:100%;height:100%;"></iframe>';
				$('#tabs').tabs('add', {
					title : title,
					content : content,
					closable : false
				});
			}
		}
	</script>
</body>
</html>