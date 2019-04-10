<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef.model.*"%>
<%@ page import="com.cust.model.*"%>

<%	
	ChefService chefSvc = new ChefService();
	CustService custSvc = new CustService();
	String chef_ID = (String)session.getAttribute("chef_ID");
	CustVO custVO = custSvc.getOneCust(chef_ID);
	ChefVO chefVO = chefSvc.getOneByChefID(chef_ID);
	session.setAttribute("custVO",custVO);
	session.setAttribute("chefVO",chefVO);
%>

<!doctype html>
<html lang="en">
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

<title>主廚個人檔案!</title>

<style type="text/css">
.header {
	background-color: gray;
	height: 150px;
	width: 100%;
}

h1 {
	color: white;
	text-align: center;
	line-height: 130px;
}

div {
	border: 1px solid gray;
}

#chef_name {
	padding: 10px;
}

#chef_resume {
	padding: 10px;
}

.btn {
	margin: 15px 15px 0px 0px;
}
</style>
</head>
<body>
	<div class="container-fliud">
		<div class="header">
			<h1>主廚個人檔案</h1>
		</div>
		<div class="row">
			<div class="col-2">
				<a href="<%=request.getContextPath()%>/menuCart/menu.jsp"
					class="btn btn-secondary btn-lg btn-block" tabindex="-1"
					role="button" aria-disabled="true">訂購套餐</a>
			</div>
			<div class="col-3">
				<div class="card mb-3">
					<c:if test="${not empty custVO.cust_pic}">
						<img
							src="<%=request.getContextPath()%>/cust/cust.do?cust_ID=${custVO.cust_ID}"
							style="display: block; margin: auto; width: 300px;; height: 400px;">
					</c:if>
					<c:if test="${empty custVO.cust_pic}">
						<img src="<%=request.getContextPath()%>/images/noimage.jpg"
							style="display: block; margin: auto; width: 300px;; height: 400px;">
					</c:if>
				</div>
			</div>
			<div class="col-4">
				<div id="chef_name">
					<h3>${custVO.cust_name}</h3>
				</div>
				<div id="chef_resume">${chefVO.chef_resume}</div>
			</div>
			<div class="col-3">
				<form method="post"
					action="<%=request.getContextPath()%>/cust/cust.do">
					<input type="hidden" name="cust_ID" value="${custVO.cust_ID}">
					<input type="hidden" name="action" value="getOne_For_Update">
					<input type="submit" class="btn btn-secondary btn-lg btn-block"
						value="編輯個人資料">
				</form>
				<form method="post"
					action="<%=request.getContextPath()%>/chef/chef.do">
					<input type="hidden" name="chef_ID" value="${chefVO.chef_ID}">
					<input type="hidden" name="action" value="getOneForEditChefResume">
					<input type="submit" class="btn btn-secondary btn-lg btn-block"
						value="編輯主廚簡介">
				</form>
				<form method="post"
					action="<%=request.getContextPath()%>/menuOrder/unCheckMenuOrder.jsp">
					<input type="hidden" name="chef_ID" value="${chefVO.chef_ID}">
					<input type="submit" class="btn btn-secondary btn-lg btn-block"
						value="查看未審核訂單">
				</form>
				<form method="post"
					action="<%=request.getContextPath()%>/menuOrder/unFinishedMenuOrder.jsp">
					<input type="hidden" name="chef_ID" value="${chefVO.chef_ID}">
					<input type="hidden" name="action" value="unFinishedMenuOrder">
					<input type="submit" class="btn btn-secondary btn-lg btn-block"
						value="查看未完成訂單">
				</form>
				<form method="post"
					action="<%=request.getContextPath()%>/chefDish/addChefDish.jsp">
					<input type="hidden" name="chef_ID" value="${chefVO.chef_ID}">
					<input type="hidden" name="action" value="updateChefDish">
					<input type="submit" class="btn btn-secondary btn-lg btn-block"
						value="管理擅長菜色">
				</form>
				<form method="post"
					action="<%=request.getContextPath()%>/chefSch/addChefSch.jsp">
					<input type="hidden" name="chef_ID" value="${chefVO.chef_ID}">
					<input type="hidden" name="action" value="updateChefDish">
					<input type="submit" class="btn btn-secondary btn-lg btn-block"
						value="新增主廚排程">
				</form>
			</div>
		</div>
		<div class="footer"></div>
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