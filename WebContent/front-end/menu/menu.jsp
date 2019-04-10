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
<head>
<!-- Font-family -->	
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Noto+Serif+TC">

<title>The God of Cooking is Coming!</title>
<style type="text/css">
.col-4 {
	margin-top: 30px;
}
</style>
</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<jsp:include page="/froTempl/headerCookGod.jsp" flush="true" />
	 <!-- ##### Contact Area Start #####-->
    <section class="contact-area section-padding-100">
    	<div class="container-fliud">
		<div class="row">
			<div class="col-2"></div>
			<div class="col-8">
				<div class="container-fliud">
					<div class="row">
						<%@ include file="page1.file"%>
						<c:forEach var="menuVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<div class="col-4">
								<form method="post" action="<%=request.getContextPath()%>/menu/menu.do">
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
										<font class="card-title" style="font-size:24px;font-weight:bold;font-family:'Noto Serif TC'">${menuVO.menu_name}</font>
										<p class="card-text text-right">$${menuVO.menu_price}</p>
									</div>
									<input type="hidden" name="menu_ID" value="${menuVO.menu_ID}">
									<input type="hidden" name="action" value="getOneForDisplay">
									<input type="submit" class="btn btn-success btn-lg btn-block" value="套餐詳情">
								</form>
							</div>
						</c:forEach>
					</div>
					<%@ include file="page2.file"%>
				</div>
			</div>
			<div class="col-2"></div>
		</div>
	</div>
    
    </section>
    <!-- ##### Contact Area End #####-->

	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>