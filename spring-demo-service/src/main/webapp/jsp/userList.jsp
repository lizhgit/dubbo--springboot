<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页</title>
<script src="js/jquery-1.9.1.min.js"></script>

<script>
	
</script>
</head>
<body>
${msg }<br/>
	<table border=1>
		<tr>
			<!-- <th></th> -->
			<th>id</th>
			<th>name</th>
			<th>phone</th>
		</tr>
		<c:forEach items="${uList}" var="user" varStatus="vs">
			<tr>
				<!-- <td><s:property value="#vs.index+1" /></td> -->
				<td align="center">${user.user_id}</td>
				<td align="center">${user.user_name}</td>
				<td align="center">${user.password}</td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="downUserList">导出</a>
</body>
</html>