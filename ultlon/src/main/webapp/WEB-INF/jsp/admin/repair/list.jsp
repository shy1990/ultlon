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
					<td style="width:200px;"><c:if test='${item.content.status eq "NOPROCESS"}'>
							<button type="button" class="am-btn am-btn-success am-radius" onclick="agree('${item.content.id}');">同意</button>
							<button type="button" class="am-btn am-btn-danger am-radius" onclick="reject('${item.content.id}');">拒绝</button>
						</c:if></td>
					<td style="width:100px;"><c:if test='${item.content.status eq "PROCESSING"}'>
							<button type="button" class="am-btn am-btn-warning am-radius" onclick="finish('${item.content.id}');">维修完成</button>
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="am-modal am-modal-prompt" tabindex="-1" id="my-prompt-a">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">三际商城</div>
			<div class="am-modal-bd">
				请输入维修报价: <input type="text" class="am-modal-prompt-input" placeholder="请输入维修报价" id="cost" >
				
				<div><div class="remark_left">请填写备注:</div><div class="remark_right"><textarea name="MSG" id="remark_a" cols=43.5 rows=6 placeholder="请输入备注"></textarea></div></div>
				    	
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
				<div id="rmk"><div class="remark_left">请填写备注:</div><div class="remark_right"><textarea name="MSG" id="remark_r" cols=43.5 rows=6 placeholder="请输入备注"></textarea></div></div>
				
				<div id="tn"><div class="remark_left">请填写物流单号:</div><div class="remark_right"><input type="text" id="track" class="am-form-field am-radius" placeholder="请输入物流单号" /></div></div>
			</div>
			<div class="am-modal-footer">
				<span class="am-modal-btn" data-am-modal-confirm>提交</span> <span
					class="am-modal-btn" data-am-modal-cancel>取消</span>
			</div>
		</div>
	</div>

</body>
</html>