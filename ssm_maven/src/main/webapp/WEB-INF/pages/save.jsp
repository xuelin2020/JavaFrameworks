<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/dept/save" method="post">
<p>部门编号：<input type="text" name="deptno"/></p>
<p>部门名称：<input type="text" name="dname"/></p>
<p>loc：<input type="text" name="loc"/></p>
<button>添加</button>
</form>
</body>
</html>