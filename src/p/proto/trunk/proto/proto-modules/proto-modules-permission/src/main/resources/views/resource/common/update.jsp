<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
	//初始化修改页面，恢复数据
	function updateFormShow(row){
		//初始化窗口
		$("#updateWindow").show();
		$('#updateWindow').window({title:'修改',width:400,height:300});
		$('#updateWindow').window('open');
		$('#updateWindow').window('center');
		
		
		//加载信息
		var url = "${ctx}/permission/resource/common/update";
		updateFormSubmit(url);
		$('#updateForm').form("clear");
		$('#updateForm').form('load',{
							id:row.id,
							key:row.key,
							value:row.value,
							remark:row.remark
						});
		
		
	}
	//提交
	function updateFormSubmit(url){
		//修改提交
		$('#updateForm').form({  
		    url:url,  
		    onSubmit: function(){  
		    	return $('#updateForm').form("validate");
		    },  
		    success:function(data){  
		       $.messager.alert('提示信息', data, 'info');
		       $('#updateWindow').window('close');
		       $('#queryTable').datagrid('reload');
		       $('#queryTable').datagrid('clearSelections');
		    }  
		});
		
	}

	//修改角色
	function updateObject() {
		var row = $('#queryTable').datagrid('getSelected');
		if(row){
			updateFormShow(row);
		}else{
			$.messager.alert('提示信息','请选择记录!','info');
		}
	}
	
	//初始化
	$(function(){
		$("#updateWindow").hide();
	});
</script>


<!--update  window-->
<div id="updateWindow" class="easyui-window"  
	data-options="modal:true,closed:true,resizable:false,maximizable:false,collapsible:false,minimizable:false">
	<form id="updateForm" method="post">
		<table>
			<tr >
				<td>id:</td>
				<td><input id="update_id"  name="id" readonly="readonly"></td>
			</tr>
				<tr>
				<td>key:</td>
				<td><input name="key" data-options="required:true,validType:'length[1,250]'" class="easyui-validatebox" ></td>
			</tr>
			<tr>
				<td>value:</td>
				<td><input name="value" data-options="required:true,validType:'length[1,250]'" class="easyui-validatebox" ></td>
			</tr>
			<tr>
				<td>remark:</td>
				<td><input name="remark" data-options="required:true,validType:'length[1,250]'" class="easyui-validatebox" ></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
</div>