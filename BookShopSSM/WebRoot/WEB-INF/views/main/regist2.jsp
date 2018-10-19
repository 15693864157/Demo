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
	<script type="text/javascript">
	    var xmlhttp;
	    function loadXMLDoc(url){
                xmlhttp=null;
				// 针对 Mozilla等浏览器的代码：
				if (window.XMLHttpRequest)
				  {
				     xmlhttp=new XMLHttpRequest();
				  }				
				// 针对 IE 的代码：
				else if (window.ActiveXObject)
				  {
				     xmlhttp=new ActiveXObject("Microsoft.XMLHTTP")
				  }
				if (xmlhttp!=null)
				  {
				  xmlhttp.onreadystatechange=state_Change22;       //注册回调函数				 
				  var uname = document.getElementById("uname");
				  var url = "<%=basePath%>RegisSvl?uname=" + uname.value;
				  xmlhttp.open("GET",url,true)
				  xmlhttp.send(null)
				  }
				else
				  {
				  alert("您的浏览器不支持XMLHTTP")
				  }
		}
		
		//与远程服务器交互完成后，接收服务器的响应信息
		function state_Change22()
		{
		// 如果 xmlhttp 显示为 "loaded"
		if (xmlhttp.readyState==4)
		  {
		  // 如果为 "OK"
		  if (xmlhttp.status==200)
		    {
		       var result = xmlhttp.responseText;
		       alert(result);
		    }
		  else
		    {
		    alert("Problem retrieving XML data")
		    }
		  }
		}
		
	    
	    function unameValid(){
	        loadXMLDoc();       
	       
	    
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
