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
			var colNum2 = 3;//后面三列
			var perColW2 = 	40;	
			var perColW = 	(tableW -60 )/colNum;	
			/*分页插件初始化**/	
			$(function(){
				$('#queryTable').treegrid({
					width:tableW,
					nowrap: false,
					rownumbers: true,
					collapsible:true,
					url:'${ctx}/permission/department/query',
					idField:'id',
					treeField:'name',
					columns:[[
						{field:'name',title:'部门名称',width:perColW},						
						{field:'value',title:'简介',width:perColW},
						{field:'rootName',title:'rootName',width:perColW,hidden:true},
						{field:'left',title:'left',width:perColW2,hidden:true},
						{field:'right',title:'right',width:perColW2,hidden:true},
						{field:'id',title:'id',width:perColW,hidden:true},
						{field:'pid',title:'pid',width:perColW,hidden:true},
						{field:'nodeType',title:'nodeType',width:perColW2,hidden:true}
					]],
					onContextMenu: function(e,row){
						e.preventDefault();
						$('#rightButtonMenu').menu('show', {
							left: e.pageX,
							top: e.pageY
						});
					},
					onLoadSuccess: function(row, data){
						$('#queryTable').treegrid('collapseAll');
					}
				});
			})
		</script>
	</head>
	<body>
		<!--菜单显示列表-->
		<table id="queryTable" style="width:700px;height:auto;" 
			data-options="nowrap: false,striped:true,rownumbers:true,singleSelect:true"></table>
		
		
		<br/>
		<a href="#" class="easyui-linkbutton" onclick="addRootNode()">添加根节点</a>
		<a href="#" class="easyui-linkbutton" onclick="addChildNode()">添加子节点</a>
		<a href="#" class="easyui-linkbutton" onclick="updateNode()">更新节点</a>
		<a href="#" class="easyui-linkbutton" onclick="deleteNode()">删除节点</a>
		
		<!--添加root-->
		<%@ include file="add_root.jsp"%>
		<!--添加childNode-->
		<%@ include file="add_child.jsp"%>
		<!--更新节点-->
		<%@ include file="update_node.jsp"%>
		<!--删除节点-->
		<%@ include file="delete_node.jsp"%>
		
		<!-- 右键菜单 -->
		<div id="rightButtonMenu" class="easyui-menu" style="width:120px;">
			<div onclick="addChildNode()">添加</div>
			<div onclick="updateNode()">修改</div>
			<div onclick="deleteNode()">删除</div>
		</div>
		
	</body>
</html>

