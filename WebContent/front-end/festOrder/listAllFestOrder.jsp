<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.festOrder.model.*"%>
<%@ page import="com.cust.model.*"%>
<jsp:useBean id="festOrderSvc" scope="page"
	class="com.festOrder.model.FestOrderService" />

<%
	CustVO custVO = (CustVO) session.getAttribute("custVO");
	festOrderSvc = new FestOrderService();
	List<FestOrderVO> list = festOrderSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<meta http-equiv="X-UA-Compatible" content="ie=edge">

<title>Document</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous"></script>

<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
	crossorigin="anonymous"></script>

<style>
#banner {
	background-image: url("https://unsplash.com/@mantra_media_ltd");
	background-size: cover;
	background-repeat: no-repeat;
	background-position: center;
}

#about .row {
	height: 40px;
}

#about .row {
	/* height: auto; */
	padding-top: 50px;
	padding-bottom: 50px;
}

@media screen and (min-width: 180px) {
	#about .row {
		/* height: 450px; */
		padding-top: 80px;
		padding-bottom: 80px;
	}
}

#service-top, #service-bottom {
	padding-top: 50px;
	padding-bottom: 50px;
}
</style>

</head>



<body>

	<nav class="navbar navbar-expand-sm navbar-dark bg-dark">

		<div class="container">

			<a class="navbar-brand" href="#">Logo</a>

			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navi" aria-controls="navbarSupportedContent"
				aria-expanded="false" aria-label="Toggle navigation">

				<span class="navbar-toggler-icon"></span>

			</button>

			<div class="collapse navbar-collapse" id="navi">

				<ul class="navbar-nav ml-auto">

					<li class="nav-item active"><a class="nav-link" href="#">Home
							<span class="sr-only">(current)</span>
					</a></li>

					<li class="nav-item"><a class="nav-link" href="#">About</span></a></li>

					<li class="nav-item"><a class="nav-link" href="#">Product</span></a>

					</li>

					<li class="nav-item"><a class="nav-link" href="#">Service</span></a>

					</li>

					<li class="nav-item"><a class="nav-link" href="#">Contact</span></a>

					</li>

				</ul>

			</div>

		</div>

	</nav>


	<div id="slider" class="carousel slide d-none d-lg-block carousel-fade"
		data-ride="carousel">

		<ol class="carousel-indicators">

			<li data-target="#slider" data-slide-to="0" class="active"></li>

			<li data-target="#slider" data-slide-to="1"></li>

			<li data-target="#slider" data-slide-to="2"></li>

			<li data-target="#slider" data-slide-to="3"></li>

		</ol>

		<div class="carousel-inner">

			<div class="carousel-item active">

				<img src="https://unsplash.com/@mantra_media_ltd/1600x600?sig=1"
					class="d-block w-100" alt="...">

				<div class="carousel-caption">

					<h4>TITLE</h4>

					<p>Lorem ipsum dolor sit amet.</p>

				</div>

			</div>

			<div class="carousel-item">

				<img src="https://source.unsplash.com/random/1600x600?sig=2"
					class="d-block w-100" alt="...">

			</div>

			<div class="carousel-item">

				<img src="https://source.unsplash.com/random/1600x600?sig=3"
					class="d-block w-100" alt="...">

			</div>

			<div class="carousel-item">

				<img src="https://source.unsplash.com/random/1600x600?sig=4"
					class="d-block w-100" alt="...">

			</div>

		</div>
		<a class="carousel-control-prev" href="#slider" role="button"
			data-slide="prev"> <span class="carousel-control-prev-icon"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>

		</a> <a class="carousel-control-next" href="#slider" role="button"
			data-slide="next"> <span class="carousel-control-next-icon"
			aria-hidden="true"></span> <span class="sr-only">Next</span>

		</a>

	</div>





	<title>List_All_FestOrder.jsp</title>

	<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
	
}

table#table-1 h4 {
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



						<table id="table-1">
							<tr>
								<td>
									<h3>節慶主題料理訂單資料 - listAllFestOrder.jsp</h3>
									<h4>
										<a
											href="<%=request.getContextPath()%>/back-end/festOrder/select_page.jsp">回首頁</a>
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
						<section class="page-content">
							<div class="container pt-3 pb-3">
								<div class="table-responsive-sm table-middle">
									<thead class="thead-dark">
										<table class="table table-hover">
											<thead class="thead-dark">
												<tr>
													<th>訂單編號</th>
													<th>訂單狀態</th>
													<th>價格</th>
													<th>訂單成立日期</th>
													<th>出貨日期</th>
													<th>訂單結束日期</th>
													<!-- 			<th>折扣</th> -->
													<th>會員編號</th>
													
													<th>送出查詢</th>
												</tr>
											</thead>
											<%@ include file="page1.file"%>
											<c:forEach var="festOrderVO" items="${list}"
												begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
												<c:if test="${festOrderVO.cust_ID == custVO.getCust_ID()}">
												<tr>
													<td>${festOrderVO.fest_or_ID}</td>
													<td>${festOrderVO.fest_or_status}</td>
													<td>${festOrderVO.fest_or_price}</td>
													<td>${festOrderVO.fest_or_start}</td>
													<td>${festOrderVO.fest_or_send}</td>
													<td>${festOrderVO.fest_or_end}</td>
													<%-- 				<td>${festOrderVO.fest_or_disc}</td> --%>
													<td>${festOrderVO.cust_ID}</td>
													
													<td>
														<FORM METHOD="post"
															ACTION="<%=request.getContextPath()%>/festOrder/festOrder.do"
															style="margin-bottom: 0px;">
															<button type="submit"
																class="btn btn-secondary text-white">送出查詢</button>
															<!-- 						<input type="submit" value="送出查詢" >  -->
															<input type="hidden" name="fest_or_ID"
																value="${festOrderVO.fest_or_ID}"> <input
																type="hidden" name="action"
																value="listFestOrderDetail_ByFest_or_ID">
														</FORM>
														
													</td>
												</tr>
												</c:if>
											</c:forEach>
										</table>
								</div>
							</div>
						</section>

						<%@ include file="page2.file"%>

						<%
							if (request.getAttribute("listFestOrderDetail_ByFest_or_ID") != null) {
						%>
						<jsp:include
							page="/front-end/festOrder/listFestOrderDetail_ByFest_or_ID.jsp" />
						<%
							}
						%>
					
</section>
<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>