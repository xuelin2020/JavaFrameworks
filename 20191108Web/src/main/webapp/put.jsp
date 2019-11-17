<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="restful/dododo" method="post">
	<input type="hidden" name="_method" value="PUT" />
	<input type="text" name="name" />
<button>put修改</button>
</form>
</body>
</html>