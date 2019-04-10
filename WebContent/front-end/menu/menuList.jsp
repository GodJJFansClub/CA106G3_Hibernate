<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>

<%
	MenuService menuSvc = new MenuService();
	List<MenuVO> list = menuSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<title>The God of Cooking is Coming!</title>
<head>
<style type="text/css">
.col-2 {
	height: 200%;
	background-color: #909497;
}

.col-4 {
	margin-top: 30px;
}
</style>
</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />

	 <!-- ##### Contact Area Start #####-->
    <section class="contact-area section-padding-100">
    <div class="container">
		<div class="row">
			<div class="col-12">
				<%@ include file="page1.file"%>
				<div class="container-fliud">
					<div class="row">
						<div id="carouselExampleIndicators" class="carousel slide"
							data-ride="carousel">
							<ol class="carousel-indicators">
								<li data-target="#carouselExampleIndicators" data-slide-to="0"
									class="active"></li>
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
							<a class="carousel-control-prev"
								href="#carouselExampleIndicators" role="button"
								data-slide="prev"> <span class="carousel-control-prev-icon"
								aria-hidden="true"></span> <span class="sr-only">Previous</span>
							</a> <a class="carousel-control-next"
								href="#carouselExampleIndicators" role="button"
								data-slide="next"> <span class="carousel-control-next-icon"
								aria-hidden="true"></span> <span class="sr-only">Next</span>
							</a>
						</div>
						
						<c:forEach var="menuVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<div class="col-4">
								<form method="post"
									action="<%=request.getContextPath()%>/menu/menu.do">
									<c:if test="${not empty menuVO.menu_pic}">
										<img class="card-img-top" name="showMenuPic"
											src="<%=request.getContextPath()%>/menu/menu.do?showMenuPic=showMenuPic&menu_ID=${menuVO.menu_ID}"
											style="width: 100%; height: 250px;">
									</c:if>
									<c:if test="${empty menuVO.menu_pic}">
										<img class="card-img-top" name="showMenuPic"
											src="<%=request.getContextPath()%>/images/noimage.jpg"
											style="width: 100%; height: 250px;">
									</c:if>
									<div class="card-body">
										<h5 class="card-title">${menuVO.menu_name}</h5>
										<p class="card-text text-right">$${menuVO.menu_price}</p>
									</div>
									<input type="hidden" name="menu_ID" value="${menuVO.menu_ID}">
									<input type="hidden" name="action" value="getOneForDisplay">
									<input type="submit" class="btn btn-success btn-lg btn-block"
										value="查看更多">
								</form>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<%@ include file="page2.file"%>
		</div>
	</div>
    </section>
    <!-- ##### Contact Area End #####-->

	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>