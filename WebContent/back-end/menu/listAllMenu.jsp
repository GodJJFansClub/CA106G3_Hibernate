<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
<title>List_All_Menu</title>
</head>
<body>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true" />
		<jsp:include page="/back-end/sideBar/dishFoodMana.jsp" flush="true" />
		<div class="page-wrapper">
			<div class="page-breadcrumb">
				<%--=================================工作區================================================--%>
 	<div class="alert alert-secondary text-center" role="alert" ><font style="font-weight:bold;font-size:26px;">所有套餐</font></div>
	<hr class="border:0;height: 1px;background-image: linear-gradient(to right, rgba(0,0,0,0), rgba(0,0,0,0.75), rgba(0,0,0,0));"/>
				
				<%--Error Message--%>
				<c:if test="${not empty errorMsgs} }">
					<font style="color: red; font-size: 30px;">Error</font>
					<ul>
						<c:forEach var="errMsgs" items="${errorMsgs}">
							<li style="color: red;">${errMsgs}</li>
						</c:forEach>
					</ul>
				</c:if>

				<div class="col">
					<div class="card">

						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>

										<th scope="col">套餐編號</th>
										<th scope="col">套餐照片</th>
										<th scope="col">套餐名稱</th>
										<th scope="col">套餐介紹</th>
										<th scope="col">套餐價錢</th>
										
										<th scope="col">編輯套餐</th>
										


									</tr>
								</thead>

								<%@ include file="page1.file"%>
								<c:forEach var="menuVO" items="${list}" begin="<%=pageIndex%>"
									end="<%=pageIndex+rowsPerPage-1%>">
									<tr>
										<th scope="row">${menuVO.menu_ID}</th>

										<th scope="row"><c:if test="${not empty menuVO.menu_pic}">
												<img class="menu_pic"
													src="<%=request.getContextPath()%>/menu/menu.do?showMenuPic=showMenuPic&menu_ID=${menuVO.menu_ID}"
													width="300" height="200">
											</c:if> <c:if test="${empty menuVO.menu_pic}">
												<img class="menu_pic"
													src="<%=request.getContextPath()%>/images/noimage.jpg">
											</c:if></th>

										<th scope="row">${menuVO.menu_name}</th>
										<th scope="row" width="30%">${menuVO.menu_resume}</th>
										<th scope="row">${menuVO.menu_price}</th>
										
										<th scope="row">
											<form method="post"
												action="<%=request.getContextPath()%>/menu/menu.do">
												<input type="submit" class="btn btn-secondary" value="編輯">
												<input
													type="hidden" name="menu_ID" value="${menuVO.menu_ID}">
												<input type="hidden" name="action" value="getOneForUpdate">
											</form>
										</th>
										
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
				<%@ include file="page2.file"%>

						
						</div>
					</div>
				</div></body>
</html>