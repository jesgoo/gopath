<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>合约书管理系统</title>
		<script type="text/javascript">
		
		//添加tab
		function addTab(title, url){
			var content = '<iframe src="'+url+'" frameborder="0" style="width:100%;height:100%;overflow:scroll;overflow-x:hidden"></iframe>';
		    if ($('#centerTabs').tabs('exists', title)){
		    	var tab = $('#centerTabs').tabs('getTab', title);
		     	$('#centerTabs').tabs('update',{
		            tab: tab,
					options: {
						content:content
					}
		        });
		        $('#centerTabs').tabs('select', title);
		    } else {
		        $('#centerTabs').tabs('add',{
		            title:title,
		            content:content,
		            closable:true
		        });
		    }
		}
		

		$(function(){
			$('#menuTree').tree({
				checkbox: false,
				url: '${ctx}/permission/resource/menu/getTreeMenuOfBack',
				onClick:function(node){
					var b = $('#menuTree').tree('isLeaf', node.target);
					if(b){
						addTab(node.text,'${ctx}/'+node.attributes.value);
					}
					
				},
				onContextMenu: function(e, node){
					e.preventDefault();
				}
			});
		});


		
		</script>
				
	</head>
<body class="easyui-layout"   >
	<!--顶模块-->
	<div region="north" border="false" style="height:50px;">
		<div>proto</div>
	</div>
	<!--菜单模块-->
	<div region="west"  style="width:200px;">
		<ul id="menuTree"></ul>
		
	</div>
	</div>
	<!--主模块-->
	<div region="center" >
		<div id="centerTabs" class="easyui-tabs" fit="true" border="false">
		</div>
	</div>
</body>

</html>

