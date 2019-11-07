<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>显示数据</title>
</head>
<body>
<h2>string = ${string }</h2>
<h2>user = ${user.uid}-${user.uname }-${user.upwd }</h2>
<h2>
  list=
  <c:forEach items="${list }" var="user" varStatus="st">
  	user${st.count } = ${user.uid}-${user.uname }-${user.upwd }<br/>
  </c:forEach>
</h2>

</body>
</html>