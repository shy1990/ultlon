<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="http://cdn.amazeui.org/amazeui/2.4.0/css/amazeui.min.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="http://cdn.amazeui.org/amazeui/2.4.0/js/amazeui.min.js"></script>
<title>修改维修单</title>
</head>
<body>
	首页
	<form action="repair/${form.id}" method="post">
		串码:<input type="text" name="imei" value="${form.imei }"><br>用户名:<input
			type="text" name="username" value="${form.username }"><br>
		备注:<input type="text" name="remark" value="${form.remark }"><br>
		<input type="submit" value="保存">
	</form>
</body>
</html>