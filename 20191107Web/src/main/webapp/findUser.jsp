<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查找</title>
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
</head>
<body>
请输入用户名<input type="text" id="uname"/>
<button id="finduser">精确查找</button>
<div id="findUser">
</div>
<button class="all">get All</button>
<script type="text/javascript">
  $(".all").click(function(){
	  $.get("json/getall",function(data){
		  console.log(data)
	  },"json");
  });


	$("#finduser").click(function(){
		var uname = $("#uname").val();
		$.ajax({
			"type":"GET",
			"data":{"name":uname},
			"dataType":"json",
			"url":"/20191107Web/json/findUser",
			"success":function(data){
				console.log(data);
				$("#findUser").empty();
				if(data.uid!=0){
					$("#findUser").append("<p>用户id:"+data.uid+"</p>");
					$("#findUser").append("<p>用户名:"+data.username+"</p>");
					$("#findUser").append("<p>密码:"+data.password+"</p>");
					$("#findUser").append("<p>昵称:"+data.nick+"</p>");
				}else{
					$("#findUser").html("用户真的不存在");
				}
			},
			"error":function(){
				$("#findUser").empty();
				$("#findUser").html("用户不存在");
			}
			
		});
	});
</script>
</body>
</html>