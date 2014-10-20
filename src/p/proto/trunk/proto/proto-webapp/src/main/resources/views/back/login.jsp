<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>登录</title>
		<script type="text/javascript">
			$(function(){
				$("#validateCode").attr("src","${ctx}/randCode/back?"+Math.random());
				$("#validateCode").click(function(){
					//带个随机参数，为了让浏览器发送请求
					$(this).attr("src","${ctx}/randCode/back?"+Math.random());
				});
				
				//隐藏提示信息
				$("#tip").hide();
				
				//表单提交前验证表单
				$("#btnSubmit").click(formSubmit);
			})
			
			//验证条件
			function checkForm(){
				return 	checkItem("#username","用户名","请输入账号","#tip") 
						&& checkItem("#password","密码","请输入密码","#tip")
						&&checkItem("#valdateCode","验证码","请输入验证码","#tip");
			}
			
			function checkItem(id,value,message,mid){
				if($(id).val() == null || $(id).val().length == 0 || $(id).val() == value){
					$(mid).empty();
					$(mid).append(message);
					$(mid).show();
					return false;
				}
				
				return true;
			}
			
			
			function formSubmit(){
				if(checkForm()){
					return true;
				}else{
					$("#validateCode").attr("src","${ctx}/randCode/back?"+Math.random());
					return false;
				}
			}
			
		</script>
	</head>
<body >
<span class="error" id="tip" >您的密码有误！</span><!--验证信息提示--> 
	<form id="loginForm" method="post" action="${ctx}/login/back">
		<table>
			<tr>
				<td>用户名</td>
				<td><input type="text" class="text"  value="用户名" id="username" name="username" maxlength="50" onfocus="javascript:if(this.value='用户名'){this.value='';}" onblur="javascript:if(!this.value){this.value='用户名';}" /></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="password" class="text" value="密码" id="password" name="password" type="password" maxlength="50" onfocus="javascript:if(this.value='密码'){this.value='';}" onblur="javascript:if(!this.value){this.value='密码';}" /></td>
			</tr>
			<tr>
				<td><img src="" id="validateCode" /></td>
				<td><input type="text" class="text w130" value="验证码" name="valdateCode" id="valdateCode" maxlength="4" onfocus="javascript:if(this.value='验证码'){this.value='';}" onblur="javascript:if(!this.value){this.value='验证码';}" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input id="btnSubmit"  type="submit" value="登录"/></td>
			</tr>
		</table>
	</form>
</body>

</html>

