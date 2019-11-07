<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<span style="color:red">${error }</span>
<form action="/20191107Web/login/loginCheck" method="post">
	<p>用户名：<input type="text" name="username"></p>
	<p>密码：<input type="password" name="password"></p>
	<button>登录</button>
</form>
</body>
</html>