<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'BookAdd.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/themes/icon.css">	
	<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
  </head>
  
  <body>
   <table align="center" width=90%>
      <jsp:include page="bhead.jsp"></jsp:include>   	
      <tr><td align="left"><h2>新书上架</h2></td></tr>
      <tr>
      	<td>
      	<form action="<%=basePath%>back/upfile.do" method="post" enctype="multipart/form-data" onsubmit="return validName()">
      		<table border="0" width=60% align="center">
      			<tr><td>图书类型</td><td>
      			    <select name="btype">
      			       <c:forEach var="tp" items="${types}">
      			          <option value="${tp.tno}">${tp.tname}</option>
      			       </c:forEach>
      			    </select>      			
      			</td></tr>
      			<tr><td>书号ISBN</td><td><input type="text" name="isbn"/></td></tr>
       			<tr><td>书名</td><td><input type="text" name="bname"/><span id="NameNull"></span></td></tr>
       			<tr><td>作者</td><td><input type="text" name="author"/></td></tr>
       			<tr><td>出版社</td><td><input type="text" name="press"/></td></tr>
       			<tr><td>出版日期</td><td><input type="text" name="pubdate" class="easyui-datebox"/></td></tr>
       			<tr><td>价格</td><td><input  name="price" class="easyui-numberbox" precision="2" value="0"></td></tr>
       			<tr><td>图片上传</td><td><input type="file" name="pic2"/></td></tr>
       			<tr><td colspan=2 align=center><input type=submit value=提交 /></td></tr>
       			<tr><td>${msg}</td></tr>
    		</table>
    	</form>
      	</td>
      </tr>
    </table>

  </body>
</html>
