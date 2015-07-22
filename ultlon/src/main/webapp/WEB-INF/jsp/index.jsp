<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8"/>
<shiro:hasRole name="admin">
	<meta http-equiv="refresh" content="0;url=admin"/>
</shiro:hasRole>
<shiro:hasRole name="app">
	<meta http-equiv="refresh" content="0;url=app"/>
</shiro:hasRole> 
<shiro:hasRole name="excel">
	<meta http-equiv="refresh" content="0;url=excel"/> 
</shiro:hasRole> 
<shiro:hasRole name="register">
	<meta http-equiv="refresh" content="0;url=register"/> 
</shiro:hasRole>         
<title>售后</title>
</head>
<body>
	<span id="username"><shiro:principal property="username"></shiro:principal></span>
	正在转向首页..
</body>
</html>