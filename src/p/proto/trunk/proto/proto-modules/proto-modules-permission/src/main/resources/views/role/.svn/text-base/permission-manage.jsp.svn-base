<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	/*分页插件初始化**/
	function permissionManage() {
		var row = $('#queryTable').datagrid('getSelected');
		if(row){
			query(row);
		}else{
			$.messager.alert('提示信息','请选择记录!','info');
		}
	}

	//查询分页显示
	function query(row) {
		
		//表格宽度=屏幕宽度-菜单栏宽度
		var tableW = screen.availWidth - 350;
		var colNum = 5;
		var perColW = (tableW - 60) / colNum;
		
		$("#permissionWindow").show();
		$('#permissionWindow').window({title:'角色权限',height:300});
		$('#permissionWindow').window('open');
		$('#permissionWindow').window('center');
		
	
		$('#permissionTable').datagrid( {
						//数据源
						url : '${ctx}/permission/queryRolePermissions',
                        queryParams: {
								roleId: row.id,
								queryType: '1'
							},
							collapsible: true,
						//数据栏
						columns : [ [ {
							field : 'id',title : 'ID',width : perColW
						}, {
							field : 'roleName',	title : '角色名称',	width : perColW
						}, {
							field : 'resourceKey',title : '资源key',width : perColW
						}, {
							field : 'resourceValue',title : '资源value',width : perColW
						}, {
							field : 'privilegeName',title : '操作',width : perColW
						} ] ],
						//工具栏
		 				toolbar : [ {
							text : '菜单',iconCls : 'icon-add',handler : function(){updateMenuObject()}
						}, {
							text : '普通资源',iconCls : 'icon-add',handler : function(){updateCommonObject()}
						}, {
							text : '删除',iconCls : 'icon-remove',	handler : function(){deletePermissionObject()}
						}, '-',{
							text : '菜单',iconCls : 'icon-search',	handler : function(){showMenuPermission(row)}
						}, {
							text : '普通资源',iconCls : 'icon-search',handler : function(){showCommonPermission(row)}
						}, {
							text : '所有',iconCls : 'icon-search',handler : function(){showAllPermission(row)}
						} ],
				    	//加载完成后
				    	onLoadSuccess:function(){$(this).datagrid('freezeRow',0).datagrid('freezeRow',1);},
				    	//加载失败后
				    	onLoadError:function(){$.messager.alert('提示信息','加载失败!','error'); },
						//主键字段
						idField : 'id',
						//表格宽度
						width : tableW
						
		});
	
	}
	
		
	function showAllPermission(row){
		$('#permissionTable').datagrid('reload',{queryType:'1',roleId:row.id});
	}
	
	function showMenuPermission(row){
		$('#permissionTable').datagrid('reload',{queryType:'2',roleId:row.id});
	}
	
	function showCommonPermission(row){
		$('#permissionTable').datagrid('reload',{queryType:'3',roleId:row.id});
	}

	
	//初始化
	$(function(){
		$("#permissionWindow").hide();
	});
</script>

<div id="permissionWindow" class="easyui-window"  
	data-options="modal:true,closed:true,resizable:false,maximizable:false,collapsible:false,minimizable:false">

		<!--显示列表-->
		<table id="permissionTable" style="width:700px;height:auto;" 
			data-options="pagination : false ,pageNumber : 1,nowrap: false,striped:true,rownumbers:true,singleSelect:true"></table>
		
</div>

		<!--菜单-->
		<%@ include file="permission-menu-update.jsp"%>
		<!--普通资源-->
		<%@ include file="permission-common-add.jsp"%>
		<!--删除权限-->
		<%@ include file="permission-delete.jsp"%>