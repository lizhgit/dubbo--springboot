<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   
    <body>
   	
                	<form action="../login">
                        <div>用户名<input type="text" name="username"></div>
                        <div>密码<input type="password" name="password"></div>
               
                    <div id="btn">
                    	<input type="submit" value="登录"/>
                        <input type="button" value="注册" onClick="window.location.href='register.jsp'"/>
                    </div>
                    </form>
         
    </body>
</html>
