<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.report.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Report Home</title>

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
					<form action="<%=request.getContextPath()%>/festOrder/festOrder.do"
						method="post">
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>

						<body bgcolor='white'>



							<p>This is the Home page for Gook God Report: Home</p>

							<h3>資料查詢Search:</h3>

							<h3>檢舉文章管理</h3>

							<ul>
								<li><a
									href='<%=request.getContextPath()%>/front-end/report/addReport.jsp'>Add</a>
									a new Report.</li>
							</ul>
</section>
							<jsp:include page="/froTempl/footer.jsp" flush="true" />
						</body>
</html>