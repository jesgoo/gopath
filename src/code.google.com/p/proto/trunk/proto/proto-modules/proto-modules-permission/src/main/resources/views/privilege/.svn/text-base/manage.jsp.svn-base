<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>Insert title here</title>
		<script type="text/javascript">
			//表格宽度=屏幕宽度-菜单栏宽度
			var tableW = screen.availWidth - 350;
			var colNum = 2;
			var perColW = 	(tableW -60)/colNum;	
			/*分页插件初始化**/	
			$(function() {
				$('#queryTable').datagrid( {
					title : '操作',
					//数据源
					url : '${ctx}/permission/privilege/query',
					//数据栏
					columns : [ [ {
						field : 'id',title : 'ID',width : perColW
					}, {
						field : 'name',	title : '操作名称',	width : perColW
					} ] ],
					//工具栏
	 				toolbar : [ {
						text : '添加',iconCls : 'icon-add',	handler : function(){ addObject()}
					}, {
						text : '删除',iconCls : 'icon-remove',handler : function(){ deleteObject()}
					}, '-', {
						text : '修改',iconCls : 'icon-save',	handler : function(){updateObject()}
					} ],
			    	//加载完成后
			    	onLoadSuccess:function(data){ },
			    	//加载失败后
			    	onLoadError:function(){$.messager.alert('提示信息','加载失败!','error'); },
					//主键字段
					idField : 'id',
					//表格宽度
					width : tableW
				});
				
			})
		
		</script>
	</head>
	<body>
		<!--显示列表-->
		<table id="queryTable" style="width:700px;height:auto;" 
			data-options="pagination : true ,pageNumber : 1,nowrap: false,striped:true,rownumbers:true,singleSelect:true"></table>
		
		<!--添加-->
		<%@ include file="add.jsp"%>
		<!--修改-->
		<%@ include file="update.jsp"%>
		<!--删除-->
		<%@ include file="delete.jsp"%>
		
	</body>
</html>

