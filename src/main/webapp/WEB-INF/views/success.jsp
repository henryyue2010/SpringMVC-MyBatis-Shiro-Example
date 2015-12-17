<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String url = request.getRequestURL().toString();
	url = url.substring(0, url.indexOf('/', url.indexOf("//") + 2));
	String context = request.getContextPath();
	url += context;
	application.setAttribute("ctx", url);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Success</title>
</head>
<body>
	<h1>index</h1>
	<a href="${ctx}/logout">Logout</a>
	<p></p>
	<shiro:guest>  
    	Hi there!  Please <a href="${ctx}/index">Login</a> or <a
			href="${ctx}/index">Signup</a> today!  
	</shiro:guest>
	<p>shiro user</p>
	<shiro:user>  
    	Welcome back John!  Not John? Click <a href="${ctx}/index">here<a>
				to login. 
	</shiro:user>
	<p></p>
	<shiro:authenticated>
		<a href="${ctx}/index">Update your contact information</a>.  
	</shiro:authenticated>
	<p></p>
	<shiro:notAuthenticated>  
	    Please <a href="${ctx}/index">login</a> in order to update your credit card information.  
	</shiro:notAuthenticated>
	
	Hello,
	<shiro:principal />
	, how are you today?
	<p></p>
	<shiro:hasRole name="administrator">
		<a href="${ctx}/index">Administer the system</a>
	</shiro:hasRole>
	<p></p>
	<shiro:lacksRole name="administrator">  
	    Sorry, you are not allowed to administer the system.  
	</shiro:lacksRole>
	<p></p>
	<shiro:hasAnyRoles name="developer, project manager, administrator">
	    You are either a developer, project manager, or administrator.  
	</shiro:hasAnyRoles>
	<p></p>
	<shiro:hasPermission name="user:create">
		<a href="${ctx}/index">Create a new User</a>
	</shiro:hasPermission>
	<p></p>
	<shiro:hasPermission name="user:create">
		<a href="${ctx}/index">Create a new User</a>
	</shiro:hasPermission>
</body>
</html>