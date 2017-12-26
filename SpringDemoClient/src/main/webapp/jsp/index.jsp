<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页</title>
<script src="js/jquery-1.9.1.min.js"></script>

<script>

 $(document).ready(function(){  
	$('#user_name').blur(function(){
		$.get("/login.do",{
			username: $(this).val(),
			action:'check'
		},function(result){
			//这里显示从服务器返回的数据
			alert(result);
			if(!result){
				$("#name_message").html("用户名已被占用");
			} else {
				$("#name_message").html("用户名可以使用");
			}
		},"json");
	}) 

});

</script>
</head>
<body>
		<H1>Spring MVC DEMO</H1>
		${adminuser[0].name}
		<c:if test="${message != null}"><H2>Hello, ${message}</H2></c:if>
		<br/>
		登陆名：<input id="user_name" type="text"/>
		<br/><br/>
		<div id="name_message"></div>
		
</body>
</html>