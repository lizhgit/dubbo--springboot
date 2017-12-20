<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>注册</title>


</head>

<body>


	<form action="../reg">
		<div>
			<input class="form" type="text" placeholder="请输入用户名" name="user_name">
		</div>
		<div>
			<input class="form" type="password" placeholder="请输入密码"
				name="password">
		</div>
		
		<input type="submit" value="注册" /> <input type="button" value="重置"
			onClick="window.location.href='register.jsp'" />
	</form>
</body>
</html>
