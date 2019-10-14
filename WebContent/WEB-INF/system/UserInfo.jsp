<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户业</title>
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="easyui/css/demo.css">
<!-- 引入JS -->
<script type="text/javascript" src="/demo/easyui/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/js/validateExtends.js"></script>
<script type="text/javascript">
	$(function() {	
	$('#dataList').datagrid({ 
	        title:'用户列表', 
	        iconCls:'icon-more',//图标 
	        border: true, 
	        collapsible:false,//是否可折叠 
	        fit: true,//自动大小 
	        method: "post",
	        url:"UserMangerServlet?method=getUserList&t="+new Date().getTime(),
	        idField:'id', 
	        singleSelect:false,//是否单选 
	        pagination:true,//分页控件 
	        rownumbers:true,//行号 
	        sortName:'id',
	        sortOrder:'DESC', 
	        remoteSort: false,
	        //field : Java Bean(StudentInfo.java)中的字段名哟 ~
	        columns: [[ 
				{field:'chk',checkbox: true,width:50},
 		        {field:'id',title:'ID',width:100, sortable: true},        
 		        {field:'name',title:'姓名',width:150},
 		        {field:'sex',title:'性别',width:100},
 		        {field:'iphone',title:'电话',width:150},
 		       {field:'password',title:'密码',width:150},
 		        {field:'xinzuo',title:'星座',width:100},
 		        {field:'birthday',title:'生日',width:100},
 		        {field:'liuyan',title:'留言',width:300},
 		        {field:'classId',title:'班级',width:100},]], 
	        toolbar: "#toolbar", 	        
	    });
		var p = $('#dataList').datagrid('getPager'); 
	    $(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [10,20,30,50,100],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	    }); 
	    
	  //设置工具类按钮
	    $("#add").click(function(){
	    	$("#addDialog").dialog("open");
	    });
	  	  
	    //删除
	    $("#delete").click(function(){
	    	var selectRows = $("#dataList").datagrid("getSelections");
        	var selectLength = selectRows.length;
        	if(selectLength == 0){
            	$.messager.alert("消息提醒", "请选择你想要删除的数据 !", "warning");
            } else{
            	var iphone = [];
            	$(selectRows).each(function(i, row){
            		iphone[i] = row.iphone;
            	});
            	var ids = [];
            	$(selectRows).each(function(i, row){
            		ids[i] = row.id;
            	});
            	$.messager.confirm("消息提醒", "仅删除与学生相关的所有数据! 确认继续 ?", function(r){
            		if(r){
            			$.ajax({
							type: "post",
							url: "UserMangerServlet?method=deleteUser",
							data: {iphone: iphone, ids: ids},
							success: function(msg){
								if(msg == "success"){
									$.messager.alert("消息提醒","删除成功!","info");
									//刷新表格
									$("#dataList").datagrid("reload");
									$("#dataList").datagrid("uncheckAll");
								} else{
									$.messager.alert("消息提醒","删除失败!","warning");
									return;
								}
							}
						});
            		}
            	});
            }
	    });
	    
	   //设置添加用户窗口
	    $("#addDialog").dialog({
	    	title: "添加用户",
	    	width: 600,
	    	height: 500,
	    	iconCls: "icon-add",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'添加',
					plain: true,
					iconCls:'icon-user_add',
					handler:function(){
						var validate = $("#addForm").form("validate");
						if(!validate){
							$.messager.alert("消息提醒","请检查你输入的数据哟 !","warning");
							return;
						} else{						
							$.ajax({
								type: "post",
								url: "UserMangerServlet?method=addUser",
								data: $("#addForm").serialize(),
								success: function(msg){
									if(msg == "success"){
										$.messager.alert("消息提醒","添加成功啦 ~","info");
										//关闭窗口
										$("#addDialog").dialog("close");
										//清空原表格数据
										$("#add_classId").textbox('setValue', "");
										$("#add_name").textbox('setValue', "");
										$("#add_password").textbox('setValue', "");
										$("#add_iphone").textbox('setValue', "");
										$("#add_sex").textbox('setValue', "男");
										$("#add_xinzuo").textbox('setValue', "");
										$("#add_birthday").textbox('setValue', "");
										$("#add_liuyan").textbox('setValue', "");
										
										//重新刷新页面数据
										$('#dataList').datagrid("options").queryParams = {clazzid: clazzid};
							  			$('#dataList').datagrid("reload");
							 
										
									} else{
										$.messager.alert("消息提醒","添加失败 !","warning");
										return;
									}
								}
							});
						}
					}
				},
				{
					text:'重置',
					plain: true,
					iconCls:'icon-reload',
					handler:function(){
						$("#add_classId").textbox('setValue', "");
						$("#add_name").textbox('setValue', "");
						$("#add_sex").textbox('setValue', "男");
						$("#add_password").textbox('setValue', "");
						$("#add_iphone").textbox('setValue', "");
						$("#add_xinzuo").textbox('setValue', "");
						$("#add_birthday").textbox('setValue', "");
						$("#add_liuyan").textbox('setValue', "");
					}
				},
			]
	    });
	    
	  //设置编辑学生窗口
	    $("#editDialog").dialog({
	    	title: "修改学生信息",
	    	width: 650,
	    	height: 460,
	    	iconCls: "icon-edit",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'提交',
					plain: true,
					iconCls:'icon-user_add',
					handler:function(){
						var validate = $("#editForm").form("validate");
						if(!validate){
							$.messager.alert("消息提醒","请检查你输入的数据哟 !","warning");
							return;
						} else{
							$.ajax({
								type: "post",
								url: "UserMangerServlet?method=editUser",
								data: $("#editForm").serialize(),
								success: function(msg){
									if(msg == "success"){
										$.messager.alert("消息提醒","更新成功 ~","info");
										//关闭窗口
										$("#editDialog").dialog("close");
										//刷新表格
										$("#dataList").datagrid("reload");
										$("#dataList").datagrid("uncheckAll");																	  		
							  			
									} else{
										$.messager.alert("消息提醒","更新失败 !","warning");
										return;
									}
								}
							});
						}
					}
				},
				{
					text:'重置',
					plain: true,
					iconCls:'icon-reload',
					handler:function(){
						//清空表单
						$("#edit_classId").textbox('setValue', "");
						$("#edit_name").textbox('setValue', "");
						$("#edit_password").textbox('setValue', "");
						$("#edit_iphone").textbox('setValue', "");
						$("#edit_sex").textbox('setValue', "男");
						$("#edit_xinzuo").textbox('setValue', "");
						$("#edit_birthday").textbox('setValue', "");
						$("#edit_liuyan").textbox('setValue', "");
					}
				}
			],
			onBeforeOpen: function(){
				var selectRow = $("#dataList").datagrid("getSelected");
				//设置值( selectRow.`学生信息属性名` )
						$("#edit_id").val(selectRow.id);
						$("#edit_classId").textbox('setValue', selectRow.classId);
						$("#edit_name").textbox('setValue', selectRow.name);
						$("#edit_sex").textbox('setValue', selectRow.sex);
						$("#edit_password").textbox('setValue',selectRow.password );
						$("#edit_iphone").textbox('setValue', selectRow.iphone);
						$("#edit_xinzuo").textbox('setValue', selectRow.xinzuo);
						$("#edit_birthday").textbox('setValue', birthday);
						$("#edit_liuyan").textbox('setValue', selectRow.liuyan);				
			},
	    
	    });
	   
	  //学生姓名搜索按钮监听事件
	  	$("#search").click(function(){
	  		$('#dataList').datagrid('load',{
	  			studentName: $('#studentName').val(),//初始化学生姓名
	  			
	  		});
	  	});
	});
