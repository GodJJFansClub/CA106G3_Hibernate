<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>

<html>
<style type="text/css">
.col-4 {
	margin-top: 30px;
}

#spCart {
	cursor: pointer;
}

.card-img {
	width: 480px;
	height: 360px;
}
</style>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>The God of Cooking is Coming!</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">God of Cooking</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarTogglerDemo02"
			aria-controls="navbarTogglerDemo02" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarTogglerDemo02">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item active"><a class="nav-link" href="#">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
				<li class="nav-item"><a class="nav-link disabled" href="#"
					tabindex="-1" aria-disabled="true">Disabled</a></li>
			</ul>
			<form class="form-inline my-2 my-lg-0">
				<img src="<%=request.getContextPath()%>/images/SpCart.png"
					style="width: 40px; height: 40px; margin: 10px;" id="spCart">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit"
					style="margin: 10px;">Search</button>
			</form>
		</div>
	</nav>

	<div class="container-fliud">
		<div class="row">
			<div class="col-2"></div>
			<div class="col-8">
				<div class="card text-center" style="background-color: #D4E6F1">
					<div class="card-body">
						<h5 class="card-title">套餐詳情</h5>
						<a href="<%=request.getContextPath()%>/menuCart/menu.jsp"
							class="btn btn-primary">回上一頁</a>
					</div>
				</div>

				<div class="container-fliud">
					<div class="row">
						<div class="col-5">
							<c:if test="${not empty menuVO.menu_pic}">
								<img class="card-img"
									src="<%=request.getContextPath()%>/menu/menu.do?showMenuPic=showMenuPic&menu_ID=${menuVO.menu_ID}">
							</c:if>
							<c:if test="${empty menuVO.menu_pic}">
								<img class="card-img"
									src="<%=request.getContextPath()%>/images/noimage.jpg">
							</c:if>
						</div>
						<div class="col-7">
							<div class="card-body">
								<form method="post"
									action="<%=request.getContextPath()%>/menu/menu.do">
									<h5 class="card-title">${menuVO.menu_name}</h5>
									<p class="card-text" style="height: 200px;">${menuVO.menu_resume}</p>
									<p class="card-text text-right">$${menuVO.menu_price}</p>
									<input type="hidden" name="menu_ID" value="${menuVO.menu_ID}">
									<input type="hidden" name="action" value="buyThisMenu">
									<input type="submit" class="btn btn-outline-secondary "
										style="float: right;" value="購買套餐">
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-2"></div>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
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
</body>
</html>