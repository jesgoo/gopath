<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script type="text/javascript"	src="${ctx}/js/common/system_config.js" ></script>
<script type="text/javascript">

	//初始化修改页面，恢复数据
	function updateMenuFormShow(row){
		//初始化窗口
		$("#updateMenuWindow").show();
		$('#updateMenuWindow').window({title:'菜单权限',width:400,height:300});
		$('#updateMenuWindow').window('open');
		$('#updateMenuWindow').window('center');
		
		
		//加载信息
		var url = "${ctx}/permission/roleUpdateMenuPermission";
		updateMenuFormSubmit(url);
		//恢复角色信息
		$('#updateMenuForm').form("clear");
		$('#updateMenuForm').form('load',{
							roleId:row.id,
							roleName:row.name
						});
		
		//初始化操作信息
		initPrivilege(row);
			
			
	}
	
	//提交
	function updateMenuFormSubmit(url){
		$('#updateMenuForm').form({  
		    url:url,  
		    onSubmit: function(){  
		    	return $('#updateMenuForm').form("validate");
		    },  
		    success:function(data){  
		       $.messager.alert('提示信息', data, 'info');
		       $('#updateMenuWindow').window('close');
		       $('#permissionTable').datagrid('reload');
		    }  
		});
	}
	
	//初始化菜单信息
	function initMenu(row,privilegeId){
		$('#updateMenuTree').combotree({   
			url: '${ctx}/permission/resource/menu/getAllTreeMenu4Role?roleId='+row.id+'&privilegeId='+privilegeId, 
		    required: true,
		    multiple: true
		}); 

	}

	//初始化操作信息
	function initPrivilege(row){
		//加载权限信息
		$('#update_privilege').combobox( 
			{url:'${ctx}/permission/privilege/getComboxData',
				onSelect:function(record){
					initMenu(row,record.id);
				},
			onLoadSuccess:function(){
					var data = $('#update_privilege').combobox('getData');
					$('#update_privilege').combobox('select',data[0].id);
				}
			});
	}
	

	//添加权限
	function updateMenuObject() {
		
		var row = $('#queryTable').datagrid('getSelected');
		if(row){
			updateMenuFormShow(row);
		}else{
			$.messager.alert('提示信息','请选择记录!','info');
		}
	}
	
	//将菜单树收缩起来
	function collapseAllMenu(){
		var node = $('#updateMenuTree').tree('getSelected');
		if (node){
			$('#updateMenuTree').tree('collapseAll', node.target);
		} else {
			$('#updateMenuTree').tree('collapseAll');
		}
	}
		
	//初始化
	$(function(){
		$("#updateMenuWindow").hide();
	});
</script>


<div id="updateMenuWindow" class="easyui-window"  
	data-options="modal:true,closed:true,resizable:false,maximizable:false,collapsible:false,minimizable:false">
	<form id="updateMenuForm" method="post">
		<table>
			<tr>
				<td>角色:</td>
				
				<td><input name="roleName" readonly="readonly" style="width:200px;" data-options="required:true,validType:'length[1,50]'" class="easyui-validatebox" ></td>
				
				<td><input type="hidden" name="roleId" class="easyui-validatebox"  readonly="readonly"></td>
				
			</tr>
			<tr>
				<td>操作:</td>
				<td>
				<input id="update_privilege" class="easyui-combobox" 
						name="privilegeId"
						data-options="valueField:'id',textField:'text',editable:false,multiple:false,panelHeight:'auto'" 
						style="width:200px;"
						>
				</td>
			</tr>
			<tr>
				<td>资源:</td>
				<!--菜单资源的树形结构-->
<!-- 				<td><ul id="updateMenuTree" ></ul></td> -->
				<td><input id="updateMenuTree" style="width:200px;"  name="menuIds" ></td>
							
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="修改"></td>
			</tr>
		</table>
	</form>
</div>