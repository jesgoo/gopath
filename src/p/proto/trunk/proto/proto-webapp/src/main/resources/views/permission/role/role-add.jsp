<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
	//添加
	function addObject() {
		$("#addWindow").show();
		$('#addWindow').window({title:'添加',width:400,height:300});
		$('#addWindow').window('open');
		$('#addWindow').window('center');
		$('#addForm').form("clear");
		
		//提交
		$('#addForm').form({  
		    url:"${ctx}/permission/role/add",  
		    onSubmit: function(){  
		    	return $('#addForm').form("validate");
		    },  
		    success:function(data){  
		       $.messager.alert('提示信息', data, 'info');
		       $('#addWindow').window('close');
		       $('#queryTable').datagrid('reload');
		    } 
		});
		
	}
	
	//初始化
	$(function(){
		$("#addWindow").hide();
	});
	
</script>


<!--add  window-->
<div id="addWindow" class="easyui-window"  
	data-options="modal:true,closed:true,resizable:false,maximizable:false,collapsible:false,minimizable:false">
	<form id="addForm" method="post">
		<table>
			<tr>
				<td>角色名称:</td>
				<td><input name="name" data-options="required:true,validType:'length[1,50]'" class="easyui-validatebox" ></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
</div>