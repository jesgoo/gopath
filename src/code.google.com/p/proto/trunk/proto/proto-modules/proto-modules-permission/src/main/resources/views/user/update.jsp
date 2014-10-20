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
		var url = "${ctx}/permission/user/update";
		updateFormSubmit(url);
		$('#updateForm').form("clear");
		$('#updateForm').form('load',{
							id:row.id,
							username:row.username,
							realName:row.realName
						});
		
		//初始化角色
		initUpdateRoles(row);
		//初始化部门
		update_initDepartment(row);
		
	}
	
	
	//初始化操作信息
	function initUpdateRoles(row){
		//加载权限信息
		$('#update_role').combobox( 
			{url:'${ctx}/permission/role/getComboxData4User?userId='+row.id,
				onSelect:function(record){
				},
				onLoadSuccess:function(){
				}
			});
	}
	//初始化部门信息
	function update_initDepartment(row){
		$('#update_department_tree').tree({
				checkbox: false,
				url: '${ctx}/permission/department/getTreeDepartment',
				onClick:function(node){
					$("#update_department").attr('value',node.id);
				},
				onContextMenu: function(e, node){
					e.preventDefault();
				},
				onLoadSuccess:function(){
					var node = $('#update_department_tree').tree('find', row.departmentId);
					$('#update_department_tree').tree('select', node.target);
					update_collapseAllDepartment();
				}
				
			});
	}
	
	//将菜单树收缩起来
	function update_collapseAllDepartment(){
		var node = $('#update_department_tree').tree('getSelected');
		if (node){
			$('#update_department_tree').tree('collapseAll', node.target);
		} else {
			$('#update_department_tree').tree('collapseAll');
		}
	}
	
	//提交
	function updateFormSubmit(url){
		//修改提交
		$('#updateForm').form({  
		    url:url,  
		    onSubmit: function(){ 
				if(null == $("#update_department").val() || "" == $("#update_department").val()){
		    		$.messager.alert('提示信息', "请选择部门", 'info');
		    		return false;
		    	}
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
			<tr>
				<td>用户名:</td>
				<td><input name="username" readonly="readonly" style="width:200px;" data-options="required:true,validType:'email'" class="easyui-validatebox" ></td>
			</tr>
			<tr>
				<td>姓名:</td>
				<td><input name="realName" readonly="readonly"  style="width:200px;" data-options="required:true,validType:'length[1,50]'" class="easyui-validatebox" ></td>
			</tr>
			<tr>
				<td>角色:</td>
				<td>
				<input id="update_role" class="easyui-combobox" 
						name="rolesStr"
						data-options="valueField:'id',textField:'text',multiple:true,panelHeight:'auto'" 
						style="width:200px;"
						>
				</td>
				</td>
			</tr>
			<tr>
				<td>部门:</td>
				<td><ul id="update_department_tree" ></ul></td>
				<input type="hidden" id="update_department"  name="departmentId" >
			</tr>
			<tr>
				<td><input name="id" type="hidden" ></td>
				<td><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
</div>