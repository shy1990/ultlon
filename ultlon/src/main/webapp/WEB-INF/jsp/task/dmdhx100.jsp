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
.a1{
    width: 100%;
    height:100px;
   padding-top:29px; 
   font-size:24px;
}
</style>
</head>
<body>
	<%@include file="../../common/navbar.jsp"%>
	<form action="change" method="post">
	<div class="content">
	<h1>多美达百日换新</h1>
		<div class="am-g">
		    <input type="hidden" name="taskId" value="${taskForm.id }">
			<div class="am-input-group">
				<span class="am-input-group-label">订单号:</span> <input type="text"
					class="am-form-field"
					placeholder="${taskForm.afterSaleForm.orderNum}" disabled="disabled">
			</div>
			<div class="am-input-group">
				<span class="am-input-group-label">串&nbsp;&nbsp;&nbsp;&nbsp;号:</span>
				<input type="text" class="am-form-field"
					placeholder="${taskForm.afterSaleForm.imei}" disabled="disabled">
			</div>
			<div class="am-input-group">
				<span class="am-input-group-label">客&nbsp;&nbsp;&nbsp;&nbsp;户:</span>
				<input type="text" class="am-form-field"
					placeholder="${taskForm.afterSaleForm.username}" disabled="disabled">
			</div>
			<div class="am-input-group am-input-group-primary">
					<span class="am-input-group-label">备注</span> 
						<textarea rows="6" cols="118" placeholder="请输入换新备注"  name="remark"></textarea>
				</div>
		    <input class="am-btn am-btn-danger a1"
					type="submit" value="百日换新"></input>
			<%--  <a class="am-btn am-btn-success a1" href="change/edit?id=${taskForm.id }">百日换新</a> --%>
		</div>
	</div>
	</form>
	<script src="js/jquery.min.js"></script>
	<script src="js/amazeui.min.js"></script>

</body>
</html>

