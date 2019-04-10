<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.festOrderDetail.model.*"%>



<jsp:useBean id="festOrderSvc" scope="page"
	class="com.festOrder.model.FestOrderService" />
<jsp:useBean id="festMenuSvc" class="com.festMenu.model.FestMenuService" />


<html>
<!--  <head><title>部門員工 - listEmps_ByDeptno.jsp</title>  -->
<head>
<title>節慶主題料理訂單明細 - listFestOrderDetail_ByFest_or_ID.jsp</title>

<style>
table#table-2 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-2 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>
</head>
<jsp:include page="/froTempl/header.jsp" flush="true" />
<!-- ##### Contact Area Start #####-->
<section class="contact-area section-padding-100">
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
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					<body bgcolor='white'>

						<table id="table-2">
							<tr>
								<td>
									<h3>節慶主題料理訂單明細 - listFestOrderDetail_ByFest_or_ID.jsp</h3>
									<h4>
										<a
											href="<%=request.getContextPath()%>/front-end/festOrder/listAllFestOrder.jsp">回首頁</a>
									</h4>
								</td>
							</tr>
						</table>

						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>

						<table>
							<tr>
								<th>節慶主題料理訂單編號</th>
								<th>節慶料理編號</th>
								<th>訂單評價</th>
								<th>訂單評價留言</th>
								<th>訂單數量</th>
								<th>訂單小計</th>
								<th>修改</th>
								<th>刪除</th>
							</tr>

							<c:forEach var="festOrderDetailVO"
								items="${listFestOrderDetail_ByFest_or_ID}">
								<tr>
									<td>${festOrderDetailVO.fest_or_ID}</td>
									<td>${festMenuSvc.getOneFestMenu(festOrderDetailVO.fest_m_ID).fest_m_name}</td>
									<td>${festOrderDetailVO.fest_or_rate}</td>
									<td>${festOrderDetailVO.fest_or_msg}</td>
									<td>${festOrderDetailVO.fest_or_qty}</td>
									<td>${festOrderDetailVO.fest_or_stotal}</td>
									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/festOrderDetail/festOrderDetail.do"
											style="margin-bottom: 0px;">
											<input type="submit" value="修改"> <input type="hidden"
												name="fest_or_ID" value="${festOrderDetailVO.fest_or_ID}">
											<input type="hidden" name="requestURL"
												value="<%=request.getServletPath()%>">
											<!--送出本網頁的路徑給Controller-->
											<!-- 目前尚未用到  -->
											<input type="hidden" name="action" value="getOne_For_Update">
										</FORM>
									</td>
									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/festOrderDetail/festOrderDetail.do"
											style="margin-bottom: 0px;">
											<input type="submit" value="刪除"> <input type="hidden"
												name="fest_or_ID" value="${festOrderDetailVO.fest_or_ID}">
											<input type="hidden" name="requestURL"
												value="<%=request.getServletPath()%>">
											<!--送出本網頁的路徑給Controller-->
											<input type="hidden" name="action" value="delete">
										</FORM>
									</td>
								</tr>
							</c:forEach>
						</table>

<!-- 						<br>本網頁的路徑: -->
<!-- 						<br> -->
<%-- 						<b> <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br> --%>
<%-- 							<font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> --%>
<!-- 						</b> -->
</section>
<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>