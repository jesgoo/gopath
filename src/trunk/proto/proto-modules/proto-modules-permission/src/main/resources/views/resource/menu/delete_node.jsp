<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
	
	function deleteFormSubmit(row){
		//加载信息
		$('#deleteForm').form("clear");
		$('#deleteForm').form('load',{
							id:row.id
						});
		var form_url = "${ctx}/permission/resource/menu/deleteNode";
		//提交
		$.messager.confirm('提示信息','确认删除?',function(r){  
		    if (r){  
		   		$('#deleteForm').form('submit', {
					url:form_url , 
					success: function(data){
					   $.messager.alert('提示信息', data, 'info');
				       $('#queryTable').treegrid('reload');
				       $('#queryTable').treegrid('clearSelections');
					}
				});
							   		
		    } 
		}); 
		
	}

	//添加资源
	function deleteNode() {
		
		var row = $('#queryTable').treegrid('getSelected');
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


<!--delete window-->
<div id="deleteWindow" class="easyui-window"  
	data-options="modal:true,closed:true,resizable:false,maximizable:false,collapsible:false,minimizable:false">
	<form id="deleteForm" method="post">
		<table>
			<tr>
				<td>id:</td>
				<td><input id="delete_id" name="id" readonly="readonly"></td>
			</tr>
			<tr>
				<td>remark:</td>
				<td><input id="delete_remark" name="remark" readonly="readonly"></td>
			</tr>
			<tr>
				<td>name:</td>
				<td><input id="delete_name" name="name" readonly="readonly"></td>
			</tr>
			<tr>
				<td>value:</td>
				<td><input id="delete_value" name="value" readonly="readonly"></td>
			</tr>
			
			<tr>
				<td></td>
				<td><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
</div>