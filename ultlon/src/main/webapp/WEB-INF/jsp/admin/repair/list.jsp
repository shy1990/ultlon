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
<script type="text/javascript" src="jquery.min.js"></script>
<base href="<%=basePath%>" />
<meta charset="UTF-8">
<link rel="stylesheet" href="css/amazeui.min.css" />
<title>退货退款申请</title>
<link rel="stylesheet" href="css/lanrenzhijia.css" media="all">
<style>
.content {
	text-align: center;
	padding: 50px 0;
}
</style>

<script src="js/jquery.min.js"></script>
<script src="js/amazeui.min.js"></script>
<script src="jquery.min.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$('.am-radius').click(function() {
			$('.theme-popover-mask').fadeIn(100);
			$('.theme-popover').slideDown(200);
		})
		
		
		$('.submittwo').click(function() {
			$('.theme-popover-mask').fadeOut(100);
			$('.theme-popover').slideUp(100);
		})
		
		
		

	})
	
	
	
	
	

	function reject(id) {
		$.post("admin/repair/" + id + "/REJECT", function(data) {
			if (data === 'ok') {
				location.reload();
			}
		});
	}
</script>

</head>
<body>
	<table
		class="am-table am-table-bordered am-table-striped am-table-hover">
		<thead>
			<tr>
				<th>申请人</th>
				<th>手机</th>
				<th>串号</th>
				<th>维修金额</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${data.content }">
				<tr class="am-active">
					<td>${item.content.taskForm.afterSaleForm.username }</td>
					<td>${item.content.taskForm.afterSaleForm.goodsName }</td>
					<td>${item.content.taskForm.afterSaleForm.imei }</td>
					<td>${item.content.cost}</td>
					<td>${item.content.status }</td>
					<td><c:if test='${item.content.status eq "NOPROCESS"}'>
							<button type="button" class="am-btn am-btn-success am-radius"
								onclick="agree('${item.content.id}');">同意</button>
							<button type="button" class="am-btn am-btn-danger am-radius"
								onclick="reject('${item.content.id}');">拒绝</button>
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>




	<div class="theme-popover">
		<div class="theme-poptit">
			<a href="javascript:;" title="关闭" class="close">×</a>
			<h1>请输入退款金额</h1>
		</div>
		<div class="theme-popbod dform">
				<form action="/admin/repair/save" method="post">
					<input id="log" class="ipt" type="text" name="log"
						value="lanrenzhijia" size="20" />
					<input
						style="text-center:right; font-size: 15px; red; background: white"
						type="submit" class="submittwo" value="确定">
					<input
						style="text-center:right; font-size: 15px; red; background: white"
						type="button" class="submittwo" value="取消">
				</form>
		</div>
	</div>
	
</body>
</html>