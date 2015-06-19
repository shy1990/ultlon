<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<base href="<%=basePath%>" />
<meta charset="UTF-8">
<title>三际手机采购网 业务登录</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet" href="css/amazeui.min.css" />
<style>
.content {
	text-align: center;
	padding: 50px 0;
}
</style>
</head>
<body>
	<%@include file="../../common/navbar.jsp"%>
	<div class="content">
		<div class="am-g">
			售后业务受理页面。
			<% //TODO 石洪岳 ----> 验机流程，所有条件满足才可以点击退货或者换货  %>
			<p>石洪岳 ----> 验机流程，所有条件满足才可以点击退货或者换货 ,拒绝受理接口 POST task/${task.id }/reject  </p>taskId,remark</p>
			<hr>
			订单号:${taskForm.afterSaleForm.orderNum }<br>
			串号:${taskForm.afterSaleForm.imei }<br>
			用户:${taskForm.afterSaleForm.username }<br>
			<a class="am-btn am-btn-danger" href="refund/edit?id=${taskForm.id }">我要退货</a>
			<a class="am-btn am-btn-success" href="change/edit?id=${taskForm.id }">我要换货</a>
			<a class="am-btn am-btn-success" href="javascript:reject('${taskForm.id }');">拒绝受理</a>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/amazeui.min.js"></script>
	<script type="text/javascript">
		function reject(id){
			//task/${task.id }/reject
			alert(id);
		}
	</script>
</body>
</html>
