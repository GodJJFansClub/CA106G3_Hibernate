<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.menu.model.*"%>
<html>
<head>
<title>套餐管理</title>
</head>
<body>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true" />
		<jsp:include page="/back-end/sideBar/dishFoodMana.jsp" flush="true"/>
		<div class="page-wrapper">
			<div class="page-breadcrumb">
				<%--=================================工作區================================================--%>
				<a class="btn btn-outline-success" href="<%=request.getContextPath()%>/back-end/menu/addMenu.jsp" role="button" style="width: 50%; float: left;">新增套餐</a>
				<a class="btn btn-outline-success" href="<%=request.getContextPath()%>/back-end/menu/listAllMenu.jsp" role="button" style="width: 50%;">查詢所有套餐</a>
				
					<%--Error Message --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red; font-size: 20px;">Error:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					<div class="container justify-content-center">
						<div class="row">
							<div class="col-12">
				
								<div class="alert alert-success" role="alert">請輸入或選擇套餐編號以查詢</div>
								<jsp:useBean id="menuSvc" scope="page"
									class="com.menu.model.MenuService" />
				
								<form method="post"
									action="<%=request.getContextPath()%>/back-end/menu/listOneByMenuID.jsp">
									<div class="input-group mb-3">
										<input type="text" class="form-control" name="menu_ID"
											placeholder="請輸入套餐編號">
										<div class="input-group-append">
											<input type="submit" class="btn btn-outline-secondary" value="送出">
										</div>
									</div>
								</form>
				
								<form method="post"
									action="<%=request.getContextPath()%>/back-end/menu/listOneByMenuID.jsp">
									<div class="input-group">
										<select size="1" name="menu_ID" class="form-control">
											<option value="請選擇套餐編號">請選擇套餐編號
												<c:forEach var="menuVO" items="${menuSvc.all}">
													<option value="${menuVO.menu_ID}">${menuVO.menu_ID}
												</c:forEach>
										</select>
										<div class="input-group-append">
											<input type="submit" class="btn btn-outline-secondary" value="送出">
										</div>
									</div>
								</form>
				
								<form method="post"
									action="<%=request.getContextPath()%>/back-end/menu/listOneByMenuID.jsp">
									<div class="input-group">
										<select size="1" name="menu_ID" class="form-control">
											<option value="請選擇套餐名稱">請選擇套餐名稱
												<c:forEach var="menuVO" items="${menuSvc.all}">
													<option value="${menuVO.menu_ID}">${menuVO.menu_name}
												</c:forEach>
										</select>
										<div class="input-group-append">
											<input type="submit" class="btn btn-outline-secondary" value="送出">
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>

			
			</div>
		</div>
	</div>
</body>
</html>