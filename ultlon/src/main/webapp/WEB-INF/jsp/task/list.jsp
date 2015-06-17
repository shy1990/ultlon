<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<tr data-id="1" data-type="开箱损">
						<td>111111</td>
						<td>三际电子商务测试手机店</td>
						<td>15年6月25日 15点52分</td>
						<td class="am-danger">开箱损</td>
					</tr>
					<tr data-id="2" data-type="30天退换货">
						<td>111111</td>
						<td>三际电子商务测试手机店</td>
						<td>15年6月25日 15点52分</td>
						<td class="am-primary">30天退换货</td>
					</tr>
					<tr data-id="3" data-type="维修">
						<td>111111</td>
						<td>三际电子商务测试手机店</td>
						<td>15年6月25日 15点52分</td>
						<td class="am-warning">维修</td>
					</tr>
					<tr data-id="4" data-type="多美达百日换新">
						<td>111111</td>
						<td>三际电子商务测试手机店</td>
						<td>15年6月25日 15点52分</td>
						<td class="am-success">多美达百日换新</td>
					</tr>
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
				var type = $(this).attr("data-type");
				var targetUrl="";
				switch (type) {
				case '开箱损':
					targetUrl="kxs";
					break;
				case '30天退换货':
					targetUrl="thh30"
					break;
				case '维修':
					targetUrl="repair/edit"
					break;
				case '多美达百日换新':
					targetUrl="dmdhx100"
					break;
				}
				location.href=targetUrl+"?id="+id;
			});
		})
	</script>
</body>
</html>

