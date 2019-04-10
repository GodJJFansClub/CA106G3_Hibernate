<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>List_All_By_Chef_ID.jsp</title>
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
	<jsp:include page="/froTempl/header.jsp" flush="true" />

	 <!-- ##### Contact Area Start #####-->
    <section class="contact-area section-padding-100">
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
    </section>
    <!-- ##### Contact Area End #####-->

	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>