<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	request.setAttribute("basePath", path);
%>
<html>
<body>
	  index
	  <!--退出还是使用这个路径  -->
	  <a href="${basePath }/j_spring_security_logout">退出</a>
</body>
</html>