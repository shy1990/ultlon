<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
						<th>手机名称</th>
						<th>申请人</th>
						<th>申请时间</th>
						<th>类型</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${data.content }">
						<tr data-id="${item.content.id }"
							data-type="${item.content.afterSaleForm.type }"
							data-status="${item.content.status}" class=" b">
							<td>${item.content.afterSaleForm.goodsName }</td>
							<td>${item.content.afterSaleForm.username }</td>
							<td>${item.content.createdDate }</td>
					 	<c:choose>
								<c:when test="${item.content.afterSaleForm.type=='THH30' }">
									<td>30天退换货</td>
								</c:when>
								<c:when test="${item.content.afterSaleForm.type=='KXS' }">
									<td>开箱损</td>
								</c:when>
								<c:when test="${item.content.afterSaleForm.type=='WX' }">
									<td>维修</td>
								</c:when>
								<c:otherwise>
									<td>多美达百日换新</td>
								</c:otherwise>
							</c:choose>  
							<%-- <c:if test="${item.content.afterSaleForm.type}=='THH30'">
							<td>三十天退换货</td>
							</c:if> --%>

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
			$("#mytable tbody tr").each(function(i, o) {
				var status = $(o).attr("data-status");
				var type = $(o).attr("data-type");
				console.log(status);
				if (status == "NOPROCESS") {
					if (type == "KXS") {
						$(o).addClass("am-success");
					} else if (type == "WX") {
						$(o).addClass("am-warning");
					} else if (type == "THH30") {
						$(o).addClass("am-danger");
					} else if (type == "DMDHX100") {
						$(o).addClass("am-primary");
					}
					/* $(o).addClass("am-disabled"); */
				}

				/* 		switch (status) {
							case 'NOPROCESS':
								return
								break;
							case 'NOPROCESS':
								return
								break;
							case 'NOPROCESS':
								return
								break;
							case 'NOPROCESS':
								return
								break;
							}  */
			});

			$("#mytable").delegate("tr", "click", function() {
				var status = $(this).attr("data-status");
				if (status != "NOPROCESS") {
					return;
				}
				var id = $(this).attr("data-id");
				var type = $(this).attr("data-type");
				var targetUrl = "task/";

				switch (type) {
				case 'KXS':
					targetUrl += "kxs";
					break;
				case 'THH30':
					targetUrl += "thh30"
					break;
				case 'WX':
					targetUrl = "repair/edit"
					break;
				case 'DMDHX100':
					targetUrl += "dmdhx100"
					break;
				}
				location.href = targetUrl + "?taskId=" + id;

			});
		})
	</script>
</body>
</html>
