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
<link rel="stylesheet" href="css/amazeui.min.css" />
<style type="text/css">
.sj-btn-block {
	display: block;
	height: 150px;
	width: 100%;
}
</style>
<title>售后app页面</title>
</head>
<body>
	<%@include file="../common/navbar.jsp"%>
	<a href="task?sort=createdDate,desc" class="am-btn am-btn-primary sj-btn-block ">待处理售后</a>
	<a href="task/historyList?sort=createdDate,desc" class="am-btn am-btn-default sj-btn-block">售后历史</a>
	<a class="am-btn am-btn-secondary sj-btn-block" href="task/help">帮助中心</a>

	<script src="js/jquery.min.js"></script>
	<script src="js/amazeui.min.js"></script>
</body>
</html>