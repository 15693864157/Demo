<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'regist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
	<script type="text/javascript">
	    function unameValid(){
	        var uname = $('#uname').val();
	        var span = document.getElementById("unameAlert");
	        $.ajax({
			   type: "GET",
			   url: "<%=basePath%>RegistSvl?uname=" + uname,
			   success: function(msg){
			       if(msg == 1){
			          span.innerHTML = "用户名已存在，请重新输入";
			       }else{
			           span.innerHTML = "可以使用";
			       }			       
			   }
			});	    
	    }
	</script>

  </head>
  
  <body>
    <table  border="0" cellpadding="0" cellspacing="0" align="center">
							<tr><td height=100></td></tr>
							<tr>
							  <td width="107" height="36">用户名：</td>
							  <td width="524"><INPUT name="uname" id="uname" type="text" maxlength="16" onblur="unameValid()">
								<span id="unameAlert"></span>
							  </td>
							</tr>
							<tr>
							  <td width="107" height="36">密码：</td>
							  <td width="524"><INPUT name="pwd" id="pwd" type="password"></td>
							</tr>
							<tr>
							  <td width="107" height="36">确认密码：</td>
							  <td width="524"><INPUT name="pwd2" type="password"></td>
							</tr>
						
							<tr>
							<td width="107" height="36">电话：</td>
							<td width="524"><INPUT name="tel" type="text"></td>
						  </tr>   
							<tr>
								<td colspan=2 >
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="提交"> &nbsp; <a href="#">返回</a>
								</td>
							</tr>
		</table>

  </body>
</html>
