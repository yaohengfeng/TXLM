                                    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>同学录 | 主页面</title>
	<!-- 引入CSS -->
    <link rel="stylesheet" type="text/css" href="/demo/easyui/css/default.css" />
    <link rel="stylesheet" type="text/css" href="/demo/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="/demo/easyui/themes/icon.css" />
    <!-- 引入JS -->
    <script type="text/javascript" src="/demo/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/demo/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src='/demo/easyui/js/outlook2.js'> </script>
    <script type="text/javascript">
    <!-- 菜单栏 -->
	 var _menus = {"menus":[
						{"menuid":"2","icon":"","menuname":"信息展示",
							"menus":[
									{"menuid":"21","menuname":"学生列表","icon":"icon-user-student","url":"UserMangerServlet?method=toUserListView"},
								]
							
						},
						<c:if test="${userType == 2 }">
						{"menuid":"3","icon":"","menuname":"个人信息",
							"menus":[
								{"menuid":"31","menuname":"查看信息","icon":"icon-user-student","url":"UserMangerServlet?method=getUserInfo"},
								{"menuid":"32","menuname":"修改信息","icon":"icon-user-student","url":"UserMangerServlet?method=editUserInfo"},
								]			
						},
						</c:if>
						
						<%-- 通过JSTL设置用户权限:  仅管理员可以查看班级列表信息	 --%>
						
						{"menuid":"4","icon":"","menuname":"系统用户管理",
							"menus":[
							        {"menuid":"41","menuname":"修改密码","icon":"icon-set","url":"EditPasswordServlet?method=toEditPasswordView"},
								]
						}
				]};
    </script>       
</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">


    <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background:  #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head">
        	<span style="color:blue; font-weight:bold;" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-man',plain:true">
        		<c:choose>
        			<c:when test="${userType==1 }">管理员  :  </c:when>
        			<c:when test="${userType==2 }">学生  :  </c:when>
        		</c:choose>
        		
        	</span>
        	<%-- ${userInfo.name}: 从Session中获取登录用户的用户名	--%>
        	<span style="color:red; font-weight:bold;">${userInfo.name}&nbsp;</span>
        		您好哟 ~&nbsp;&nbsp;&nbsp;
        	<a href="AdminServlet?method=loginOut" id="loginOut"  href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-back',plain:true">
        		
        		[安全退出]
        	</a>
        </span>
        <span style="padding-left:10px; font-size: 18px; ">同学录系统</span>
    </div>
    <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
        
    </div>
    <div region="west" hide="true" split="true" title="[ 导航菜单  ]" style="width:180px;" id="west">
	<div id="nav" class="easyui-accordion" fit="true" border="false">
		<!--  导航菜单内容 -->
	</div>
	
    </div>
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
        	<!-- 引入欢迎页面资源 -->
			<jsp:include page="/WEB-INF/system/welcome.jsp" />
		</div>
    </div>

</body>
</html>