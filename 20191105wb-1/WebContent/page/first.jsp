<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Spring MVC</h1>
msg = ${msg }<br/>
request.msg = ${requestScope.msg }<br/>
session.msg = ${sessionScope.msg }<br/>
application.msg = ${applicationScope.msg }<br/>

</body>
</html>