<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>" />
<meta charset="UTF-8">
<link rel="stylesheet" href="css/amazeui.min.css" />
<title>修改维修单</title>
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
			<%
				//TODO 石洪岳 ----> 退货价格确认。必须有明确返回。
			%>
			石洪岳 ----> 退货价格确认。必须有明确返回。 POST refund taskId,remark
			<form action="refund" method="post">
				<input type="hidden" name="taskId" value="${taskForm.id }">
				成交价:<input type="text" name="" readonly="readonly"
					value="${refundForm.orderPrice }"><br> 现价:<input
					type="text" name="" readonly="readonly"
					value="${refundForm.refundMoney }"><br> 应退:<input
					type="text" name="" readonly="readonly"
					value="${refundForm.realRefundMoney }"><br> <input
					type="submit" value="保存">

			</form>
			<a href="javascript:history.back(1);">返回</a>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/amazeui.min.js"></script>
</body>
</html>