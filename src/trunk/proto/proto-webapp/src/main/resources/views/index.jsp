<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<link rel="stylesheet" type="text/css"	href="${ctx}/js/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"	href="${ctx}/js/easyui/themes/icon.css">
		<script type="text/javascript"	src="${ctx}/js/easyui/jquery-1.6.min.js" ></script>
		<script type="text/javascript"	src="${ctx}/js/easyui/jquery.easyui.min.js" ></script>
		
		<script type="text/javascript">
		$(function(){
			var p = $('body').layout('panel','west').panel({
				onCollapse:function(){
					alert('collapse');
				}
			});
		});
		
		//添加tab
		function addTab(title, url){
			var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
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
		
		
		</script>
				<title>Insert title here</title>
	</head>
<body class="easyui-layout">
	<!--顶模块-->
	<div region="north" border="false" style="height:60px;background:#B3DFDA;">north region</div>
	<!--菜单模块-->
	<div region="west" split="true" title="West" style="width:150px;">
		<!--折叠框-->
		<div class="easyui-accordion" fit="true" border="false" >
			<!--标签1-->
			<div title="Title1" style="overflow:auto;" selected="true" >
				<a href="#" onclick="addTab('easyui','${ctx}/account!findAll.action')" class="easyui-linkbutton" icon="icon-search" plain="true"  style="width: 150px;padding-left: 20px;" >Query</a><br/>
				<a href="#" onclick="addTab('add','${ctx}/page/front/account/add.jsp')" class="easyui-linkbutton" icon="icon-cancel" plain="true" style="width: 150px;padding-left: 20px;" >Cancel</a>
			</div>
			<!--标签2-->
			<div title="Title2" style="padding:10px;">
				<div class="easyui-panel" title="Picture Tasks" collapsible="true" style="width:100px;height:auto;padding:5px;">
			        View as a slide show<br/>
			        Order prints online<br/>
			        Print pictures
			    </div>
			</div>
		</div>

	</div>
	<!--右边推广模块-->
<!--	<div region="east" split="true" title="East" style="width:100px;padding:10px;">east region</div>-->
	<!--底模块-->
	<div region="south" border="false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<!--主模块-->
	<div region="center" title="Main Title">
		<div id="centerTabs" class="easyui-tabs" fit="true" border="false">
		</div>
	</div>

</body>

</html>

