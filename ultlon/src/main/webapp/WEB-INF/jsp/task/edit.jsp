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
<base href="<%=basePath%>" />
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

.am-input-group {
	width: 100%;
}

.am-input-group-label {
	text-align: right;
	width: 40%;
}

.am-form-field {
	width: 60%
}

.a1 {
	width: 100%;
	height: 100px;
	padding-top: 29px;
	font-size: 24px;
}

.checkbox1 {
	width: 15px;
	height: 15px;
	left: 0;
	top: 0;
}
}
</style>
</head>
<body>
	<%@include file="../../common/navbar.jsp"%>
	<div class="content">
	<h1>退换货订单</h1>
		<div class="am-g">
			<div class="am-input-group">
				<span class="am-input-group-label">订单号:</span> <input type="text"
					class="am-form-field"
					placeholder="${taskForm.afterSaleForm.orderNum}">
			</div>
			<div class="am-input-group">
				<span class="am-input-group-label">串&nbsp;&nbsp;&nbsp;&nbsp;号:</span>
				<input type="text" class="am-form-field"
					placeholder="${taskForm.afterSaleForm.imei}">
			</div>
			<div class="am-input-group">
				<span class="am-input-group-label">客&nbsp;&nbsp;&nbsp;&nbsp;户:</span>
				<input type="text" class="am-form-field"
					placeholder="${taskForm.afterSaleForm.username}">
			</div>
			<div class="am-panel-group" id="accordion">
				<div class="am-panel am-panel-default">
					<div class="am-panel-hd">
						<h4 class="am-panel-title"
							data-am-collapse="{parent: '#accordion', target: '#do-not-say-1'}">
							包装检测</h4>
					</div>
					<div id="do-not-say-1" class="am-panel-collapse am-collapse">
						<div class="am-panel-bd">
							<label>盒子外观是否有破损</label> <input name="checkbox1"
								class="checkbox1" type="checkbox">
						</div>
						<div class="am-panel-bd">
							<label>是否有说明书</label> <input name="checkbox1" class="checkbox1"
								type="checkbox">
						</div>
						<div class="am-panel-bd">
							<label>是否有充电器</label> <input name="checkbox1" class="checkbox1"
								type="checkbox">
						</div>
						<div class="am-panel-bd">
							<label>是否有耳机，耳机是否损坏</label> <input name="checkbox1"
								class="checkbox1" type="checkbox">
						</div>
						<div class="am-panel-bd">
							<label>盒子外观是否有破损</label> <input name="checkbox1"
								class="checkbox1" type="checkbox">
						</div>
					</div>
				</div>
				<div class="am-panel am-panel-default">
					<div class="am-panel-hd">
						<h4 class="am-panel-title"
							data-am-collapse="{parent: '#accordion', target: '#do-not-say-2'}">
							手机检测</h4>
					</div>
					<div id="do-not-say-2" class="am-panel-collapse am-collapse">
						<div class="am-panel-bd">
							<label>外观检测（屏幕是否有划伤，边框是否有磕碰）</label> <input name="checkbox1"
								class="checkbox1" type="checkbox">
						</div>
						<div class="am-panel-bd">
							<label>电池仓检测（划痕、易碎标及封机标是否完整、防水标是否变红）</label> <input
								name="checkbox1" class="checkbox1" type="checkbox">
						</div>
						<div class="am-panel-bd">
							<label>盒子外观是否有破损</label> <input name="checkbox1"
								class="checkbox1" type="checkbox">
						</div>
						<div class="am-panel-bd">
							<label>盒子外观是否有破损</label> <input name="checkbox1"
								class="checkbox1" type="checkbox">
						</div>
						<div class="am-panel-bd">
							<label>盒子外观是否有破损</label> <input name="checkbox1"
								class="checkbox1" type="checkbox">
						</div>
					</div>
				</div>

			</div>



		</div>





		<a class=" am-disabled am-btn am-btn-danger a1 a"
			href="refund/edit?taskId=${taskForm.id }">我要退货</a> <a
			class=" am-disabled am-btn am-btn-success a1 a"
			href="change/edit?taskId=${taskForm.id }">我要换货</a>
		<%-- <a
			class="am-btn am-btn-danger a1"
			href="javascript:reject('${taskForm.id }');">拒绝受理</a> --%>
		<button class="am-btn am-btn-danger a1" id="doc-prompt-toggle">拒绝</button>

		<div class="am-modal am-modal-prompt" tabindex="-1" id="my-prompt">
			<div class="am-modal-dialog">
				<div class="am-modal-hd">三际商城</div>
				<div class="am-modal-bd">
					请输入拒绝原因 <input type="text" class="am-modal-prompt-input">
				</div>
				<div class="am-modal-footer">
					<span class="am-modal-btn" data-am-modal-confirm>提交</span>
					<span class="am-modal-btn" data-am-modal-cancel>取消</span> 
				
				</div>
			</div>
		</div>
	</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/amazeui.min.js"></script>
	<script type="text/javascript">
		$(function() {
			var allck = $("#accordion :checkbox").change(function() {
				var cked = $("#accordion :checked");

				if (cked.length < allck.length) {
					$(".a").addClass("am-disabled");
				} else {
					$(".a").removeClass("am-disabled");
				}
			});
			$('#doc-prompt-toggle').on('click', function() {
				$('#my-prompt').modal({
					relatedTarget : this,
					onConfirm : function(e) {
						$.post("task/${taskForm.id}/reject", {
							"remark" : e.data
						}, function(data) {
							if (data === 'ok') {
								location.href = "task?sort=createdDate,desc";
							}
						});

					}
					
				});
			});
		})
		function reject(id) {

			var remark = prompt("备注：", "请输入拒绝原因")
			if (remark) {
				$.post("task/" + id + "/reject", {
					"remark" : remark
				}, function(data) {
					if (data === 'ok') {
						location.href = "task?sort=createdDate,desc";
					}
				});

			}

		}
	</script>
</body>
</html>

