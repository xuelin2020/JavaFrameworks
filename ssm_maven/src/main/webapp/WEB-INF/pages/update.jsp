<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form action="${pageContext.request.contextPath }/dept/update" method="post" modelAttribute="dept">
	<input type="hidden" name="_method" value="put" />
	<form:hidden path="deptno"/>
	<p>部门名称：<form:input path="dname"/></p>
	<p>loc：<form:input path="loc"/></p>
	<button>修改</button>
</form:form>
</body>
</html>