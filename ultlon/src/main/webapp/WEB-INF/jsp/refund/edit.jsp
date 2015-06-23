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

.a1 {
	width: 100%;
	height: 100px;
	padding-top: 29px;
	font-size: 24px;
}
</style>
</head>
<body>

	<%@include file="../../common/navbar.jsp"%>
	<div class="content">
		<div class="am-g">

			<form action="refund" method="post">
				<input type="hidden" name="taskId" value="${taskForm.id }">
				<div class="am-input-group">
					<span class="am-input-group-label">成交价:</span> <input type="text"
						class="am-form-field" placeholder="${refundForm.orderPrice }" disabled="disabled">
				</div>
				<div class="am-input-group">
					<span class="am-input-group-label">现&nbsp;&nbsp;&nbsp;&nbsp;价:</span>
					<input type="text" class="am-form-field"
						placeholder="${refundForm.currentPrice }" disabled="disabled">
				</div>
				<div class="am-input-group">
					<span class="am-input-group-label">应&nbsp;&nbsp;&nbsp;&nbsp;退:</span>
					<input type="text" class="am-form-field"
						placeholder="${refundForm.realRefundMoney }" disabled="disabled">
				</div>
				<div class="am-input-group am-input-group-primary">
					<span class="am-input-group-label">备注</span>
					<textarea rows="6" cols="118" placeholder="请输入退货备注" id="wen"
						name="remark"></textarea>
				</div>
				<input class="am-btn am-btn-danger a1" type="submit" value="退货确认"></input>

			</form>
			<a class="am-btn  a1" href="javascript:history.back(1);">返回</a>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/amazeui.min.js"></script>
</body>
</html>