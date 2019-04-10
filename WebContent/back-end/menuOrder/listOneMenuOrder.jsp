<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menuOrder.model.*"%>

<html>
<head>
<title>List_One_Menu_Order</title>
<style type="text/css">
th,td{
	width:100px;
	border:1px solid gray;
	text-align:center;
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
				<c:if test="${not empty errorMsgs} }">
					<font style="color: red; font-size: 30px;">Error</font>
					<ul>
						<c:forEach var="errMsgs" items="${errorMsgs}">
							<li style="color: red;">${errMsgs}</li>
						</c:forEach>
					</ul>
				</c:if>
				<div class="container-filed justify-content-center">
					<div class="row">
						<div class="col-12">
							<table>
								<tr>
									<th>訂單編號</th>
									<th>訂單狀態</th>
									<th>下訂日期</th>
									<th>預約日期</th>
									<th>完成日期</th>
									<th>訂單評價</th>
									<th style="width:400px;">訂單留言</th>
									<th>顧客編號</th>
									<th>主廚編號</th>
									<th>套餐編號</th>
									<th>修改訂單</th>
									<th>刪除訂單</th>
								</tr>
								<tr>
									<td>${menuOrderVO.menu_od_ID}</td>
									<td><c:if test="${menuOrderVO.menu_od_status=='g0'}">未審核</c:if>
										<c:if test="${menuOrderVO.menu_od_status=='g1'}">審核通過</c:if>
										<c:if test="${menuOrderVO.menu_od_status=='g2'}">審核不通過</c:if>
										<c:if test="${menuOrderVO.menu_od_status=='g3'}">主廚到府</c:if>
										<c:if test="${menuOrderVO.menu_od_status=='g4'}">訂單完成</c:if></td>
									<td>${menuOrderVO.menu_od_start}</td>
									<td>${menuOrderVO.menu_od_book}</td>
									<td>${menuOrderVO.menu_od_end}</td>
									<td>${menuOrderVO.menu_od_rate}</td>
									<td>${menuOrderVO.menu_od_msg}</td>
									<td>${menuOrderVO.cust_ID}</td>
									<td>${menuOrderVO.chef_ID}</td>
									<td>${menuOrderVO.menu_ID}</td>
									<td>
										<form method="post" action="<%=request.getContextPath()%>/menuOrder/menuOrder.do">
											<input type="submit" value="編輯"> <input type="hidden" name="menu_od_ID" value="${menuOrderVO.menu_od_ID}">
											<input type="hidden" name="action" value="getOneForDispaly">
										</form>
									</td>
									<td>
										<form method="post" action="<%=request.getContextPath()%>/menuOrder/menuOrder.do">
											<input type="submit" value="刪除"> <input type="hidden" name="menu_od_ID" value="${menuOrderVO.menu_od_ID}">
											<input type="hidden" name="action" value="delete">
										</form>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>