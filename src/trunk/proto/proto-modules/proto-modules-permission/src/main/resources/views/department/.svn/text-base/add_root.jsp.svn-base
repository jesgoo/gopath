<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
	//添加资源
	function addRootNode() {
		$("#addRootWindow").show();
		$('#addRootWindow').window({title:'添加根节点',width:400,height:300});
		$('#addRootWindow').window('open');
		$('#addRootWindow').window('center');
		$('#addRootForm').form("clear");
		
		//提交
		$('#addRootForm').form({  
		    url:"${ctx}/permission/department/addRootNode",  
		    onSubmit: function(){  
		    	return $('#addRootForm').form("validate");
		    },  
		    success:function(data){  
		       $.messager.alert('提示信息', data, 'info');
		       $('#addRootWindow').window('close');
		       $('#queryTable').treegrid('reload');
		    } 
		});
		
	}
	
	//初始化
	$(function(){
		$("#addRootWindow").hide();
	});
</script>


<!--addRoot  window-->
<div id="addRootWindow" class="easyui-window"  
	data-options="modal:true,closed:true,resizable:false,maximizable:false,collapsible:false,minimizable:false">
	<form id="addRootForm" method="post">
		<table>
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