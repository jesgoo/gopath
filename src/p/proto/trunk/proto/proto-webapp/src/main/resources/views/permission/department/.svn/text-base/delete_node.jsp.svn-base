<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
	//添加资源
	function deleteNode() {
		
		//初始化parentNode
		var node = $('#queryTable').treegrid('getSelected');
		if (node){
			$("#deleteWindow").show();
			$('#deleteWindow').window({title:'删除节点',width:400,height:300});
			$('#deleteWindow').window('open');
			$('#deleteWindow').window('center');
			$('#deleteForm').form("clear");
			
			$('#delete_id').attr("value",node.id);
			$('#delete_name').attr("value",node.name);
			$('#delete_value').attr("value",node.value);
			$('#delete_remark').attr("value",node.remark);
		}else{
			$.messager.alert('提示信息', "请选择节点", 'info');
			return;
		}
		
		//提交
		$('#deleteForm').form({  
		    url:"${ctx}/permission/department/deleteNode",  
		    onSubmit: function(){  
		    	return $('#deleteForm').form("validate");
		    },  
		    success:function(data){  
		       $.messager.alert('提示信息', data, 'info');
		       $('#deleteWindow').window('close');
		       $('#queryTable').treegrid('reload');
		       $('#queryTable').datagrid('clearSelections');
		    }  
		});
		
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