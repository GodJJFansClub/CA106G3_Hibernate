<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>

<%
	MenuService menuSvc = new MenuService();
	List<MenuVO> list = menuSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>List_All_Chef_Dish.jsp</title>
<style type="text/css">
table {
	border: 2px solid gray;
	margin: 15px;
}

th, td {
	border: 1px solid;
	width: 100px;
	height: 50px;
	text-align: center;
	padding: 3px;
}

.menu_pic {
	width: 200px;
	height: 150px;
}
</style>
</head>
<body>

	<div class="card text-center" style="background-color: #D4E6F1">
		<div class="card-body">
			<h5 class="card-title">所有套餐</h5>
			<p class="card-text">listAllMenu.jsp</p>
			<a href="index.jsp" class="btn btn-primary">回首頁</a>
		</div>
	</div>

	<%--Error Message--%>
	<c:if test="${not empty errorMsgs} }">
		<font style="color: red; font-size: 30px;">Error</font>
		<ul>
			<c:forEach var="errMsgs" items="${errorMsgs}">
				<li style="color: red;">${errMsgs}</li>
			</c:forEach>
		</ul>
	</c:if>
	<table>
		<tr>
			<th>套餐編號</th>
			<th>套餐照片</th>
			<th>套餐名稱</th>
			<th>套餐介紹</th>
			<th>套餐價錢</th>
			<th>套餐狀態</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="menuVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td>${menuVO.menu_ID}</td>

				<td><c:if test="${not empty menuVO.menu_pic}">
						<img class="menu_pic"
							src="<%=request.getContextPath()%>/menu/menu.do?showMenuPic=showMenuPic&menu_ID=${menuVO.menu_ID}">
					</c:if> <c:if test="${empty menuVO.menu_pic}">
						<img class="menu_pic"
							src="<%=request.getContextPath()%>/images/noimage.jpg">
					</c:if>
				</td>
				<td>${menuVO.menu_name}</td>
				<td>${menuVO.menu_resume}</td>
				<td>${menuVO.menu_price}</td>
				<td>${menuVO.menu_status}</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous">
	</script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous">
	</script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
		integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
		crossorigin="anonymous">
	</script>

</body>
</html>