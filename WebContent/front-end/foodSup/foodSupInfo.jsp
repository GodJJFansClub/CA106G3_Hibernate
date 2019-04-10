<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>食材供應商資料</title>
</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<!-- ##### Breadcrumb Area Start ##### -->
	<jsp:include page="/froTempl/headerFoodSup.jsp" flush="true" />
	<!-- ##### Breadcrumb Area End ##### -->


	<!-- ##### Feature Area Start ##### -->
	<section class="pixel-feature-area d-flex flex-wrap">
		<!-- Feature Thumbnail -->
		<div class="feature-thumbnail bg-img jarallax"
			style="background-image: url(<%=request.getContextPath()%>/froTempl/temp/img/bg-img/custPic.jpg);"></div>

		<!-- Feature Content -->
		<div class="feature-content">
			<h1 style="font-weight: bold">食材供應商資料</h1>

			<div class="single-testimonial-slide">
				<c:if test="${not empty custVO.cust_pic}">
					<img
						src="<%=request.getContextPath()%>/cust/cust.do?cust_ID=${custVO.cust_ID}">
				</c:if>
				<c:if test="${empty custVO.cust_pic}">
					<img src="<%=request.getContextPath()%>/images/null2.jpg">
				</c:if>
				
				<h4 style="font-weight:bold">${custVO.cust_name}</h4>
				<span>${custVO.cust_niname}</span>
			</div>


			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>顧客帳號</h6>
					<p>${custVO.cust_acc}</p>
				</div>
			</div>

			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>顧客密碼</h6>
					<p>${custVO.cust_pwd}</p>
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
					<h6>狀態</h6>
					<p>${foodSupStatusMap[foodSupVO.food_sup_status]}</p>
				</div>
			</div>

			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>聯絡電話</h6>
					<p>${custVO.cust_tel}</p>
				</div>
			</div>

			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>地址</h6>
					<p>${custVO.cust_addr}</p>
				</div>
			</div>

			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>顧客身份證字號</h6>
					<p>${custVO.cust_pid}</p>
				</div>
			</div>

			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>E-Mail</h6>
					<p>${custVO.cust_mail}</p>
				</div>
			</div>

			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>聯絡人生日</h6>
					<p>${custVO.cust_brd}</p>
				</div>
			</div>

			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>聯絡人註冊日期</h6>
					<p>${custVO.cust_reg}</p>
				</div>
			</div>

		</div>
	</section>
	<!-- ##### Feature Area End ##### -->


	<section class="pixel-feature-area d-flex flex-wrap">
		<!-- Feature Content -->
		<div class="feature-content">

			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>食材供應商聯絡電話</h6>
					<p>${foodSupVO.food_sup_tel}</p>
				</div>
			</div>


			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>介紹</h6>
					<p>${foodSupVO.food_sup_resume}</p>
				</div>
			</div>
			<!-- Feature Thumbnail -->
			</div>
			<div class="feature-thumbnail bg-img jarallax"
				style="background-image: url(<%=request.getContextPath()%>/froTempl/temp/img/bg-img/foodPic.jpg);"></div>
		
	</section>

	<!-- ##### Contact Area End #####-->

	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>