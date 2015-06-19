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
<title>修改换新单</title>
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
				//TODO 石洪岳 ----> 换新确认。必须有明确返回。
			%>
			石洪岳 ---->换新确认。必须有明确返回。 POST change afterSaleId,remark。
			手机名称:${afterSaleForm.goodsName },,,,,串号:${afterSaleForm.imei }
			<form action="change" method="post">
				<input type="hidden" name="afterSaleId" value="${afterSaleForm.id }">
				备注:<input type="text" name="remark" value="${changeForm.remark }"><br>
				<input type="submit" value="保存">
			</form>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/amazeui.min.js"></script>
</body>
</html>