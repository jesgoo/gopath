<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript">
	//添加权限
	function updateCommonObject() {
			var row = $('#queryTable').datagrid('getSelected');
			if(row){
				updateCommonFormShow(row);
			}else{
				$.messager.alert('提示信息','请选择记录!','info');
			}
		
	}

	//初始化修改页面，恢复数据
	function updateCommonFormShow(row){
		//初始化窗口
		$("#addCommonWindow").show();
		$('#addCommonWindow').window({title:'普通资源权限',width:400,height:300});
		$('#addCommonWindow').window('open');
		$('#addCommonWindow').window('center');
		
		
		//加载信息
		var url = "${ctx}/permission/roleAddCommonPermission";
		updateCommonFormSubmit(url);
		//恢复角色信息
		$('#addCommonForm').form("clear");
		$('#addCommonForm').form('load',{
							roleId:row.id,
							roleName:row.name
						});
			
		//恢复角色信息
		$('#addCommonForm').form("clear");
		$('#addCommonForm').form('load',{
							roleId:row.id,
							roleName:row.name
						});
		
		//初始化操作信息
		initCommonPrivilege(row);
		initCommonResource(row);
		
			
	}
	
	//初始化操作信息
	function initCommonPrivilege(row){
		//加载权限信息
		$('#common_privilege').combobox( 
			{url:'${ctx}/permission/privilege/getComboxData',
				onSelect:function(record){
				},
				onLoadSuccess:function(){
					var data = $('#common_privilege').combobox('getData');
					$('#common_privilege').combobox('select',data[0].id);
				}
			});
	}
	
	//初始化操作信息
	function initCommonResource(row){
		//加载权限信息
		$('#common_resource').combobox( 
			{url:'${ctx}/permission/resource/common/getComboxData',
				onSelect:function(record){
				},
				onLoadSuccess:function(){
					var data = $('#common_resource').combobox('getData');
					$('#common_resource').combobox('select',data[0].id);
				}
			});
	}
	
	//提交
	function updateCommonFormSubmit(url){
		$('#addCommonForm').form({  
		    url:url,  
		    onSubmit: function(){  
		    	return $('#addCommonForm').form("validate");
		    },  
		    success:function(data){  
		       $.messager.alert('提示信息', data, 'info');
		       $('#addCommonWindow').window('close');
		       $('#permissionTable').datagrid('reload');
		    }  
		});
	}

	//初始化
	$(function(){
		$("#common_resource").hide();
	});
</script>


<!--add account window-->
<div id="addCommonWindow" class="easyui-window"  
	data-options="modal:true,closed:true,resizable:false,maximizable:false,collapsible:false,minimizable:false">
	<form id="addCommonForm" method="post">
		<table>
			<tr>
				<td>角色:</td>
				
				<td><input name="roleName" readonly="readonly" style="width:200px;" data-options="required:true,validType:'length[1,50]'" class="easyui-validatebox" ></td>
				
				<td><input type="hidden" name="roleId" class="easyui-validatebox"  readonly="readonly"></td>
			</tr>
			<tr>
				<td>操作:</td>
				<td>
				<input id="common_privilege" class="easyui-combobox" 
						name="privilegeId"
						data-options="valueField:'id',textField:'text',editable:false,multiple:false,panelHeight:'auto'" 
						style="width:200px;"
						>
				</td>
			</tr>
			<tr>
				<td>普通资源:</td>
				<td>
				<input id="common_resource" class="easyui-combobox" 
						name="resourceId"
						data-options="valueField:'id',textField:'text',multiple:false,panelHeight:'auto'" 
						style="width:200px;"
						>
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" value="提交">
				</td>
			</tr>
		</table>
	</form>
</div>