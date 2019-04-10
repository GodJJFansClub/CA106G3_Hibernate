<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>

<%
	MenuService menuSvc = new MenuService();
	List<MenuVO> list = menuSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<style type="text/css">
.col-2 {
	height: 200%;
	background-color: #909497;
}

.col-4 {
	margin-top: 30px;
}
#spCart{
	cursor: pointer;
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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <a class="navbar-brand" href="#">God of Cooking</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
	    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
	      <li class="nav-item active">
	        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">Link</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
	      </li>
	    </ul>
	    <form class="form-inline my-2 my-lg-0">
	      <img src="<%=request.getContextPath()%>/images/SpCart.png" style="width:40px;height:40px;margin:10px;" id="spCart">
	      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" style="margin:10px;">Search</button>
	    </form>
	  </div>
	</nav>

	<div class="container-fliud">
		<div class="row">
			<div class="col-2"></div>
			<div class="col-8">
				<div class="container-fliud">
					<div class="row">
						<div id="carouselExampleIndicators" class="carousel slide"
							data-ride="carousel">
							<ol class="carousel-indicators">
								<li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
								<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
								<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
							</ol>
							<div class="carousel-inner">
								<div class="carousel-item active">
									<img src="<%=request.getContextPath()%>/images/food1.jpg"
										class="d-block w-100" alt="...">
								</div>
								<div class="carousel-item">
									<img src="<%=request.getContextPath()%>/images/food2.jpg"
										class="d-block w-100" alt="...">
								</div>
								<div class="carousel-item">
									<img src="<%=request.getContextPath()%>/images/food3.jpg"
										class="d-block w-100" alt="...">
								</div>
							</div>
							<a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev"> 
								<span class="carousel-control-prev-icon" aria-hidden="true"></span> 
								<span class="sr-only">Previous</span>
							</a>
							<a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next"> 
								<span class="carousel-control-next-icon" aria-hidden="true"></span> 
								<span class="sr-only">Next</span>
							</a>
						</div>
						<%@ include file="page1.file"%>
						<c:forEach var="menuVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<div class="col-4">
								<form method="post" action="<%=request.getContextPath()%>/menu/menu.do">
									<c:if test="${not empty menuVO.menu_pic}">
										<img class="card-img-top" name = "showMenuPic" src="<%=request.getContextPath()%>/menu/menu.do?showMenuPic=showMenuPic&menu_ID=${menuVO.menu_ID}" style="width: 100%; height: 250px;">
									</c:if>
									<c:if test="${empty menuVO.menu_pic}">
										<img class="card-img-top" name = "showMenuPic" src="<%=request.getContextPath()%>/images/noimage.jpg" style="width: 100%; height: 250px;">
									</c:if>
									<div class="card-body">
										<h5 class="card-title">${menuVO.menu_name}</h5>
										<p class="card-text">${menuVO.menu_resume}</p>
									</div>
									<input type="hidden" name="menu_ID" value="${menuVO.menu_ID}">
									<input type="hidden" name="action" value="getOneForDisplay"> 
									<input type="submit" class="btn btn-success btn-lg btn-block" value="查看更多">
								</form>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<%@ include file="page2.file"%>
			<div class="col-2">
			<jsp:include page="MenuCart.jsp" flush="true" />
			</div>
		</div>
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