<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="addEmpController" method="post">
<p>员工编号：<input type="text" name="empno"></p>
<p>员工姓名：<input type="text" name="ename"></p>
<p>工作类型：<input type="text" name="job"></p>
<p>入职时间：<input type="text" name=hiredate>yyyy-MM-dd</p>
<p>领导编号：<input type="text" name="mgr"></p>
<p>工资：<input type="text" name="sal"></p>
<p>奖金：<input type="text" name="comm"></p>
<p>所在部门：<select name="dept.deptno">
	<option value="10">ACCOUNTING</option>
	<option value="20">RESEARCH</option>
	<option value="30">SALES</option>
	<option value="33">开发部</option>
</select></p>
<button>add</button>
</form>
</body>
</html>