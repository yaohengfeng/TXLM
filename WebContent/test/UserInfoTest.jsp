<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet"
	href="/demo/easyui/css/inputInfo.css" />
</head>
<body>
	<div class="security-right">
		<div class="security-right-title">
			<span class="security-right-title-icon"></span> <span
				class="security-right-title-test">个人信息</span>
		</div>
		<div class="user-setting-warp">
			<div>
				<form class="el-form clearfix">
					<div class="el-form-item user-nick-name">
						<label class="el-form-item__label">姓名:</label>
						<div class="el-form-item__content">
							<div class="el-input">
								<input autocomplete="off" value="${user.name }" type="test"
									rows="2" maxlength="16" validateevent="true"
									class="el-input__inner" readonly="readonly">
							</div>
						</div>
						<label class="el-form-item__label">班级:</label>
						<div class="el-form-item__content">
							<div class="el-input">
								<input autocomplete="off" value="${user.classId }"
									type="number" rows="1" maxlength="6" validateevent="true"
									class="el-input__inner" readonly="readonly">
							</div>
						</div>
					</div>

					<div class="el-form-item user-nick-name1">
						<label class="el-form-item__label">性别:</label>
						<div class="el-form-item__content">
							<div class="el-input">
								<input autocomplete="off" value="${user.sex }" type="test"
									rows="1" maxlength="1" validateevent="true"
									class="el-input__inner" readonly="readonly">
							</div>
						</div>
						<label class="el-form-item__label">星座:</label>
						<div class="el-form-item__content">
							<div class="el-input">
								<input autocomplete="off" value="${user.xinzuo }"
									type="test" rows="1" maxlength="3" validateevent="true"
									class="el-input__inner" readonly="readonly">
							</div>
						</div>
					</div>

					<div class="el-form-item user-nick-name2">
						<label class="el-form-item__label">生日:</label>
						<div class="el-form-item__content">
							<div class="el-input">
								<input autocomplete="off" value="${user.birthday }"
									type="number" rows="2" maxlength="8" validateevent="true"
									class="el-input__inner" readonly="readonly">
							</div>
						</div>
						<label class="el-form-item__label">电话:</label>
						<div class="el-form-item__content">
							<div class="el-input">
								<input autocomplete="off" value="${user.iphone }" type="tel"
									rows="1" maxlength="11" validateevent="true"
									class="el-input__inner" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="el-form-item user-nick-name3">
						<label class="el-form-item__label">留言:</label>
						<div class="el-form-item__content">
							<div class="el-input">
								<textarea rows="5" cols="80" class="el-input__inner"
									maxlength="20" readonly="readonly">${user.liuyan }</textarea>
							</div>
						</div>

					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>