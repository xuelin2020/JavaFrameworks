<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RESTFUL</title>
<style type="text/css">
	.delbtn{
		border:none;
		background:#fff;
		text-decoration: none;
		color:blue;
		font-size:16px;
	}
	.delfrom{
		display:inline-block;
	}
</style>
</head>
<body>

1  Tom  123456  男
<form class="delfrom" action="restful/dododo" method="post">
<input type="hidden" value="delete" name="_method">
<input type="hidden" value="Tom" name="name">
  <button class="delbtn">删除</button>
</form>

<form action="restful/dododo" method="get">
	<input type="text" name="name" />
<button>get获取</button>
</form>

<form action="restful/dododo" method="post">
	<input type="text" name="name" />
<button>post新增</button>
</form>

<form action="restful/dododo" method="post">
	<input type="hidden" name="_method" value="PUT" />
	<input type="text" name="name" />
<button>put修改</button>
</form>
<form action="restful/dododo" method="post">
	<input type="hidden" name="_method" value="DELETE" />
	<input type="text" name="name" />
<button>delete删除</button>
</form>
</body>
</html>