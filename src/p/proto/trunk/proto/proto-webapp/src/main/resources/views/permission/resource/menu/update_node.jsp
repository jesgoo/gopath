<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
	//添加资源
	function updateNode() {
		//清理以前的表单
		$('#addChildForm').get(0).reset();
		//初始化parentNode
		var node = $('#queryTable').treegrid('getSelected');
		if (node){
			if(node.nodeType == 1){
				$.messager.alert('提示信息', "不能修改根节点", 'info');
				return;
			}
			
			$("#updateWindow").show();
			$('#updateWindow').window({title:'修改节点',width:400,height:300});
			$('#updateWindow').window('open');
			$('#updateWindow').window('center');
			
			//加载信息
			var url = "${ctx}/permission/resource/menu/updateNode";
			updateFormSubmit(url);
			$('#updateForm').form("clear");
			$('#updateForm').form('load',{
								id:node.id,
								name:node.name,
								value:node.value,
								rootName:node.rootName
							});
		}else{
			$.messager.alert('提示信息', "请选择节点", 'info');
			return;
		}
		
		
	}
	//提交
	function updateFormSubmit(url){
		//提交
		$('#updateForm').form({  
		    url:url,  
		    onSubmit: function(){  
		    	return $('#updateForm').form("validate");
		    },  
		    success:function(data){  
		       $.messager.alert('提示信息', data, 'info');
		       $('#updateWindow').window('close');
		       $('#queryTable').treegrid('reload');
		       $('#queryTable').datagrid('clearSelections');
		    }  
		});
		
	}
		
	//初始化
	$(function(){
		$("#updateWindow").hide();
	});
</script>


<!--update account window-->
<div id="updateWindow" class="easyui-window"  
	data-options="modal:true,closed:true,resizable:false,maximizable:false,collapsible:false,minimizable:false">
	<form id="updateForm" method="post">
		<table>
			<tr>
				<input name="id" type="hidden" readonly="readonly">
				<td>菜单名称:</td>
				<td><input name="rootName" readonly="readonly"></td>
			</tr>
			<tr>
				<td>菜单项名称:</td>
				<td><input name="name" data-options="required:true,validType:'length[1,250]'" class="easyui-validatebox" ></td>
			</tr>
			<tr>
				<td>菜单项URL:</td>
				<td><input name="value" data-options="required:true,validType:'length[1,250]'" class="easyui-validatebox" ></td>
			</tr>
			
			<tr>
				<td></td>
				<td><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
</div>