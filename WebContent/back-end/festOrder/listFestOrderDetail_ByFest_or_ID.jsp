<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.festOrderDetail.model.*"%>
<jsp:useBean id="festOrderSvc" scope="page" class="com.festOrder.model.FestOrderService" />
<jsp:useBean id="festMenuSvc" class="com.festMenu.model.FestMenuService" />


<html>

<head>
<title>節慶主題料理訂單明細</title>


</head>

<body bgcolor='white'>
		<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true"/>
		<jsp:include page="/back-end/sideBar/custMana.jsp" flush="true"/>
		<div class="page-wrapper">
			<div class="page-breadcrumb">
<%--=================================工作區================================================--%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>

						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
<section class="page-content">
   <div class="container pt-3 pb-3">
   <div class="table-responsive-sm table-middle">

						<table class="table table-hover">
	   <thead class="thead-dark">
							<tr>
								<th>節慶主題料理訂單編號</th>
								<th>節慶料理編號</th>
								<th>訂單評價</th>
								<th>訂單評價留言</th>
								<th>訂單數量</th>
								<th>訂單小計</th>

							</tr>
</thead>
							<c:forEach var="festOrderDetailVO"
								items="${listFestOrderDetail_ByFest_or_ID_Back}">
								<tr>
									<td>${festOrderDetailVO.fest_or_ID}</td>
									<td>${festMenuSvc.getOneFestMenu(festOrderDetailVO.fest_m_ID).fest_m_name}</td>
									<td>${festOrderDetailVO.fest_or_rate}</td>
									<td>${festOrderDetailVO.fest_or_msg}</td>
									<td>${festOrderDetailVO.fest_or_qty}</td>
									<td>${festOrderDetailVO.fest_or_stotal}</td>
									
								</tr>
							</c:forEach>
						</table>

			</div>
			</div>
			</section>

	</div>
	</div>
	</div>
</body>
</html>