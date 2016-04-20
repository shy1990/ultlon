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
<title>维修申请</title>
<style>
.content {
	text-align: center;
	padding: 50px 0;
}

.remark {
	width: 260px;
}
</style>

<script src="js/jquery.min.js"></script>
<script src="js/amazeui.min.js"></script>

<style type="text/css">
.content {
	text-align: center;
	padding: 50px 0;
}

#background {
	position: absolute;
	z-index: 998;
	top: 0px;
	left: 0px;
	background: rgb(50, 50, 50);
	background: rgba(0, 0, 0, 0.5);
	display: none;
}

#content {
	position: absolute;
	width: 580px;
	z-index: 999;
	padding: 10px;
	background: #fff;
	border-radius: 5px;
	display: none;
}

.ultlon_content_body {
	text-align: center;
}

.ultlon_gather {
	margin-top: 25px;
	font-size: 22px;
}

.ultlon_content_body_btn {
	border: 1px solid #ccc;
	background: #F9F9F9;
	width: 250px;
	margin: 20px auto;
	height: 30px;
	line-height: 30px;
	cursor: pointer;
	font-size: 18px;
}

.remark {
	width: 260px;
}

.remark_left {
	width: 10%;
	text-align: right;
	float: left;
}

.remark_right {
	width: 90%;
	text-align: left;
	float: left;
}

#cl_cancel {
	font-size: 16px;
}

.clear {
	clear: both
}
/*----------- 分页 start------------- */
.phone_main_07 {
	padding-top: 20px;
	width: 500px;
	margin: 0 auto;
}

#choose {
	border: 1px solid #666666;
		height:38px;
	width:100px;
	text-align:center;

}
</style>

