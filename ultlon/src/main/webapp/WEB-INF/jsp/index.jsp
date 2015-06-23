<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<meta charset="UTF-8">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<shiro:hasRole name="admin"><meta http-equiv="refresh" content="0;url=admin"></shiro:hasRole>
<shiro:hasRole name="app"><meta http-equiv="refresh" content="0;url=app"></shiro:hasRole>
<title>售后</title>
</head>
<body>
正在转向首页..
</body>
</html>