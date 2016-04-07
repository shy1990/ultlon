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
<link rel="stylesheet" href="../css/amazeui.min.css" />
<style>
.content {
	text-align: center;
	padding: 50px 0;
}

.phone_main_07 {
	padding-top: 20px;
	width: 500px;
	margin: 0 auto;
}

</style>
</head>
<body>

	<%@include file="../../common/navbar.jsp"%>
	  <div class="am-collapse am-topbar-collapse" id="collapse-head">
		<div class="am-topbar-right" style="margin-right: 200px;">
			<div class="am-u-lg-6">
				<div class="am-input-group">
				<span
						class="am-input-group-btn"> <input type="text" class="am-form-field" style="width: 300px"
						id="status" placeholder="请输入要查询的状态" value="${status}">
						<button class="am-btn am-btn-default" type="button"
							onclick="goSearch();" style="margin-right: 5px;">搜索</button>
						<button class="am-btn am-btn-default" type="button"
							onclick="goAll();">查询全部</button>
					</span>


				</div>
			</div>
		</div>
	</div>  
	
	<div class="content">
		<div class="am-g">
			<table id="mytable1"
				class="am-table am-table-bordered am-table-striped am-table-hover">
				<thead>
					<tr>
						<th>手机名称</th>
						<th>申请人</th>
						<th>申请时间</th>
						<th>类型</th>
						<th>处理状态</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody>
					<div id="labelDiv">
						<c:forEach var="item" items="${data.content }">
							<tr data-id="${item.content.id }"
								data-type="${item.content.afterSaleForm.type }"
								data-status="${item.content.status}">
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
								<td>${item.content.afterSaleForm.result }</td>
							<td>${item.content.afterSaleForm.remark }</td>

							</tr>
						</c:forEach>
					</div>
				</tbody>
			</table>
		</div>
	</div>
	<div class="phone_main_07">
		<p id="p"></p>
		<!-- <ul id="phone_list_page_ul"> -->
		<ul data-am-widget="pagination"
			class="am-pagination am-pagination-select">
			<li class="am-pagination-prev "><c:choose>
					<c:when test="${data.metadata.number ==0}">
						<a>上一页</a>
					</c:when>
					<c:otherwise>
						<a
							href="historyList?sort=createdDate,desc&status=${status}&page=${data.metadata.number-1}"
							class="">上一页</a>
					</c:otherwise>
				</c:choose></li>
			<li class="am-pagination-select">${data.metadata.number+1} /
				${data.metadata.totalPages}</li>
			<li class="am-pagination-next "><c:choose>
					<c:when
						test="${ data.metadata.number+1 == data.metadata.totalPages}">
						<a>下一页</a>
					</c:when>

					<c:otherwise>
						<a
							href="historyList?sort=createdDate,desc&status=${status}&page=${data.metadata.number+1}"
							class="">下一页</a>
					</c:otherwise>
				</c:choose></li>
		</ul>
		<!-- </ul> -->
	</div>
	<script src="${ctx }/js/jquery.min.js"></script>
	<script src="${ctx }/js/amazeui.min.js"></script>
	<script type="text/javascript">
		$(function(){
		
			$("#mytable1 tbody tr").each(function(i, o1) {
				
				var status = $(o1).attr("data-status");
				var type = $(o1).attr("data-type");
		
				if (status == "NOPROCESS") {
					/*  $(o1).css("display", "none"); */
					if (type == "KXS") {
						$(o1).addClass("am-success");
					} else if (type == "WX") {
						$(o1).addClass("am-warning");
					} else if (type == "THH30") {
						$(o1).addClass("am-danger");
					} else if (type == "DMDHX100") {
						$(o1).addClass("am-primary");
					}
			
				} else {
					 
				}

			});

			$("#mytable1").delegate("tr", "click", function() {
			
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
		
		function goSearch() {
			var status= $("#status").val().trim();
		//	if(!(status.equals("处理中")||status.equals("同意")||status.equals("已完成")||status.equals("拒绝")){
		//		alert("请按照处理状态查询");
		//	}else{
				window.location.href = "historyList?sort=createdDate,desc&status="+status;	
		//	}
	//	if(status=="处理中"){
	//		alert{"chulizhong"};
	//	}else{
	//		alert("bupipei");
	//	}								
		}
		
		function goAll() {
			$("#imei").val("");
			window.location.href = "historyList?sort=createdDate,desc";
		}
	</script>
</body>
</html>
