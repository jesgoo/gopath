<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">

	function deletePermissionFormSubmit(row){
		//加载信息
		$('#deletePermissionForm').form("clear");
		$('#deletePermissionForm').form('load',{
							id:row.id,
							roleId:row.roleId
						});
		var form_url = "${ctx}/permission/roleDeletePermission";
		//提交
		$.messager.confirm('提示信息','确认删除?',function(r){  
		    if (r){  
		   		$('#deletePermissionForm').form('submit', {
					url:form_url , 
					success: function(data){
					   $.messager.alert('提示信息', data, 'info');
				       $('#permissionTable').datagrid('reload');
				       $('#permissionTable').datagrid('clearSelections');
					}
				});
							   		
		    } 
		}); 
		
	}

	//删除
	function deletePermissionObject() {
		var row = $('#permissionTable').datagrid('getSelected');
		if(row){
			$("#deletePermissionWindow").show();
			deletePermissionFormSubmit(row);
			
		}else{
			$.messager.alert('提示信息','请选择记录!','info');
		}
	}
	
	//初始化
	$(function(){
		$("#deletePermissionWindow").hide();
	});
	
</script>


<!--delete  window-->
<div id="deletePermissionWindow" class="easyui-window"  
	data-options="modal:true,closed:true,resizable:false,maximizable:false,collapsible:false,minimizable:false">
	<form id="deletePermissionForm" method="post">
		<table>
			<tr >
				<td>id:</td>
				<td><input  name="id" readonly="readonly"></td>
				<td><input  name="roleId" readonly="readonly"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
</div>