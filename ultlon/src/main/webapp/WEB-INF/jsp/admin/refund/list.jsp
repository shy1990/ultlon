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
<head>
<base href="<%=basePath%>" />
<meta charset="UTF-8">
<link rel="stylesheet" href="css/amazeui.min.css" />
<title>退货退款申请</title>
<style>
.content {
	text-align: center;
	padding: 50px 0;
}
</style>
</head>
<body>
	<table
		class="am-table am-table-bordered am-table-striped am-table-hover">
		<thead>
			<tr>
				<th>申请人</th>
				<th>手机</th>
				<th>串号</th>
				<th>退款金额</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${data.content }">
				<tr class="am-active">
					<td>${item.content.afterForm.username }</td>
					<td>${item.content.afterForm.goodsName }</td>
					<td>${item.content.afterForm.imei }</td>
					<td>${item.content.realRefundMoney }</td>
					<td>${item.content.status }</td>
					<td><c:if test='${item.content.status eq "NOPROCESS"}'>
							<button type="button" class="am-btn am-btn-success am-radius" onclick="agree('${item.content.id}');">同意</button>
							<button type="button" class="am-btn am-btn-danger am-radius"  onclick="reject('${item.content.id}');">拒绝</button>
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


	<script src="js/jquery.min.js"></script>
	<script src="js/amazeui.min.js"></script>
	<script type="text/javascript">
		function agree(id) {
			$.post("admin/refund/" + id + "/AGREE", function(data) {
				if (data === 'ok') {
					location.reload();
				}
			});
		}
		function reject(id) {
			$.post("admin/refund/" + id + "/REJECT", function(data) {
				if (data === 'ok') {
					location.reload();
				}
			});
		}
	</script>
</body>
</html>