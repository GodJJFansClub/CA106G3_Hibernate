<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<html>
<head>
<title>主廚資料</title>
</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<!-- ##### Breadcrumb Area Start ##### -->
	<jsp:include page="/froTempl/headerCust.jsp" flush="true" />
	<!-- ##### Breadcrumb Area End ##### -->


	<!-- ##### Feature Area Start ##### -->
	<section class="pixel-feature-area d-flex flex-wrap">
		<!-- Feature Thumbnail -->
		<div class="feature-thumbnail bg-img jarallax"
			style="background-image: url(<%=request.getContextPath()%>/froTempl/temp/img/bg-img/custPic.jpg);"></div>

		<!-- Feature Content -->
		<div class="feature-content">
			<h1 style="font-weight: bolder;">個人資料</h1>

			<div class="single-testimonial-slide">
				<c:if test="${not empty custVO.cust_pic}">
					<img src="<%=request.getContextPath()%>/cust/cust.do?cust_ID=${custVO.cust_ID}">
				</c:if>
				<c:if test="${empty custVO.cust_pic}">
					<img src="<%=request.getContextPath()%>/images/noimage.jpg">
				</c:if>
				<c:if test="${not empty custVO.cust_niname}">
					<h4 style="font-weight:bold">${custVO.cust_niname}</h4>
				</c:if>
				<c:if test="${empty custVO.cust_niname}">
					<h4 style="font-weight:bold">${custVO.cust_name}</h4>
				</c:if>
			</div>
			
			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>主廚姓名</h6>
					<p>${custVO.cust_name}</p>
				</div>
			</div>


			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>主廚帳號</h6>
					<p>${custVO.cust_acc}</p>
				</div>
			</div>
			
			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>主廚生日</h6>
					<p>${custVO.cust_brd}</p>
				</div>
			</div>
			
			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>身份證字號</h6>
					<p>${custVO.cust_pid}</p>
				</div>
			</div>

			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>性別</h6>
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
					<h6>電話</h6>
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
					<h6>E-Mail</h6>
					<p>${custVO.cust_mail}</p>
				</div>
			</div>
			
			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>主廚服務地區</h6>
					<c:if test="${chefVO.chef_area=='0'}"><p>北部</p></c:if>
					<c:if test="${chefVO.chef_area=='1'}"><p>中部</p></c:if>
					<c:if test="${chefVO.chef_area=='2'}"><p>南部</p></c:if>
					<c:if test="${chefVO.chef_area=='3'}"><p>東部</p></c:if>
				</div>
			</div>
			
			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>主廚註冊日期</h6>
					<p>${custVO.cust_reg}</p>
				</div>
			</div>

			<div class="single-contact-content d-flex">
				<div class="icon">
					<i class="fa fa-star" aria-hidden="true"></i>
				</div>
				<div class="text">
					<h6>主廚狀態</h6>
					<c:if test="${chefVO.chef_status=='b0'}"><p>未審核</p></c:if>
					<c:if test="${chefVO.chef_status=='b1'}"><p>正常</p></c:if>
					<c:if test="${chefVO.chef_status=='b2'}"><p>審核未通過</p></c:if>
					<c:if test="${chefVO.chef_status=='b3'}"><p>停權</p></c:if>
				</div>
			</div>
			
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/cust/cust.do"
				style="margin-bottom: 0px;">
				<input type="hidden" name="cust_ID" value="${custVO.cust_ID}">
				<input type="hidden" name="action" value="getOne_For_Update">
				<button type="submit" class="btn pixel-btn btn-3 m-2" value="修改">修改資料</button>
			</FORM>

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
					<h6>個人簡介</h6>
					<p>${chefVO.chef_resume}</p>
				</div>
			</div>
			<!-- Feature Thumbnail -->
			</div>
			<div class="feature-thumbnail bg-img jarallax"
				style="background-image: url(<%=request.getContextPath()%>/froTempl/temp/img/bg-img/chefPic.jpg);"></div>
		
	<!-- ##### Contact Area End #####-->
	</section>
	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>