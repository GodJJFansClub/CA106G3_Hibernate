<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menuOrder.model.*"%>
<%@ page import="com.cust.model.*"%>
<%@ page import="com.chef.model.*"%>

<%
	MenuOrderService menuOrderSvc = new MenuOrderService();
	String chef_ID = session.getAttribute("chef_ID").toString(); 
	List<MenuOrderVO> listAll = menuOrderSvc.getUnCheck(chef_ID);
	pageContext.setAttribute("listAll", listAll);
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

<title>List_All_Menu_Order.jsp</title>
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
</style>
</head>
<body>
		<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true"/>
		<jsp:include page="/back-end/sideBar/custMana.jsp" flush="true" />
		<div class="page-wrapper">
			<div class="page-breadcrumb">
<%--=================================工作區================================================--%>

	<div class="card text-center" style="background-color: #D4E6F1">
		<div class="card-body">
			<h5 class="card-title">未審核訂單</h5>
			<a
				href="<%=request.getContextPath()%>/front-end/chef/chef_profile.jsp"
				class="btn btn-primary">回上一頁</a>
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
	<div class="container justify-content-center">
		<div class="row">
			<div class="col-12">
				<table>
					<tr>
						<th>訂單編號</th>
						<th>訂單狀態</th>
						<th>下訂日期</th>
						<th>預約日期</th>
						<th>顧客編號</th>
						<th>主廚編號</th>
						<th>套餐編號</th>
						<th>審核訂單</th>
					</tr>
					<%@ include file="page1.file"%>
					<c:forEach var="menuOrderVO" items="${listAll}"
						begin="<%=pageIndex %>" end="<%=pageIndex+rowsPerPage-1 %>">
						<tr>
							<td>${menuOrderVO.menu_od_ID}</td>
							<td>${menuOrderVO.menu_od_status}</td>
							<td>${menuOrderVO.menu_od_start}</td>
							<td>${menuOrderVO.menu_od_book}</td>
							<td>${menuOrderVO.cust_ID}</td>
							<td>${menuOrderVO.chef_ID}</td>
							<td>${menuOrderVO.menu_ID}</td>
							<td>
								<form method="post"
									action="<%=request.getContextPath()%>/menuOrder/menuOrder.do">
									<input type="submit" value="通過"> <input type="hidden"
										name="menu_od_ID" value="${menuOrderVO.menu_od_ID}"> <input
										type="hidden" name="menu_od_status" value="g1"> <input
										type="hidden" name="checkMenuOrder" value="pass"> <input
										type="hidden" name="action" value="getOneForUpdate">
								</form>
								<form method="post"
									action="<%=request.getContextPath()%>/menuOrder/menuOrder.do">
									<input type="submit" value="不通過"> <input type="hidden"
										name="menu_od_ID" value="${menuOrderVO.menu_od_ID}"> <input
										type="hidden" name="menu_od_status" value="g2"> <input
										type="hidden" name="checkMenuOrder" value="fail"> <input
										type="hidden" name="action" value="getOneForUpdate">
								</form>
							</td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="page2.file"%>
			</div>
		</div>
	</div>
	</div>
	</div>
	</div>
</body>
</html>