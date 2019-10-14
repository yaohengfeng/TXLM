<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册</title>
	<link rel="stylesheet" type="text/css" href="/demo/easyui/css/bootstrap.min.css" />
	<!-- <link rel="stylesheet" type="text/css" href="/demo/easyui/css/default.css" /> -->
	<link rel="stylesheet" type="text/css" href="/demo/easyui/themes/default/easyui.css" />
	<!-- <link rel="stylesheet" type="text/css" href="/demo/easyui/themes/icon.css" />  -->
 	 <script type="text/javascript" src="/demo/easyui/jquery-3.4.1.min.js"></script> 
 	<script type="text/javascript" src="/demo/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/demo/easyui/jquery.form.js"></script> 
		<style>
		html body {
			margin: 0;
			padding: 0;
			background-color: EaEAea;
			background-image: url(../h-ui/images/bg2.jpg);
			background-repeat: no-repeat;
			background-size:100% 100%;
			background-attachment: fixed;"
		}
		
		.center {
			width: 200px;
			height: 200px;
			position: absolute;
			left: 30%;
			top: 30%;
		}
	</style>
	<script type="text/javascript">
	$(function(){
		$("#registe").click(function(){
			/* var data1 = $("#test").serialize(); */
			$.ajax({
				type: "post",
				url: "/demo/Register?method=register",
				data: $("#test").serialize(), 
				dataType: "text", //设置返回的数据类型
				success: function(msg1){
					if("nullError"==msg1){
						$.messager.alert("消息提醒", "用户名或密码不能为空 !", "warning");
					}else if("iphoneError" == msg1){
						$.messager.alert("消息提醒", "该电话已注册 !", "warning");
						
					}else if("passwordError" == msg1){
						$.messager.alert("消息提醒", "两次密码不同 !", "warning");
						
					}else if("error"==msg1){
						$.messager.alert("消息提醒", "注册失败 !", "warning");
					}
					else if("success" == msg1){
						//进入系统主页面
						$.messager.alert("消息提醒", "注册成功 !", "info");		
					}
				}				
			});
		});
	})
	
		$(function () {
		$("#back").click(function(){
			window.location.href="/demo/jsps/User.jsp";
		});
	})
</script> 

</head>
<body>
	<div class="center">
		<div class="container">
			<form class="form-horizontal" method="post" id="test">
				<div class="form-group">
					<label for="inputname" class="col-sm-2 control-label">用户名:</label>
					<div class="col-xs-3">
						<input type="text" id="inputname" class="form-control"
							name="username" placeholder="用户名" />
					</div>
				</div>
				<div class="form-group">
					<label for="inputname" class="col-sm-2 control-label">电&nbsp;&nbsp;&nbsp;话:</label>
					<div class="col-xs-3">
						<input type="tel" id="inputname" class="form-control"
							name="iphone" placeholder="电话" />
					</div>
				</div>
				<div class="form-group">
					<label for="inputpassword" class="col-sm-2 control-label">密&nbsp;&nbsp;&nbsp;码:</label>
					<div class="col-xs-3">
						<input type="password" id="inputpassword" class="form-control"
							name="passWord" placeholder="密码" />
					</div>
				</div>
				<div class="form-group">
					<label for="inputpassword" class="col-sm-2 control-label">密&nbsp;&nbsp;&nbsp;码:</label>
					<div class="col-xs-3">
						<input type="password" id="inputpassword" class="form-control"
							name="passWord1" placeholder="再次输入密码" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button" id="registe" class="btn btn-default"
							style="width: 130px">注&nbsp;&nbsp;&nbsp;&nbsp;册</button>
						<button type="button" id="back" class="btn btn-default"
							style="width: 130px;">返&nbsp;&nbsp;&nbsp;&nbsp;回</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>