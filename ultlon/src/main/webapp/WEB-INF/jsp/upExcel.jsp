<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8"/>
<title>上传excel文件</title>
<link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- jQuery -->
    <script src="//cdn.bootcss.com/jquery/1.9.0/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script type="text/javascript">
		$(function(){
			$("#upload-submit").click(function(){
				$("#upBody").hide();
				$("#loding").show();
			});
		});
	</script>
	<style type="text/css">
		.container{margin-top:10%;}
	</style>
</head>

<body>
	<div class="container">
		<div class="row" id="upBody">
			<form action="${ctx}/upload" method="post" enctype="multipart/form-data">
				<div class="col-md-4">
					<select class="form-control" name="excelType">
					    <option value="1">出库excel</option>
					    <option value="2">商品信息excel</option>
					</select>
				</div>
				<div class="col-md-4">
					<input type="file" name="file" id="exampleInputFile">
				</div>
				<div class="col-md-4">
					<input type="submit" id="upload-submit" class="btn btn-default" value="上传" />
				</div>
			</form>
		</div>
		<div class="row" style="text-algin:center;display:none;" id="loding"> 
       		<img alt="玩命处理中" src="static/img/loding2.gif"> 玩 命 处 理 中 · · ·文件有点大不要捉急
       	</div>
	</div>
</body>
</html>

