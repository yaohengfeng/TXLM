<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="/demo/easyui/css/default.css" />
	<link rel="stylesheet" type="text/css" href="/demo/easyui/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="/demo/easyui/themes/icon.css" />
	<script type="text/javascript" src="/demo/easyui/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="/demo/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/demo/easyui/jquery.form.js"></script>
	
 	<!-- <script type="text/javascript">	
		 $(function () {
			$("#submitBtn").click(function() {
				var data=$("#from").serialize();
				$.ajax({
					type: "post",
					url:"/demo/src/com/yhf/web/servlet/SysMainServletTest?method=getUserList",
					data:data,
					dataType:"test",
					success:function(msg){
						if("error"==msg){	
							$.messager.alert("消息提醒","没有查到","warning")
							window.location.href
						}
						else if("success"==msg){
							window.location.href = "/demo/SysMainServletTest?method=toMainView";
						}
					}
				});
			});
		})
	
	</script>  -->
</head>
<body>
<!--  	  <form action="/demo/UserServletTest" method="post">
	 	<input type="hidden" name="method" value="getUserById"> 
		使用id查询<input type="text" placeholder="id" value="" name="id"/><br/>
		<input type="submit" value="查找" />
	</form>  -->
	
	 
	<form id="form" method="post" action="/demo/UserServletTest" >
		  <input type="hidden" name="method" value="getUserList">  
		姓名:<input type="text" name="name" placeholder="名字"  ><br>
		班号:<input type="number" name="classId" placeholder="201609" ><br>
		电话:<input type="tel" name="tel" placeholder="号码">
		 <input type="submit"id="submitBtn" value="&nbsp;查&nbsp;&nbsp;&nbsp;&nbsp;找&nbsp;"> 
		<!--<input type="button"id="submitBtn" value="&nbsp;查&nbsp;&nbsp;&nbsp;&nbsp;找&nbsp;">-->
	</form>
<!--  	<form action="/demo/UserServletTest" method="post"> 
		使用id查询<input type="text" placeholder="id" value="" name="id"/><br/>
		<input type="submit" value="提交" />
	</form> -->
</body>
</html>