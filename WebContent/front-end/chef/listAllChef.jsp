<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cust.model.*"%>
<%@ page import="com.chef.model.*"%>
<jsp:useBean id="custSvc" class="com.cust.model.CustService"/>
<%	
	ChefService chefSvc = new ChefService();
	List<ChefVO> list = chefSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<html>
<head>
<!-- Font-family -->	
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Noto+Serif+TC">

<title>瀏覽所有主廚</title>
</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<jsp:include page="/froTempl/headerCookGod.jsp" flush="true" />
	 <!-- ##### Contact Area Start #####-->
    <section class="contact-area section-padding-100">
    <%--Error Message--%>
	<c:if test="${not empty errorMsgs} }">
		<font style="color: red; font-size: 30px;">Error</font>
		<ul>
			<c:forEach var="errMsgs" items="${errorMsgs}">
				<li style="color: red;">${errMsgs}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div class="container justify-content-center">
		<div class="row">
			<div class="col-12">
				<%@ include file="page1.file"%>
			</div>
		</div>
	</div>
	<div class="container-fliud justify-content-center">
		<div class="row">
			<div class="col-1"></div>
			<div class="col-10">
				<div class="container justify-content-center">
					<div class="row">
						<c:forEach var="chefVO" items="${list}" begin="<%=pageIndex %>" end="<%=pageIndex+rowsPerPage-1 %>">
							<div class="col-4" >
								<a href="<%=request.getContextPath()%>/front-end/chef/listOneChefProfile.jsp?chef_ID=${chefVO.chef_ID}">
									<div class="card bg-dark text-white" style="margin-bottom:25px;">
										<c:if test="${not empty custSvc.getOneCust(chefVO.chef_ID).cust_pic}">
											<img class="card-img" src="<%=request.getContextPath()%>/cust/cust.do?cust_ID=${chefVO.chef_ID}" style="width:350px;height:350px;">
										</c:if>
										<c:if test="${empty custSvc.getOneCust(chefVO.chef_ID).cust_pic}">
											<img class="card-img"src="<%=request.getContextPath()%>/images/noimage.jpg">
										</c:if>
										<div class="card-img-overlay">
											<font class="card-title" style="font-size:24px;font-weight:bold;font-family:'Noto Serif TC';color:white;">${custSvc.getOneCust(chefVO.chef_ID).cust_name}</font>
										</div>
									</div>
								</a>
							</div>
						</c:forEach>
					</div>
				</div>
				<%@ include file="page2.file"%>
			</div>
			<div class="col-1"></div>
		</div>
	</div>
    </section>
    <!-- ##### Contact Area End #####-->
	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>