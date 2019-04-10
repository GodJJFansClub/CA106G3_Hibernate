<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.dish.model.*"%>
<%@ page import="com.menu.model.*"%>
<%@ page import="com.menuDish.model.*"%>
<jsp:useBean id="dishSvc" class="com.dish.model.DishService"/>
<jsp:useBean id="menuDishSvc" class="com.menuDish.model.MenuDishService"/>

<html>
<head>
<title>套餐詳情</title>
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
</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<jsp:include page="/froTempl/headerCookGod.jsp" flush="true" />

	 <!-- ##### Contact Area Start #####-->
    <section class="contact-area section-padding-100">
    	<div class="container-fliud" style="margin-top:50px;">
		<div class="row">
			<div class="col-2"></div>
			<div class="col-8">
				<div class="container-fliud" >
					<div class="header"><h2 class="card-title" style="font-family:Microsoft JhengHei;font-weight:bold;font-size:36px;text-align:center">${menuVO.menu_name}</h2></div>
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
								<form method="post" action="<%=request.getContextPath()%>/menu/menu.do">
									<p class="card-text" style="height: 240px;" style="font-family:Microsoft JhengHei;">${menuVO.menu_resume}</p>
									<p class="card-text text-right" style="font-family:Microsoft JhengHei;">$${menuVO.menu_price}</p>
									<input type="hidden" name="menu_ID" value="${menuVO.menu_ID}">
									<input type="hidden" name="action" value="buyThisMenu">
									<input type="submit" class="btn btn-outline-secondary " style="float: right;" value="購買套餐" >
								</form>
							</div>
						</div>
					</div>
					<hr class="border:0;height: 1px;background-image: linear-gradient(to right, rgba(0,0,0,0), rgba(0,0,0,0.75), rgba(0,0,0,0));"/>
					<div class="container-fliud">
						<div class="header" style="margin:20px;"><font style="font-size:21px;font-weight:bolder;font-family: Microsoft JhengHei;">套&nbsp;餐&nbsp;菜&nbsp;色</font></div>
						<div class="row">
							<c:forEach var="menuDishVO" items="${menuDishSvc.getAllByMenuID(menuVO.menu_ID)}">
								<div class="col-2">
									<div class="card-deck">
										<div class="card">
											<c:if test="${not empty dishSvc.getOneDish(menuDishVO.dish_ID).dish_pic}">
												<img class="card-img-top" src="<%=request.getContextPath()%>/dish/dish.do?dish_ID=${dishSvc.getOneDish(menuDishVO.dish_ID).dish_ID}">
											</c:if>
											<c:if test="${empty dishSvc.getOneDish(menuDishVO.dish_ID).dish_pic}">
												<img class="card-img-top" src="<%=request.getContextPath()%>/images/noimage.jpg">
											</c:if>
										</div>
									</div>
								</div>
								<div class="col-4">
									<div class="card-deck">
										<div class="card">
											<div class="card-body">
											    <h5 class="card-title" style="font-family:Microsoft JhengHei;">${dishSvc.getOneDish(menuDishVO.dish_ID).dish_name}</h5>
										    	<p class="card-text" style="font-family:Microsoft JhengHei;">${dishSvc.getOneDish(menuDishVO.dish_ID).dish_resume}</p>
										    </div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-2"></div>
	</div>
    </section>
    <!-- ##### Contact Area End #####-->

	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>