</head>
<body>
	<%@include file="../../../common/afterbar.jsp"%>
	<div class="am-collapse am-topbar-collapse" id="collapse-head">
		<div class="am-topbar-right" style="margin-right: 200px;">
			<div class="am-u-lg-6">
				<div class="am-input-group">
					<select id="choose" onchange="choose()">
						<option value="1">串号</option>
						<option value="2">申请人</option>
					</select><span
						class="am-input-group-btn"> <input type="text" class="am-form-field" style="width: 300px"
						id="imei" placeholder="请输入要查询的串号" value="${imei }"> 
						<button class="am-btn am-btn-default" type="button"
							onclick="goSearch();" style="margin-right: 5px;">搜索</button>
						<button class="am-btn am-btn-default" type="button"
							onclick="goAll();">查询全部</button>
					</span>
				</div>
			</div>
		</div>
	</div>
	<table
		class="am-table am-table-bordered am-table-striped am-table-hover">
		<thead>
			<tr>
				<th>申请人</th>
				<th>手机</th>
				<th>串号</th>
				<th>维修金额</th>
				<th>物流单号</th>
				<th>备注</th>
				<th>状态</th>
				<th>操作</th>
				<th>维修操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${data.content }">
				<tr class="am-active">
					<td>${item.content.taskForm.afterSaleForm.username }</td>
					<td>${item.content.taskForm.afterSaleForm.goodsName }</td>
					<td>${item.content.taskForm.afterSaleForm.imei }</td>
					<td>${item.content.cost}</td>
					<td>${item.content.track_no}</td>
					<td class="remark" title="${item.content.remark.toString()}">${item.content.remark.toString()}</td>
					<td>${item.content.status.toString() }</td>
					<td style="width: 200px;"><c:if
							test='${item.content.status eq "NOPROCESS"}'>
							<button type="button" class="am-btn am-btn-success am-radius"
								onclick="agree('${item.content.id}');">同意</button>
							<button type="button" class="am-btn am-btn-danger am-radius"
								onclick="reject('${item.content.id}');">拒绝</button>
						</c:if></td>
					<td style="width: 100px;"><c:if
							test='${item.content.status eq "PROCESSING"}'>
							<button type="button" class="am-btn am-btn-warning am-radius"
								onclick="finish('${item.content.id}');">维修完成</button>
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="am-modal am-modal-prompt" tabindex="-1" id="my-prompt-a">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">三际商城</div>
			<div class="am-modal-bd">
				请输入维修报价: <input type="text" class="am-modal-prompt-input"
					placeholder="请输入维修报价" id="cost">

				<div>
					<div class="remark_left">请填写备注:</div>
					<div class="remark_right">
						<textarea name="MSG" id="remark_a" cols=43.5 rows=6
							placeholder="请输入备注"></textarea>
					</div>
				</div>

			</div>
			<div class="am-modal-footer">
				<span class="am-modal-btn" data-am-modal-confirm>提交</span> <span
					class="am-modal-btn" data-am-modal-cancel>取消</span>
			</div>
		</div>
	</div>

	<div class="am-modal am-modal-prompt" tabindex="-1" id="my-prompt-r">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">三际商城</div>
			<div class="am-modal-bd">
				<div id="rmk">
					<div class="remark_left">请填写备注:</div>
					<div class="remark_right">
						<textarea name="MSG" id="remark_r" cols=43.5 rows=6
							placeholder="请输入备注"></textarea>
					</div>
				</div>

				<div id="tn">
					<div class="remark_left">请填写物流单号:</div>
					<div class="remark_right">
						<input type="text" id="track" class="am-form-field am-radius"
							placeholder="请输入物流单号" />
					</div>
				</div>
			</div>
			<div class="am-modal-footer">
				<span class="am-modal-btn" data-am-modal-confirm>提交</span> <span
					class="am-modal-btn" data-am-modal-cancel>取消</span>
			</div>
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
							href="admin/repair?sort=createdDate,desc&imei=${imei}&page=${data.metadata.number-1}"
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
							href="admin/repair?sort=createdDate,desc&imei=${imei}&page=${data.metadata.number+1}"
							class="">下一页</a>
					</c:otherwise>
				</c:choose></li>
		</ul>
		<!-- </ul> -->
	</div>
	<script type="text/javascript">
	$(function() {
		
		
		
		//限制字符个数
		$(".remark").each(function(){
		    var maxwidth=14;
		    if($(this).text().length>maxwidth){ 
		       $(this).text($(this).text().substring(0,maxwidth));
		        $(this).html($(this).html()+'…');
		    }
		});
		
		$("#cost").keyup(function () {
            var reg = $(this).val().match(/\d+\.?\d{0,2}/);
            var txt = '';
            if (reg != null) {
                txt = reg[0];
            }
            $(this).val(txt);
        }).change(function () {
            $(this).keyup();
        });
		
	});
	
	function agree(id) {
		$('#my-prompt-a').modal({
			relatedTarget : this,
			onConfirm : function(e) {
				var cost = e.data || 0;
				var remark=$('#remark_a').val();
				$.post("admin/repair", {
					"id":id,
					"cost" : cost,
					"remark":remark,
					"status":"PROCESSING"
				}, function(data) {
					if (data === 'ok') {
						location.href = "admin/repair?sort=createdDate,desc&page=${data.metadata.number+1}";
					}
				});

			}

		});
	}
	
	function reject(id) {
		$("#rmk").css("display", "block");
		$("#tn").css("display", "none");
		$('#my-prompt-r').modal({
			relatedTarget : this,
			onConfirm : function(e) {
				$.post("admin/repair/" + id + "/REJECT", {remark : $('#remark_r').val()}, function(data) {
					if (data === 'ok') {
						location.reload();
					}
				});

			}

		});
	}
	
	function finish(id) {
		$("#rmk").css("display", "none");
		$("#tn").css("display", "block");
		$('#my-prompt-r').modal({
			relatedTarget : this,
			onConfirm : function(e) {
				$.post("admin/repair/" + id + "/FINISH", {track_no : $('#track').val()}, function(data) {
					if (data === 'ok') {
						location.reload();
					}
				});

			}

		});
	}
	
	function choose() {
		//alert($("#choose").val());
		//$("#imei").val()=$("#choose").val();
		if ($("#choose").val() == "1") {
			$("#imei").attr('placeholder', "请输入要查询的串号");

		} else {
			$("#imei").attr('placeholder', "请输入要查询的申请人");
		}

	}
	
	function goSearch() {
		var imei = $("#imei").val().trim();
		if(($("#choose").val()=="1")){
			console.log(imei);
			if(imei==""){
				alert("串号不能为空，请输入串号");
			}else{
				window.location.href = "admin/repair?sort=createdDate,desc&imei="	+ imei;	
			}
		}else{
			if(imei==""){
				alert("申请人不能为空，请输入申请人")
			}else{
				window.location.href = "admin/repair?sort=createdDate,desc&username="	+ imei;					
			}
		}
	}
	
	function goAll() {
		$("#imei").val("");
		window.location.href = "admin/repair?sort=createdDate,desc";
	}
	
	
	/* $('#username').blur(function (){
			if ($('#username').val() ) {
			$('#q').text('用户名已存在！')
			}
			else { $('#q').text('ok!') }
			}) */
	
	/* function reject(id) {
		$.post("admin/repair/" + id + "/REJECT", function(data) {
			if (data === 'ok') {
				location.reload();
			}
		});
	} */
</script>

</body>
</html>