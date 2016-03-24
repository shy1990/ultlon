<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<header class="am-topbar am-topbar-fixed-top" style="margin-bottom: ">
	<div class="am-container">
		<h1 class="am-topbar-brand">
			<a href="${ctx }">三际手机采购网</a>
		</h1>

		<button
			class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-secondary am-show-sm-only"
			data-am-collapse="{target: '#collapse-head'}">
			<span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span>
		</button>

		<div class="am-collapse am-topbar-collapse am-topbar-left" id="collapse-head">
			<ul class="am-nav am-nav-pills am-topbar-nav">
				<!-- <li class="am-active"><a href="./">首页</a></li> -->
				<li><a href="${ctx }/task?sort=createdDate,desc">待处理售后</a></li>
				<li><a href="${ctx }/task/historyList?sort=createdDate,desc">售后历史</a></li>
				<li><a href="${ctx }/task/help">帮助中心</a></li>
			</ul>
		</div>
		<div class="am-topbar-right">
				<a class="am-btn am-btn-primary am-topbar-btn am-btn-sm"
					href="${ctx }/logout"> <span class="am-icon-user"></span> 点此退出系统
				</a>
		</div>
	</div>
</header>