<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
</head>
<body>
<div>
	请输入问题关键字：<input type="text" id="kw" />
	<button id="findBtn">查找</button>
</div>
<div>
	<table width="1000" border="1" cellspace="0">
		<thead>
		<th>编号</th>
		<th width="50%">问题</th>
		<th width="10%">选项A</th>
		<th  width="10%">选项B</th>
		<th  width="10%">选项C</th>
		<th  width="10%">选项D</th>
		<th>答案</th>
		<th>难度级别</th>
		<th>作者</th>
		</thead>
		<tbody id="que"></tbody>
	</table>
</div>
</body>
<script type="text/javascript">
$("#findBtn").click(function(){
	var kw = $("#kw").val();
	$.get("/ssm_maven/question/find",{"keyword":kw},fullTbody,"json");
	
});

function fullTbody(data){
	//清空
	$("#que").empty();
	//循环data，添加tr到tbody
	for(var i=0; i< data.length; i++){
		var q = data[i];
		var otr = $("<tr>");
		otr.append("<td>"+q.id+"</td>");
		otr.append("<td>"+q.question+"</td>");
		otr.append("<td>"+q.ans1+"</td>");
		otr.append("<td>"+q.ans2+"</td>");
		otr.append("<td>"+q.ans3+"</td>");
		otr.append("<td>"+q.ans4+"</td>");
		otr.append("<td>"+q.answer+"</td>");
		otr.append("<td>"+q.level+"</td>");
		otr.append("<td>"+q.author+"</td>");
		$("#que").append(otr);
	}
}


</script>
</html>