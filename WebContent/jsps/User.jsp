<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<link rel="shortcut icon" href="favicon.ico"/>
<link rel="bookmark" href="favicon.ico"/> 
 <link href="/demo/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="/demo/h-ui/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="/demo/h-ui/lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="/demo/h-ui/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" /> 
	<link rel="stylesheet" type="text/css" href="/demo/easyui/css/default.css" />
	 <link rel="stylesheet" type="text/css" href="/demo/easyui/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="/demo/easyui/themes/icon.css" />
 	<script type="text/javascript" src="/demo/easyui/jquery-3.4.1.min.js"></script>
	 <script type="text/javascript" src="/demo/h-ui/js/H-ui.js"></script>
	<script type="text/javascript" src="/demo/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/demo/easyui/jquery.form.js"></script> 
	 <script type="text/javascript" src="/demo/h-ui/lib/icheck/jquery.icheck.min.js"></script>
	
	<script type="text/javascript">
	$(function(){
		//登录操作
		$("#submitBtn").click(function(){
			var data = $("#form").serialize();
			$.ajax({
				type: "post",
				//验证登录
				url: "demo/AdminServlet?method=login",
				data: data, 
				dataType: "text", //设置返回的数据类型
				success: function(msg){
					if("nullError"==msg){
						$.messager.alert("消息提醒", "该用户还未注册 !", "warning");
					}else if("spaceError"==msg){
						$.messager.alert("消息提醒", "用户名或密码不能为空 !", "warning");
					}else if("loginError" == msg){
						$.messager.alert("消息提醒", "用户名或密码错误 !", "warning");
						
					} else if("loginSuccess" == msg){
						//进入系统主页面
						window.location.href = "/demo/SysMainInterfaceServlet?method=toMainView";
					}
				}				
			});
		});
		//设置复选框
		$(".skin-minimal input").iCheck({
			radioClass: 'iradio-blue',
			increaseArea: '25%'
		});
	})

	$(function () {
		$("#registerBtn").click(function(){
			window.location.href="/demo/jsps/Register.jsp";
		});
	})
	
</script> 

<script type="text/javascript">

</script>

<title>登录页面 | 同学录管理系统</title>
<meta name="keywords" content="同学录管理系统">
</head>



<body style="font-weight: lighter; ">
<div class="header" style="padding: 0;">
	<h3 style="font-weight: lighter; color: black; width: 500px; height: 60px; line-height: 60px; margin: 0 0 0 30px; padding: 0;">
		School Yearbook
	</h3>
</div>
<div class="loginWraper" >
  <div id="loginform" class="loginBox" >
    <form id="form" class="form form-horizontal" method="post">
      <!-- 输入框 -->
      <div class="row cl">
         <label class="form-label col-3"><i class="Hui-iconfont">&#xe60d;</i></label> 
        <div class="formControls col-8">
          <!-- <input id="" name="userName" type="text" placeholder="账户" class="input-text size-L"> -->
          <input id="" name="iphone" type="tel" placeholder="电话号码" class="input-text size-L" maxlength="11">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60e;</i></label> 
        <div class="formControls col-8">
          <input id="" name="userPassword" type="password" placeholder="密码" class="input-text size-L">
        </div>
      </div> 
 
      <!-- 用户类型 -->
      <div class="mt-20 skin-minimal" style="text-align: center;">
		<div class="radio-box">
			<input type="radio" id="radio-1" name="userType" value="1" />
			<label for="radio-2">管理员</label>
		</div>
		<div class="radio-box">
			<input type="radio" id="radio-2" name="userType" checked value="2" />
			<label for="radio-1">学生</label>
		</div>
	</div>
      <!-- 登录按钮 -->
      <div class="row" style="text-align: center;" >
        <div class="formControls col-8 col-offset-3"  >
          <input id="submitBtn" type="button" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;录&nbsp;">
           <input id="registerBtn" type="button" class="btn btn-success radius size-L" value="&nbsp;注&nbsp;&nbsp;册&nbsp;" style="margin-left:70px;margin-right:30px"> 
        </div>
      </div>	
    </form>
    
<!--     <form action="/demo/jsps/Register.jsp">
    	<div class="row" >
       <div class="formControls col-8 col-offset-3">
          <input  type="submit" class="btn btn-success radius size-L" value="&nbsp;注&nbsp;&nbsp;&nbsp;&nbsp;册&nbsp;">
        </div>
      </div>
    </form> -->
    
  </div>
</div>
		
</body>
</html>