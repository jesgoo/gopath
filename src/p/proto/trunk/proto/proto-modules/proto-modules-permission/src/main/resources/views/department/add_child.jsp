<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
	//添加资源
	function addChildNode() {
		
		//初始化parentNode
		var node = $('#queryTable').treegrid('getSelected');
		if (node){
			$("#addChildWindow").show();
			$('#addChildWindow').window({title:'添加子节点',width:400,height:300});
			$('#addChildWindow').window('open');
			$('#addChildWindow').window('center');
			$('#addChildForm').form("clear");
			
			$('#parent_id').attr("value",node.id);
			$('#parent_name').attr("value",node.name);
		}else{
			$.messager.alert('提示信息', "请选择节点", 'info');
			return;
		}
		
		//提交
		$('#addChildForm').form({  
		    url:"${ctx}/permission/department/addChildNode",  
		    onSubmit: function(){  
		    	return $('#addChildForm').form("validate");
		    },  
		    success:function(data){  
		       $.messager.alert('添加资源', data, 'info');
		       $('#addChildWindow').window('close');
		       $('#queryTable').treegrid('reload');;
		    }  
		});
		
	}
	
	//初始化
	$(function(){
		$("#addChildWindow").hide();
	});
</script>


<!--addChild  window-->
<div id="addChildWindow" class="easyui-window"  
	data-options="modal:true,closed:true,resizable:false,maximizable:false,collapsible:false,minimizable:false">
	<form id="addChildForm" method="post">
		<table>
			<tr>
				<td>parent_id:</td>
				<td><input id="parent_id" name="parentNodeId" readonly="readonly"></td>
			</tr>
			<tr>
				<td>parent_name:</td>
				<td><input id="parent_name" name="parentNode.name" readonly="readonly"></td>
			</tr>
			<tr>
				<td>name:</td>
				<td><input name="name" data-options="required:true,validType:'length[1,250]'" class="easyui-validatebox" ></td>
			</tr>
			<tr>
				<td>value:</td>
				<td><input name="value" data-options="required:true,validType:'length[1,250]'" class="easyui-validatebox" ></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
</div>