<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.menuOrder.model.*"%>
<html>
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

<title>套餐訂單管理系統!</title>
</head>
<body>

	<div class="card text-center" style="background-color: #D4E6F1">
		<div class="card-body">
			<h5 class="card-title">套餐訂單管理</h5>
			<p class="card-text">Index.jsp</p>
		</div>
	</div>

	<a class="btn btn-outline-success" href='addMenuOrder.jsp'
		role="button" style="width: 50%; float: left;">新增一筆訂單</a>
	<a class="btn btn-outline-success" href='listAllMenuOrder.jsp'
		role="button" style="width: 50%;">查詢所有訂單</a>

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
				<form method="post" action="menuOrder.do">
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
				<form method="post" action="menuOrder.do">
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