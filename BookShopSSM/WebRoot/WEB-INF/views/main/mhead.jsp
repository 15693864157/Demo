<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

	 <tr>
      	<td align=right>      	    
      	      <c:if test="${user==null}">
      	         <a href="<%=basePath%>login.do">登录</a>      
      	      </c:if>
      	      <c:if test="${user!=null}">
      	         welcome you ${user.uname} &nbsp;<a href="<%=basePath%>user/shopcar.do">购物车</a>
      	  		&nbsp;<a href="<%=basePath%>user/logout.do">退出</a>
      	  		 <c:if test="${user.role==1}">
      	  		    <a href="<%=basePath%>back/upfile.do">后台</a>
      	  		 </c:if>	           	  	
      	      </c:if>   			 	
      	</td>
      </tr>