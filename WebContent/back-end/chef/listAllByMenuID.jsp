<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef.model.*"%>
<jsp:useBean id="custSvc" scope="page" class="com.cust.model.CustService"/>
<%	
	ChefService chefSvc = new ChefService();
	String menu_ID = request.getParameter("menu_ID");
	List<ChefVO> list = chefSvc.getAllByMenuID(menu_ID);
	pageContext.setAttribute("list",list);
%>

<html>
<head>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
</head>
<body>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true"/>
		<jsp:include page="/back-end/sideBar/foodSupMana.jsp" flush="true"/>
		<div class="page-wrapper">
			<div class="page-breadcrumb">
<%--=================================工作區================================================--%>
	<%--Error Message--%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red; font-size: 30px;">Error</font>
		<ul>
			<c:forEach var="errMsgs" items="${errorMsgs}">
				<li style="color: red;">${errMsgs}</li>
			</c:forEach>
		</ul>
	</c:if>
				<div class="col">
					<div class="card">

						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th scope="col">主廚名稱</th>
										<th scope="col">主廚服務地區</th>
										<th scope="col">主廚簡介</th>
										<th scope="col">審核主廚</th>
									</tr>
								</thead>
								<%@ include file="page1.file"%>
								<c:forEach var="chefVO" items="${list}" begin="<%=pageIndex %>"
									end="<%=pageIndex+rowsPerPage-1 %>">
									<tr>
										<th scope="row">${custSvc.getOneCust(chefVO.chef_ID).cust_name}</th>
										<th scope="row">${chefLocal[chefVO.chef_area]}</th>
										<th scope="row" width="50%">${chefVO.chef_resume}</th>
										<th scope="row">
											<form method="post"
												action="<%=request.getContextPath()%>/chef/chef.do">
												<input type="submit" class="btn btn-secondary" value="審核"> <input
													type="hidden" name="chef_ID" value="${chefVO.chef_ID}">
												<input type="hidden" name="action" value="getOneForDisplay">
											</form>
										</th>
									</tr>
								</c:forEach>
							</table>
							<%@ include file="page2.file"%>
						</div>
					</div>
				</div>

	
			</div>
		</div>
	</div>
</body>
</html>