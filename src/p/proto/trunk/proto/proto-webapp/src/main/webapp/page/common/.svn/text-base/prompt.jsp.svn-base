<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>提示页面</title>
<style type="text/css">
body
{
text-align: center;
background-color:#608fbf;
}

#show_container
{
  background: #ffffff;
  width:400px;
  height:200px;
  border: 1px solid red;
  margin: 100px auto;
  padding: 0;
  font:16px "Microsoft YaHei";
}
#show_title
{
  margin: 10px auto;
  padding: 0;
  text-align:center;
  font-size:50px;
}
#show_content
{
  margin: 10px auto;
  padding: 0;
  text-align:center;
  color:red;
  font-size:30px ;
}
#show_url
{
  margin: 10px auto;
  padding: 0;
  text-align:center;
  color:blue;
  font-size:20px;
}
#show_url a
{
width:7em;
text-decoration:none;
color:white;
background-color:purple;
padding:0.2em 0.6em;
   font-size:16px;
}
#show_url a:hover {background-color:#ff3300}
</style>
  </head>

  <body>
	
	<div id="show_container">
	  <div id="show_title">
	   	 提示信息
	  </div>
	  <div id="show_content">
	    ${prompt }
	    ${exception.message}
	  </div>
	  <div id="show_url">
	  	<%-- 有跳转连接 --%>
		 <c:if test="${!empty requestScope.backURL}">
			<a href="${ctx}/${backURL}">跳转地址</a>
		</c:if>
		<%-- 没有有跳转连接 --%>
		<c:if test="${empty requestScope.backURL}">
			<a href="javascript:history.back(-1);">跳转地址</a>
		</c:if>
	  </div>
	</div>
		

  </body>
</html>
