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
<title>售后管理页面</title>
</head>
<body>
<a class="am-btn am-btn-primary am-btn-block" href="admin/refund?sort=createdDate,desc">退货退款单</a>
<a class="am-btn am-btn-warning am-btn-block" href="admin/change?sort=createdDate,desc">换货单</a>
<a class="am-btn am-btn-success am-btn-block" href="admin/repair?sort=createdDate,desc">维修单</a>

	<script src="js/jquery.min.js"></script>
	<script src="js/amazeui.min.js"></script>
</body>
</html>