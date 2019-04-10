<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true" />
		<aside class="left-sidebar" data-sidebarbg="skin5">
			<%--==============<jsp:include page="/back-end/XXXX/sidebar.jsp" flush="true" />=================================--%>

		</aside>
		<div class="page-wrapper">
			<div class="page-breadcrumb">
				<%--=================================工作區================================================--%>
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
										<form method="post" action="<%=request.getContextPath()%>/menu/menu.do">
											<input type="submit" value="編輯"> 
											<input type="hidden" name="menu_ID" value="${menuVO.menu_ID}"> 
											<input type="hidden" name="action" value="getOneForUpdate">
										</form>
									</td>
									<td>
										<form method="post" action="<%=request.getContextPath()%>/menu/menu.do">
											<input type="submit" value="刪除"> 
											<input type="hidden" name="menu_ID" value="${menuVO.menu_ID}"> 
											<input type="hidden" name="action" value="delete">
										</form>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<% } %>

			
			</div>
		</div>
	</div>
</body>
</html>