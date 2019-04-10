<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.menuOrder.model.*"%>
<html>
<head>
<title>套餐訂單管理系統!</title>
</head>
<body>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true" />
		<jsp:include page="/back-end/sideBar/custMana.jsp" flush="true"/>
		<div class="page-wrapper">
			<div class="page-breadcrumb">
				<%--=================================工作區================================================--%>
				<a class="btn btn-outline-secondary" href='listAllMenuOrder.jsp' role="button" style="width: 100%;">查詢所有訂單</a>
				
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
							<div class="alert alert-success" role="alert">請輸入或選擇一筆資料以查詢</div>
							<form method="post" action="<%=request.getContextPath()%>/menuOrder/menuOrder.do">
								<div class="input-group mb-3">
									<input type="text" class="form-control" name="menu_od_ID"
										placeholder="請輸入訂單號碼">
									<div class="input-group-append">
										<input type="hidden" name="action" value="getOneMenuOrder">
										<input type="submit" class="btn btn-outline-secondary" value="送出">
									</div>
								</div>
							</form>
							<jsp:useBean id="menuOrderSvc" scope="page"
								class="com.menuOrder.model.MenuOrderService" />
							<form method="post" action="<%=request.getContextPath()%>/menuOrder/menuOrder.do">
								<div class="input-group">
									<select class="custom-select" name="menu_od_ID"">
										<option value="請選擇訂單號碼">請選擇訂單號碼
											<c:forEach var="menuOrderVO" items="${menuOrderSvc.all}">
												<option value="${menuOrderVO.menu_od_ID}">${menuOrderVO.menu_od_ID}
											</c:forEach>
									</select>
									<div class="input-group-append">
										<input type="hidden" name="action" value="getOneMenuOrder">
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