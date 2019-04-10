<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.festOrder.model.*"%>

<%
	FestOrderVO festOrderVO = (FestOrderVO) request.getAttribute("festOrderVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>查詢一筆節慶主題料理訂單(FestOrder) - listOneFestOrder.jsp</title>

</head>

<body bgcolor='white'>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true"/>
<%--==============<jsp:include page="/back-end/XXXX/sidebar.jsp" flush="true" />=================================--%>
		<div class="page-wrapper">
			<div class="page-breadcrumb">
<%--=================================工作區================================================--%>
<div class="container">
		<div class="row">
			<div class="col-12">
				<!-- Section Heading -->
				<div class="section-heading text-center wow fadeInUp"
					data-wow-delay="100ms">
					<h2>Get In Touch</h2>
					<img src="img/core-img/x.png" alt="">
				</div>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-12 col-lg-8">
				<!-- Contact Form -->
				<div class="contact-form-area text-center">
					<form action="<%=request.getContextPath()%>/festOrderDetail/festOrderDetail.do"
						method="post">
						<c:if test="${not empty errorMsgs}">
							<font style="color: red"></font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>

	<table id="table-1">
		<tr>
			<td>
				<h3>節慶主題料理訂單資料 - ListOneFestOrder.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back-end/festOrder/select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>訂單編號</th>
			<th>訂單狀態</th>
			<th>價格</th>
			<th>訂單成立日期</th>
			<th>出貨日期</th>
			<th>訂單結束日期</th>
<!-- 			<th>折扣</th> -->
			<th>會員編號</th>
		</tr>
		<tr>
			<td><%=festOrderVO.getFest_or_ID()%></td>
			<td><%=festOrderVO.getFest_or_status()%></td>
			<td><%=festOrderVO.getFest_or_price()%></td>
			<td><%=festOrderVO.getFest_or_start()%></td>
			<td><%=festOrderVO.getFest_or_send()%></td>
			<td><%=festOrderVO.getFest_or_end()%></td>
<%-- 			<td><%=festOrderVO.getFest_or_disc()%></td> --%>
			<td><%=festOrderVO.getCust_ID()%></td>
		</tr>
	</table>
</section>

			</div>
		</div>
	</div>
</body>
</html>