<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet" href="css/amazeui.min.css" />
<style>
.header {
	text-align: center;
}

.header h1 {
	font-size: 200%;
	color: #333;
	margin-top: 30px;
}

.header p {
	font-size: 14px;
}
</style>
<script  src="js/jquery.min.js"></script>
<script  src="js/amazeui.min.js"></script>
<title>售后</title>
<script type="text/javascript">
 var regionsData = '';
 var provinceData;
 var cityData;
 var areaData;
 $(function() {
	 var pid = $('#province').val();
	 var region = $('#province');
	 if(pid = 'null'){
		  pid = '';
	  }
	getRegion(pid,region);
	   
	}); 
  function getRegion(pid,region){
	   $.ajax({
		 type:"post",
			url:"user/getRegions?pid="+pid,
			//data:pid = null,
			dataType:"JSON",
			success : function(data){
			    var regions = region; 
			    regions.empty(); 
                for(var i=0;i<data.length;i++){
            	   regions.append("<option value = '"+data[i].id+"'>"+data[i].name+"</option>");    
				}  
		 }
		});   
  }
	   
	  function getCity(){
		    var value = $('#province').val();
		    var region = $('#city');
		   
			  if(value != null && value != ''){
				  getRegion(value,region);
				  provinceData = '';
				  provinceData +=value+','; 
			   } 
			   

	  }
	  
	  
	  function getArea(){
		  var value = $('#city').val();
		  var region = $('#area');
		  if(value != null && value != ''){
			  getRegion(value,region);
			  cityData = '';
			  cityData +=value+',';
		   }
	  
	  }
	  
	  function setAreaData(){
		  var value = $('#area').val();
		 
		  if(value != null && value != ''){
			  areaData = '';
			  areaData +=value;
		  }
		 // alert(regionsData);
	  }
	  
	  function setRegionsData(){
		  regionsData = provinceData+cityData+areaData; 
		  $("#regions").val(regionsData);
	  }
      
</script>
 
</head>
<body>
    <div class="header">
		<div class="am-g">
			<h1>三际手机采购网</h1>
			<p>用户添加</p>
		</div>
		<hr />
	</div>
	<div class="am-g">
	   <div  class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
		<form action="user/register" class="am-form" method="post">
		        <input type="hidden" id="regions" name="regions" />
		        <label for="username">用户名:</label> <input type="text" name="username"
					id="username" value=""> <br> <label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
				<input type="password" name="password" id="password" value=""><br>
				 <label for="mobile">手机号:</label>
				<input type="tel" name="mobile" id="password" value="">
				<br/> 
				    <label for="roler">职&nbsp;&nbsp;&nbsp;&nbsp;位:</label>
					<select name="roler"  onchange="setRegionsData()">
					  <option >点击选择</option>
					  <option value="app">业务经理</option>
					  <option value="admin">售后</option>
					  <option value="admin">财务</option>
					  <option value="meuser">管理員</option>
		           </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
				    <!-- 获取省级数据 -->
				    <label for="province">省:&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<select id="province" name="province" multiple data-am-selected onchange="getCity()" >
					</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<!-- 获取市级数据 -->
					<label for="city">市:&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<select id="city" name="city" multiple data-am-selected onchange="getArea()" >
					</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<!-- 获取区县级数据 -->
					<label for="area">区:&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<select id="area" name="area" multiple data-am-selected onchange="setAreaData()" >
					</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
					<br/><br/>
				
				<div class="am-cf">
					<input type="submit" name="submit" value="保  存"
						class="am-btn am-btn-primary am-btn-sm am-fl">
				</div>
	    </form>
	  </div>
	</div>
</body>
</html>