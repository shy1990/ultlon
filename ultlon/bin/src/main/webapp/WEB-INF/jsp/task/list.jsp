<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
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
			<table id="mytable"
				class="am-table am-table-bordered am-table-striped am-table-hover">
				<thead>
					<tr>
						<th>手机串号</th>
						<th>申请人</th>
						<th>申请时间</th>
						<th>类型</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${data.content }">
						<tr data-id="${item.content.id }">
							<td>${item.content.afterForm.imei }</td>
							<td>${item.content.afterForm.username }</td>
							<td>${item.content.createdDate }</td>
							<td class="am-danger">${item.content.afterForm.type }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/amazeui.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#mytable").delegate("tr", "click", function() {
				var id = $(this).attr("data-id");
				location.href = "task/edit?id=" + id;
			});
		})
	</script>
</body>
</html>

