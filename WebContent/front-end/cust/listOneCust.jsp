<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cust.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	CustVO custVO = (CustVO) session.getAttribute("custVO"); //SustServlet.java(Concroller), 存入req的custVO物件
%>

<html>
<head>
<title>顧客資料</title>


</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<!-- ##### Breadcrumb Area Start ##### -->
	<jsp:include page="/froTempl/headerCust.jsp" flush="true" />
	<!-- ##### Breadcrumb Area End ##### -->

	<!-- ##### Contact Area Start #####-->


	<!-- ##### Feature Area Start ##### -->
	<section class="pixel-feature-area d-flex flex-wrap">
		<!-- Feature Thumbnail -->
		<div class="feature-thumbnail bg-img jarallax"
			style="background-image: url(<%=request.getContextPath()%>/froTempl/temp/img/bg-img/custPic.jpg);"></div>

		<!-- Feature Content -->
		<div class="feature-content">
			<h1 style="font-weight: bold">顧客資料</h1>
			<%
				if (custVO != null) {
			%>

			<div class="single-testimonial-slide">
				<c:if test="${not empty custVO.cust_pic}">
					<img
						src="<%=request.getContextPath()%>/cust/cust.do?cust_ID=${custVO.cust_ID}">
				</c:if>
				<c:if test="${empty custVO.cust_pic}">
					<img src="<%=request.getContextPath()%>/images/null2.jpg">
				</c:if>
				</td>

				<h4 style="font-weight:bold"><%=custVO.getCust_name()%></h4>
				<span><%=custVO.getCust_niname()%></span>
			</div>


			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>顧客帳號</h6>
					<p><%=custVO.getCust_acc()%>
					</p>
				</div>
			</div>


			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>顧客密碼</h6>
					<p><%=custVO.getCust_pwd()%></p>
				</div>
			</div>

			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>顧客性別</h6>
					<c:if test="${custVO.cust_sex.equals('M')}" var="true">
						<p>男生</p>
					</c:if>
					<c:if test="${custVO.cust_sex.equals('F')}" var="true">
						<p>女生</p>
					</c:if>
				</div>
			</div>

			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>顧客電話</h6>
					<p><%=custVO.getCust_tel()%></p>
				</div>
			</div>

			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>顧客地址</h6>
					<p><%=custVO.getCust_addr()%></p>
				</div>
			</div>

			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>顧客身份證字號</h6>
					<p><%=custVO.getCust_pid()%></p>
				</div>
			</div>

			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>E-Mail</h6>
					<p><%=custVO.getCust_mail()%></p>
				</div>
			</div>

			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>顧客生日</h6>
					<p><%=custVO.getCust_brd()%></p>
				</div>
			</div>

			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>顧客註冊日</h6>
					<p><%=custVO.getCust_reg()%></p>
				</div>
			</div>

			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>顧客狀態</h6>
					<c:if test="${custVO.cust_status.equals('a0')}" var="true">
						<p>已啟用</p>
					</c:if>
					<c:if test="${custVO.cust_status.equals('a1')}" var="true">
						<p>停權</p>
					</c:if>
				</div>
			</div>


			<!-- ##### Contact Area End #####-->

			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/cust/cust.do"
				style="margin-bottom: 0px;">
				<input type="hidden" name="cust_ID" value="${custVO.cust_ID}">
				<input type="hidden" name="action" value="getOne_For_Update">
				<button type="submit" class="btn pixel-btn btn-3 m-2" value="修改">修改資料</button>
			</FORM>

			<%
				}
			%>

		</div>
	</section>
	<!-- ##### Feature Area End ##### -->



	<!-- ##### Contact Area End #####-->

	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>