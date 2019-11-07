<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="getdata/request" method="post">
<p>部门编号：<input type="text" name="deptno"></p>
<p>部门名称：<input type="text" name="dname"></p>
<p>所在地：<input type="text" name="loc"></p>
<button>添加部门request</button>
</form>
<hr/>
<form action="getdata/simple" method="post">
<p>部门编号：<input type="text" name="no"></p>
<p>部门名称：<input type="text" name="dname"></p>
<p>所在地：<input type="text" name="loc"></p>
<button>添加部门simpleType</button>
</form>

<hr/>
<form action="getdata/pojo2" method="post">
<p>部门编号：<input type="text" name="deptno"></p>
<p>部门名称：<input type="text" name="dname"></p>
<p>所在地：<input type="text" name="loc"></p>
<button>添加部门 pojo</button>
</form>
</body>
</html>