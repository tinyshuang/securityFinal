<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	request.setAttribute("basePath", path);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>  
<title>登录</title>  
</head>  
<body>  
	<!-- 写了过滤器之后,我们可以在表单中使用我们定义的登录验证路径地址 -->
    <form action ="${basePath}/login.do" method="POST">  
    <table>  
        <tr>  
            <td>用户:</td>  
            <!--  以下表单值可以直接使用实体类的值了-->
            <td><input type ='text' name='name'></td>  
        </tr>  
        <tr>  
            <td>密码:</td>  
            <td><input type ='password' name='pwd'></td>  
        </tr>  
        <tr>  
            <td><input name ="reset" type="reset"></td>  
            <td><input name ="submit" type="submit"></td>  
        </tr>  
    </table>  
    </form>  
</body>  
</html>
