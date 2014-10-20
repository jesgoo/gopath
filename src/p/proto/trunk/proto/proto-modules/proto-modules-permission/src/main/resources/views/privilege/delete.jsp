<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">

	function deleteFormSubmit(row){
		//加载信息
		$('#deleteForm').form("clear");
		$('#deleteForm').form('load',{
							id:row.id
						});
		var form_url = "${ctx}/permission/privilege/delete";
		//提交
		$.messager.confirm('提示信息','确认删除?',function(r){  
		    if (r){  
		   		$('#deleteForm').form('submit', {
					url:form_url , 
					success: function(data){
					   $.messager.alert('提示信息', data, 'info');
				       $('#queryTable').datagrid('reload');
				       $('#queryTable').datagrid('clearSelections');
					}
				});
							   		
		    } 
		}); 
		
	}

	//删除
	function deleteObject() {
		var row = $('#queryTable').datagrid('getSelected');
		if(row){
			$("#deleteWindow").show();
			deleteFormSubmit(row);
			
		}else{
			$.messager.alert('提示信息','请选择记录!','info');
		}
	}
	
	//初始化
	$(function(){
		$("#deleteWindow").hide();
	});
	
</script>


<!--delete  window-->
<div id="deleteWindow" class="easyui-window"  
	data-options="modal:true,closed:true,resizable:false,maximizable:false,collapsible:false,minimizable:false">
	<form id="deleteForm" method="post">
		<table>
			<tr >
				<td>id:</td>
				<td><input id="delete_id"  name="id" readonly="readonly"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
</div>