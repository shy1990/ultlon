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
.am-input-group {
	width: 100%;
}

.am-input-group-label {
	text-align: right;
	width: 40%;
}

.am-form-field {
	width: 60%
}
</style>
</head>
<body>
	<%@include file="../../common/navbar.jsp"%>
	<div class="content">
		<div class="am-g">
			<%-- 	售后业务受理页面。
			<% //TODO 石洪岳 ----> 验机流程，所有条件满足才可以点击退货或者换货  %>
			<p>石洪岳 ----> 维修页面美化，提交接口 POST repair afterSaleId,remark</p>
			<hr>
			保存时要提交的参数:${afterSaleForm.id }, --%>
			<div class="am-input-group">
				<span class="am-input-group-label">订单号:</span> <input type="text"
					class="am-form-field"
					placeholder="${taskForm.afterSaleForm.orderNum}">
			</div>
			<div class="am-input-group">
				<span class="am-input-group-label">串&nbsp;&nbsp;&nbsp;&nbsp;号:</span>
				<input type="text" class="am-form-field"
					placeholder="${taskForm.afterSaleForm.imei}">
			</div>
			<div class="am-input-group">
				<span class="am-input-group-label">客&nbsp;&nbsp;&nbsp;&nbsp;户:</span>
				<input type="text" class="am-form-field"
					placeholder="${taskForm.afterSaleForm.username}">
			</div>
		
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/amazeui.min.js"></script>

</body>
</html>

