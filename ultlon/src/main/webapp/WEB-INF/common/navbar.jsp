<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header class="am-topbar am-topbar-fixed-top" style="margin-bottom: ">
	<div class="am-container">
		<h1 class="am-topbar-brand">
			<a href="javascript:location.reload();">三际手机采购网</a>
		</h1>

		<button
			class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-secondary am-show-sm-only"
			data-am-collapse="{target: '#collapse-head'}">
			<span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span>
		</button>

		<div class="am-collapse am-topbar-collapse" id="collapse-head">
			<ul class="am-nav am-nav-pills am-topbar-nav">
				<li class="am-active"><a href="./">首页</a></li>
				<li><a href="task">待处理售后</a></li>
				<li><a href="#">售后历史</a></li>
				<li><a href="#">帮助中心</a></li>
			</ul>
			<div class="am-topbar-right">
				<a class="am-btn am-btn-primary am-topbar-btn am-btn-sm"
					href="logout"> <span class="am-icon-user"></span> 退出
				</a>
			</div>
		</div>
	</div>
</header>