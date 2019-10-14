<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link type="text/css" rel="stylesheet"href="/demo/easyui/css/inputInfo.css" />
	<link rel="stylesheet" type="text/css" href="/demo/easyui/themes/default/easyui.css" />
 	 <script type="text/javascript" src="/demo/easyui/jquery-3.4.1.min.js"></script> 
 	<script type="text/javascript" src="/demo/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/demo/easyui/jquery.form.js"></script> 
	<script type="text/javascript">
	$(function(){
		$("#submitBtn").click(function(){
			$.ajax({
				type: "post",
				url: "/demo/EditUserInfo?method=editUser",
				data: $("#edit").serialize(), 
				dataType: "text", //设置返回的数据类型
				success: function(msg1){
					if("editError"==msg1){
						$.messager.alert("消息提醒", "修改失败 !", "warning");
						
					}else if("editSuccess" == msg1){
						
						$.messager.alert("消息提醒", "修改成功 !", "info");	
					}
				}				
			});
		});
	})
	
	
	
</script> 
</head>
<body>
			<div class="security-right">
		<div class="security-right-title">
			<span class="security-right-title-icon"></span> <span
				class="security-right-title-test">个人信息</span>
		</div>
		<div class="user-setting-warp">
			<div>
				<form class="el-form clearfix" id="edit" method="post">
					<div class="el-form-item user-nick-name">
						<label class="el-form-item__label">姓名:</label>
						<div class="el-form-item__content">
							<div class="el-input">
								<input autocomplete="off" placeholder="${userInfo.name }" type="test"
								name="name"	rows="2" maxlength="16" validateevent="true"
									class="el-input__inner" >
							</div>
						</div>
						<label class="el-form-item__label">班级:</label>
						<div class="el-form-item__content">
							<div class="el-input">
								<input autocomplete="off" placeholder="${userInfo.classId }"
								name="classId"	type="number" rows="1" maxlength="6" validateevent="true"
									class="el-input__inner" >
							</div>
						</div>
					</div>

					<div class="el-form-item user-nick-name1">
						<label class="el-form-item__label">性别:</label>
						<div class="el-form-item__content">
							<div class="el-input">
								<input autocomplete="off" placeholder="${userInfo.sex }" type="test"
							name="sex"	rows="1" maxlength="1" validateevent="true"
									class="el-input__inner" >
							</div>
						</div>
						<label class="el-form-item__label">星座:</label>
						<div class="el-form-item__content">
							<div class="el-input">
								<input autocomplete="off" placeholder="${userInfo.xinzuo }"
								name="xinzuo"	type="test" rows="1" maxlength="3" validateevent="true"
									class="el-input__inner" >
							</div>
						</div>
					</div>

					<div class="el-form-item user-nick-name2">
						<label class="el-form-item__label">生日:</label>
						<div class="el-form-item__content">
							<div class="el-input">
								<input autocomplete="off" placeholder="${userInfo.birthday }"
								name="birthday"	type="number" rows="2" maxlength="8" validateevent="true"
									class="el-input__inner" >
							</div>
						</div>
						<label class="el-form-item__label">电话:</label>
						<div class="el-form-item__content">
							<div class="el-input">
								<input autocomplete="off" value="${userInfo.iphone }" type="tel"
								name="iphone"	rows="1" maxlength="11" validateevent="true"
									class="el-input__inner"  readonly="readonly">
							</div>
						</div>
					</div>
					<div class="el-form-item user-nick-name3">
						<label class="el-form-item__label">留言:</label>
						<div class="el-form-item__content">
							<div class="el-input">
								<textarea rows="5" cols="80" class="el-input__inner"
								name="liuyan"	maxlength="20" placeholder="${userInfo.liuyan }"></textarea>
							</div>
						</div>

					</div>
					<div class="el-form-item user-nick-name4">
						<div class="el-form-item__content">
							<div class="el-input1">
								<input type="button" id="submitBtn" class="el-input__inner1" 	value="&nbsp;修&nbsp;&nbsp;&nbsp;&nbsp;改&nbsp;">
							</div>
						</div>
					</div>					
				</form>
			</div>
		</div>
	</div>
</body>
</html>