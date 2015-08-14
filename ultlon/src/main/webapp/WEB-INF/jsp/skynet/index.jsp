<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>用户浏览记录</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
	th,tb,nav{text-align: center;}
	
</style>

<script src="//cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>
<link href="//cdn.bootcss.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
<script src="//cdn.bootcss.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="${ctx}/js/bootstrap-datetimepicker.min.js"></script>
<script src="${ctx}/js/bootstrap-datetimepicker.fr.js"></script>
<link href="${ctx}/css/bootstrap-datetimepicker.min.css" rel="stylesheet">



</head>

<body>

	<nav class="navbar navbar-default" role="navigation">
	   <div class="navbar-header">
	      <div class="navbar-brand" >用户浏览记录</div>
	   </div>
	   <div>
	      <form class="navbar-form navbar-left" method="get" action="${ctx}/ugrc/1" role="search">
	         <div class="form-group">
	            <input type="text" name="userName" value="${param.userName}" class="form-control" placeholder="用户名">
	            <input type="text" name="goodsName" value="${param.goodsName}" class="form-control" placeholder="商品名称">
	            <input type="text" class="form_datetime form-control" readonly name="startTime" value="${param.startTime}"  placeholder="开始时间">
	            <input type="text" class="form_datetime form-control" readonly name="endTime" value="${param.endTime}"  placeholder="结束时间">
	         </div>
	         <button type="submit" id="search_btn" class="btn btn-default">搜索</button>
	      </form>  
	   </div>
	</nav>

    <div class="container-fluid">
	   <div class="row in-out-row">
	      <div class="col-md-12 col-lg-12">
			<table class="table table-striped table-condensed">
		      <thead class="table-head">
		        <tr>
		          <th>用户名</th>
		          <th>商品名称</th>
		          <th>访问时间</th>
		        </tr>
		      </thead>
		      <tbody >
			      <c:forEach var="ug" items="${list}">
			      	<tr class="alert alert-info">
			      		<th scope="row">${ug.USERNAME}</th>
			      		<th scope="row">${ug.NAME}</th>
			      		<th scope="row">${ug.CREATE_TIME}</th>
			      	</tr>
			      </c:forEach>
		      </tbody>
		    </table>
		  </div><!-- in-col End -->
	   </div>
	</div>
	
	<nav>
	  <ul class="pager">
	    <c:if test="${page>1}">
	    	<li class="previous"><a href="${ctx}/ugrc/${page-1}?userName=${param.userName}&goodsName=${param.goodsName}&startTime=${param.startTime}&endTime=${param.endTime}"><span aria-hidden="true">&larr;</span> 上一页</a></li>
	    </c:if>
	    ${page } / ${countPage}
	    <c:if test="${page<countPage}">
	    	<li class="next"><a href="${ctx}/ugrc/${page+1}?userName=${param.userName}&goodsName=${param.goodsName}&startTime=${param.startTime}&endTime=${param.endTime}">下一页 <span aria-hidden="true">&rarr;</span></a></li>
	    </c:if>
	  </ul>
	</nav>
	
	<script type="text/javascript">
	    $(".form_datetime").datetimepicker({format: 'yyyy-mm-dd hh:ii'});
	</script>  
	
</body>
</html>


