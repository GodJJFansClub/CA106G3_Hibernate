<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>

<%	
	MenuService menuSvc = new MenuService();
	String menu_ID = request.getParameter("menu_ID");
	MenuVO menuVO = menuSvc.getOneMenu(menu_ID);
	pageContext.setAttribute("menuVO",menuVO);
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

<title>List_One_By_Menu_ID</title>
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

img {
	width: 320px;
	height: 240px;
}
</style>
</head>
<body>
	<div class="card text-center" style="background-color: #D4E6F1">
		<div class="card-body">
			<h5 class="card-title">套餐</h5>
			<p class="card-text">listOneByChefID.jsp</p>
			<a href="index.jsp" class="btn btn-primary">回首頁</a>
		</div>
	</div>

	<%--Error Message--%>
	<% if(menuVO==null){ %>
	<font style="color: red; font-size: 30px;">Error</font>
	<ul>
		<li style="color: red;">查無資料</li>
	</ul>
	<% }else{ %>
	<div class="container justify-content-center">
		<div class="row">
			<div class="col-12">
				<table>
					<tr>
						<th>套餐編號</th>
						<th>套餐照片</th>
						<th>套餐名稱</th>
						<th>套餐簡介</th>
						<th>套餐價錢</th>
						<th>編輯喜愛食材供應商</th>
						<th>刪除喜愛食材供應商</th>
					</tr>
					<tr>
						<td>${menuVO.menu_ID}</td>
						<td><c:if test="${not empty menuVO.menu_pic}">
								<img class="menu_pic"
									src="<%=request.getContextPath()%>/menu/menu.do?showMenuPic=showMenuPic&menu_ID=${menuVO.menu_ID}">
							</c:if> <c:if test="${empty menuVO.menu_pic}">
								<img class="menu_pic"
									src="<%=request.getContextPath()%>/images/noimage.jpg">
							</c:if></td>
						<td>${menuVO.menu_name}</td>
						<td>${menuVO.menu_resume}</td>
						<td>${menuVO.menu_price}</td>
						<td>
							<form method="post"
								action="<%=request.getContextPath()%>/menu/menu.do">
								<input type="submit" value="編輯"> <input type="hidden"
									name="menu_ID" value="${menuVO.menu_ID}"> <input
									type="hidden" name="action" value="getOneForUpdate">
							</form>
						</td>
						<td>
							<form method="post"
								action="<%=request.getContextPath()%>/menu/menu.do">
								<input type="submit" value="刪除"> <input type="hidden"
									name="menu_ID" value="${menuVO.menu_ID}"> <input
									type="hidden" name="action" value="delete">
							</form>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<% } %>

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