<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文件上传</title>
</head>
<body>
<form action="/20191107Web/file/up" method="post" enctype="multipart/form-data">
<input type="file" name="file" />
<p>用户名：<input type="text" name="username"></p>
	<p>密码：<input type="password" name="password"></p>
<button>上传</button>
</form>
</body>
</html>