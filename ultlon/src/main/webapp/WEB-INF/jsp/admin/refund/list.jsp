<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
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
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/amazeui.min.js"></script>

<title>退货退款申请</title>
<script type="text/javascript">
		/* function agree(id) {
			$.post("admin/refund/" + id + "/AGREE", function(data) {
				if (data === 'ok') {
					location.reload();
				}
			});
		} */
		
		/* function reject(id) {
			$.post("admin/refund/" + id + "/REJECT", function(data) {
				if (data === 'ok') {
					location.reload();
				}
			});
		} */
		
		$(function() {
			
			$("#id").val("");
			$("#sa").val("");
			$("#sr").val("");
			$("#remark").val("");
		    function conPosition() {
		        var oBackground = document.getElementById("background");
		        var dw = $(document).width();
		        var dh = $(document).height();
		        oBackground.style.width = dw+'px';
		        oBackground.style.height = dh+'px';
		        var oContent = document.getElementById("content");
		        var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
		        var l = (document.documentElement.clientWidth - oContent.offsetWidth) / 2;
		        var t = ((document.documentElement.clientHeight - oContent.offsetHeight) / 4) + scrollTop;
		        oContent.style.left = l + 'px';
		        oContent.style.top = t + 'px';
		    }
		    
		    $("#agree").click(function() {
				 var id=$(this).attr('testvalue');
				 var status=$("#agree").text();
				 $("#background, #content").show();
					
				 conPosition();

				 $("#common_id").val(id);
				 $("#sa").val(status);
				});    
		    
			$("#reject").click(function() {
				 var id=$(this).attr('testvalue');
				 var status=$("#reject").text();
				 alert(status);
					$("#background, #content").show();
					
					conPosition();
	
					$("#common_id").val(id);
					$("#sr").val(status);
				});

		$("#background,#cl_cancel").click(function() {
			$("#background, #content").hide();
		});
		//点击黑色背景隐藏弹出层，当然可以绑定在任意一个按钮上
		$(window).resize(function() {
			conPosition();
		});
		//$(window).scroll(function() {conPosition();});
		//开启内容跟随垂直滚动条（水平滚动条需要处理的问题更多，暂时没有考虑）
		
		//限制字符个数
	    $(".remark").each(function(){
	        var maxwidth=14;
	        if($(this).text().length>maxwidth){ 
	           $(this).text($(this).text().substring(0,maxwidth));
	            $(this).html($(this).html()+'…');
	        }
	    });
		
	});

	function btn_ok() {
		var id = $("#common_id").val();
		var sa=$("#sa").val();
		var sr=$("#sr").val();
		if("同意"==sa && sr==""){
			$.post("admin/refund/" + id + "/AGREE", {remark : $('#remark').val()}, function(data) {
				if (data === 'ok') {
					location.reload();
				}
			});
			
		}
		if("拒绝"==sr && sa==""){
			$.post("admin/refund/" + id + "/REJECT", {remark : $('#remark').val()}, function(data) {
				if (data === 'ok') {
					location.reload();
				}
			});
			
		}
		
	}
	
</script>
<style type="text/css">
.content {
	text-align: center;
	padding: 50px 0;
}
				#background {position:absolute; z-index:998; top:0px; left:0px; background:rgb(50,50,50);background:rgba(0,0,0,0.5); display:none;}
				#content {position:absolute; width:580px; z-index:999; padding:10px; background:#fff; border-radius:5px; display:none;}
				.ultlon_content_body{text-align: center;}	 
				.ultlon_gather{margin-top:25px;font-size: 22px;}
				.ultlon_content_body_btn{border: 1px solid #ccc;
					  background: #F9F9F9;
					  width: 250px;
					  margin: 20px auto;
					  height: 30px;
					  line-height: 30px;
					  cursor: pointer;
					  font-size: 18px;
				}
				
				.remark{
					width:260px;
					
				}
				.remark_left{width: 10%; text-align: right; float: left;}
				.remark_right{width: 90%; text-align: left; float: left;}
				#cl_cancel{font-size: 16px;}
				.clear{clear:both}
				/*----------- 分页 start------------- */
.phone_main_07{ padding-top:20px; width:500px; margin:0 auto;}
</style>
</head>
<body>
<%@include file="../../../common/afterbar.jsp"%>
	<table
		class="am-table am-table-bordered am-table-striped am-table-hover">
		<thead>
			<tr>
				<th>申请人</th>
				<th>手机</th>
				<th>串号</th>
				<th>订单号</th>
				<th>签收时间</th>
				<th>退款金额</th>
				<th>类型</th>
				<th>备注</th>
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
					<td>${item.content.taskForm.afterSaleForm.orderNum }</td>
					<td>${item.content.taskForm.afterSaleForm.receiveTime }</td>
					<td>${item.content.realRefundMoney }</td>
					<td>${item.content.taskForm.afterSaleForm.type.toString() }</td>
					<td class="remark" title="${item.content.remark.toString()}">${item.content.remark.toString()}</td>
					<td>${item.content.status.toString()}</td>
					<td style="width:200px;"><c:if test='${item.content.status eq "NOPROCESS"}'>
							<button type="button" class="am-btn am-btn-success am-radius" id="agree" testvalue="${item.content.id}" >同意</button>
							<button type="button" class="am-btn am-btn-danger am-radius" id="reject" testvalue="${item.content.id}" >拒绝</button>
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 弹窗 -->
			<div id="background"></div>
			<div id="content">
			    <div class="ultlon_content_body">
				    	
					<div><div class="remark_left">备注：</div><div class="remark_right"><textarea name="MSG" id="remark" cols=50 rows=6></textarea></div></div>
				    	
				    <input type="hidden" name="type" value="" id="common_id">
				    <input type="hidden" name="type" value="" id="sa">
				    <input type="hidden" name="type" value="" id="sr">
				    
				    <button type="button" class="ultlon_gather ultlon_content_body_btn" style="background:red;" id="btn_ok" onclick="btn_ok();">确定</button>
				    <button class="ultlon_gather ultlon_content_body_btn" id="cl_cancel">取消</button>
			    </div>
			</div>
	<div class="phone_main_07">
		
			<p id="p"></p>
			<!-- <ul id="phone_list_page_ul"> -->
			     <ul data-am-widget="pagination" class="am-pagination am-pagination-select">
				  <li class="am-pagination-prev ">
				  <c:choose>
				  	<c:when test="${data.metadata.number ==0}">
				  		<a>上一页</a>
				  	</c:when>
					<c:otherwise>
					  <a href="admin/refund?sort=createdDate,desc&page=${data.metadata.number-1}"class="">上一页</a>
					</c:otherwise>
				   </c:choose>
				  </li>
				  <li class="am-pagination-select">
				    ${data.metadata.number+1} / ${data.metadata.totalPages}
				  </li>
				  <li class="am-pagination-next ">
				  <c:choose>
				  	<c:when test="${ data.metadata.number+1 == data.metadata.totalPages}">
				  		<a>下一页</a>
				  	</c:when>
				  
					<c:otherwise>
					  <a href="admin/refund?sort=createdDate,desc&page=${data.metadata.number+1}" class="">下一页</a>
					</c:otherwise>
				    </c:choose>
				  </li>
				</ul>
			<!-- </ul> -->
		</div>
	
</body>
</html>