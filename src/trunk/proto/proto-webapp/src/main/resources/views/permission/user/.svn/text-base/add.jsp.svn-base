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
		    url:"${ctx}/permission/user/add",  
		    onSubmit: function(){ 
		    	return $('#addForm').form("validate");
		    },  
		    success:function(data){  
		       $.messager.alert('提示信息', data, 'info');
		       $('#addWindow').window('close');
		       $('#queryTable').datagrid('reload');
		    } 
		});
		
		//初始化角色
		initAddRoles();
		//初始化部门
		initAddDepartment();
		
	}
	
	//初始化角色信息
	function initAddRoles(){
		//加载权限信息
		$('#add_role').combobox( 
			{url:'${ctx}/permission/role/getComboxData',
				onSelect:function(record){
				},
				onLoadSuccess:function(){
				}
			});
	}
	
	//初始化部门信息
	function initAddDepartment(){
		$('#add_department').combotree({   
		    url: '${ctx}/permission/department/getTreeDepartment',   
		    required: true  
		});  

		
	}
	
	//将菜单树收缩起来
	function collapseAllDepartment(){
		var node = $('#add_department_tree').tree('getSelected');
		if (node){
			$('#add_department_tree').tree('collapseAll', node.target);
		} else {
			$('#add_department_tree').tree('collapseAll');
		}
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
				<td>用户名:</td>
				<td><input name="username" style="width:200px;" data-options="required:true,validType:'email'" class="easyui-validatebox" ></td>
			</tr>
			<tr>
				<td>姓名:</td>
				<td><input name="realName" style="width:200px;" data-options="required:true,validType:'length[1,50]'" class="easyui-validatebox" ></td>
			</tr>
			<tr>
				<td>角色:</td>
				<td>
				<input id="add_role" class="easyui-combobox" 
						name="rolesStr"
						data-options="required:true,valueField:'id',textField:'text',editable:false,multiple:true,panelHeight:'auto'" 
						style="width:200px;"
						>
				</td>
			</tr>
			<tr>
				<td>部门:</td>
				<td><input id="add_department" style="width:200px;"  name="departmentId" ></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
</div>