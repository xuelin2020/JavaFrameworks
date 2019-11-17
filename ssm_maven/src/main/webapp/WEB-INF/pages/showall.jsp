<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.1.1.js"></script>
</head>
<body>
<a href="${pageContext.request.contextPath }/dept/savepage">新增部门</a>

<table width="500" border="1" cellspaceing="0">
	<thead>
	<tr>
		<th>编号</th>
		<th>名称</th>
		<th>所在</th>
		<th colspan="3">操作</th>
	</tr>
	</thead>
	<tbody>
		<c:forEach items="${all }" var="dept">
			<tr>
				<td>${dept.deptno }</td>
				<td>${dept.dname }</td>
				<td>${dept.loc }</td>
				<td><a href="${pageContext.request.contextPath }/dept/getbyno?deptno=${dept.deptno}">修改</a></td>
				<td><a href="${pageContext.request.contextPath }/dept/getbyno2/${dept.deptno}">修改2</a></td>
				<td><a class="del_a" href="${pageContext.request.contextPath }/dept/delete/${dept.deptno}">删除</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 表单，点击删除时，提交 -->
<form action="" method="post" id="delform">
	<input type="hidden" name="_method" value="delete" />
</form>
</body>
<script type="text/javascript">
//删除链接添加点击事件，设置表单action，表单提交，取消链接的默认事件
$(".del_a").click(function(){
	var rs = window.confirm("确定删除此条记录吗？");
	if(rs){
		$("#delform").attr("action", this.href);
		$("#delform").submit();
		return false;
	}
	
	
});


</script>
</html>