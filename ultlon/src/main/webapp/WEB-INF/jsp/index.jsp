<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="http://cdn.amazeui.org/amazeui/2.4.0/css/amazeui.min.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="http://cdn.amazeui.org/amazeui/2.4.0/js/amazeui.min.js"></script>
<title>售后</title>
</head>
<body>首页<% //TODO jsp获取当前登录用户demo %>
你好：<shiro:principal property="username"/>
</body>
</html>