</script>
</head>
<body>
	<table id="dataList" cellspacing="0" cellpadding="0"></table>
	<div id="toolbar">
		<!-- 通过JSTL设置用户操作权限 :  将添加和删除按钮设置为仅管理员可见 -->	
		<c:if test="${userType==1}">
			<div style="float: left;"><a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a></div>
				<div style="float: left;" class="datagrid-btn-separator"></div>
			 <div style="float: left;"><a id="delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-some-delete',plain:true">删除</a></div>
				<div style="float: left;" class="datagrid-btn-separator"></div>
				<div style="float: left;"><a id="edit" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a></div>
		</c:if>
		<!-- 学生姓名搜索框 -->
		<div style="margin-left: 10px;">用户<input id="studentName" class="easyui-textbox" name="studentName" />
			<!-- 搜索按钮 -->
			<a id="search" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
		</div>
	</div> 
	
		<!-- 添加信息窗口 -->
 	   <div id="addDialog" style="padding: 10px">  
	
    	<form id="addForm" method="post">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>班级:</td>
	    			<td><input id="add_classId" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="classId" data-options="required:true, missingMessage:'请填写班级号'" /></td>
	    		</tr>
	    		<tr>
	    			<td>姓名</td>
	    			<td><input id="add_name" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="name" data-options="required:true, missingMessage:'请填写姓名'" /></td>
	    		</tr>
	    		<tr>
	    			<td>密码</td>
	    			<td><input id="add_password" style="width: 200px; height: 30px;" class="easyui-textbox" type="password" name="password" data-options="required:true, missingMessage:'请填写密码'" /></td>
	    		</tr>
	    		<tr>
	    			<td>性别</td>
	    			<td><select id="add_sex" class="easyui-combobox" data-options="editable: false, panelHeight: 50, width: 60, height: 30" name="sex"><option value="男">男</option><option value="女">女</option></select></td>
	    		</tr>
	    		<tr>
	    			<td>电话</td>
	    			<td><input id="add_iphone"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="iphone" validType="mobile" data-options="required:true, validType:'repeat', missingMessage:'请填写联系电话'" /></td>
	    		</tr>
	    		<tr>
	    			<td>星座</td>
	    			<td><input id="add_xinzuo" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="xinzuo"   data-options="required:true, missingMessage:'请填写星座地址'"  /></td>
	    		</tr>
	    		<tr>
	    			<td>生日</td>
	    			<td><input id="add_birthday" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="birthday"   data-options="required:true, missingMessage:'请填写生日日期'" /></td>
	    		</tr>
	    		<tr>
	    			<td>留言</td>
	    			<td><input id="add_liuyan" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="liuyan"   data-options="required:true, missingMessage:'请填写留言'" /></td>
	    		</tr>
	    	</table>
	    </form>
	    
	    <!-- 修改信息窗口 -->
	<div id="editDialog" style="padding: 10px">
    	<form id="editForm" method="post">
    		<!-- 指定被修改信息的学生ID -->
    		<input type="hidden" id="edit_id" name="id"/>
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>班级:</td>
	    			<td><input id="edit_classId" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="classId" data-options="required:true, missingMessage:'请填写班级号'" /></td>
	    		</tr>
	    		<tr>
	    			<td>姓名</td>
	    			<td><input id="edit_name" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="name" data-options="required:true, missingMessage:'请填写姓名'" /></td>
	    		</tr>
	    		<tr>
	    			<td>密码</td>
	    			<td><input id="edit_password" style="width: 200px; height: 30px;" class="easyui-textbox" type="password" name="password" data-options="required:true, missingMessage:'请填写密码'" /></td>
	    		</tr>
	    		<tr>
	    			<td>性别</td>
	    			<td><select id="edit_sex" class="easyui-combobox" data-options="editable: false, panelHeight: 50, width: 60, height: 30" name="sex"><option value="男">男</option><option value="女">女</option></select></td>
	    		</tr>
	    		<tr>
	    			<td>电话</td>
	    			<td><input id="edit_iphone"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="iphone" validType="mobile" data-options="required:true, validType:'repeat', missingMessage:'请填写联系电话'" /></td>
	    		</tr>
	    		<tr>
	    			<td>星座</td>
	    			<td><input id="edit_xinzuo" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="xinzuo"   data-options="required:true, missingMessage:'请填写星座地址'"  /></td>
	    		</tr>
	    		<tr>
	    			<td>生日</td>
	    			<td><input id="edit_birthday" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="birthday"   data-options="required:true, missingMessage:'请填写生日日期'" /></td>
	    		</tr>
	    		<tr>
	    			<td>留言</td>
	    			<td><input id="edit_liuyan" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="liuyan"   data-options="required:true, missingMessage:'请填写留言'" /></td>
	    		</tr>
	    	</table>
	    </form>
	</div>
	
	
</body>
</html>