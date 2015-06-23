<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>修改换新单</title>
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
</style>
</head>
<body>
	<%@include file="../../common/navbar.jsp"%>
	<div class="content">
		<div class="am-g">

			<form action="change" method="post">
			<input type="hidden" name="taskId" value="${taskForm.id }">
				<div class="am-input-group">
					<span class="am-input-group-label">订单号:</span> <input type="text"
						class="am-form-field"
						placeholder="${taskForm.afterSaleForm.orderNum}" disabled="disabled">
				</div>
				<div class="am-input-group">
					<span class="am-input-group-label">手机名称:</span> <input type="text"
						class="am-form-field"
						placeholder="${taskForm.afterSaleForm.goodsName }" disabled="disabled">
				</div>
				<div class="am-input-group">
					<span class="am-input-group-label">串&nbsp;&nbsp;&nbsp;&nbsp;号:</span>
					<input type="text" class="am-form-field"
						placeholder="${taskForm.afterSaleForm.imei}" disabled="disabled">
				</div>
				<div class="am-input-group">
					<span class="am-input-group-label">客&nbsp;&nbsp;&nbsp;&nbsp;户:</span>
					<input type="text" class="am-form-field"
						placeholder="${taskForm.afterSaleForm.username}">
				</div>
				<div class="am-input-group am-input-group-primary">
					<span class="am-input-group-label">备注</span> 
						<textarea rows="6" cols="118" placeholder="请输入换机备注" id="wen" name="remark"></textarea>
				</div>
		

				<input class="am-btn am-btn-danger a1"
					type="submit" value="换货确认"></input>
				
			</form>
			<a class="am-btn  a1" href="javascript:history.back(1);">返回</a>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/amazeui.min.js"></script>
	<footer data-am-widget="footer" class="am-footer am-footer-default"
		data-am-footer="{  }">
		<div class="am-footer-switch">
			<span class="am-footer-ysp" data-rel="mobile"
				data-am-modal="{target: '#am-switch-mode'}">云适配版</span> <span
				class="am-footer-divider">|</span> <a id="godesktop"
				data-rel="desktop" class="am-footer-desktop" href="javascript:">电脑版</a>
		</div>
		<div class="am-footer-miscs ">
			<p>
				由 <a href="http://www.yunshipei.com/" title="三际商城" target="_blank"
					class="">三际商城</a>提供技术支持
			</p>
			<p>CopyRight©2014 AllMobilize Inc.</p>
			<p>京ICP备13033158</p>
		</div>
	</footer>
	<div id="am-footer-modal"
		class="am-modal am-modal-no-btn am-switch-mode-m am-switch-mode-m-default">
		<div class="am-modal-dialog">
			<div class="am-modal-hd am-modal-footer-hd">
				<a href="javascript:void(0)" data-dismiss="modal"
					class="am-close am-close-spin " data-am-modal-close>&times;</a>
			</div>
			<div class="am-modal-bd">
				您正在浏览的是 <span class="am-switch-mode-owner">云适配</span> <span
					class="am-switch-mode-slogan">为您当前手机订制的移动网站。</span>
			</div>
		</div>
	</div>
</body>
</html>