<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	   function tijiao(){
	       var unameText = document.getElementById("uname");
	       var pwdText = document.getElementById("pwd");
	       if(unameText.value == ""){
	           alert("用户名不能为空");
	           return false;
	       }
	       if(pwdText.value == ""){
	          alert("密码不能为空");
	          return false;
	       }
	       var myform = document.getElementById("myform");
	       myform.submit();	   
	   }	
	</script>
  </head>
  
  <body>
       <form action="<%=basePath%>login.do" method="post" id="myform">
       <table align="center" >
           <tr><td height="80"></td></tr>
           <tr><td>用户名：</td><td><input type="text" name="uname" id="uname"></td></tr>
           <tr><td>密码：</td><td><input type="password" name="pwd" id="pwd"></td></tr>
           <tr><td colspan=2 > <input type="button"  value="提交" onclick="tijiao()"/> </td></tr>
           <tr><td colspan=2>${msg}</td></tr>
       </table>
       </form>
  </body>
</html>
