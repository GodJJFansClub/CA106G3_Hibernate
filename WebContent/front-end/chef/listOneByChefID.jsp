<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef.model.*"%>

<%	
	ChefService chefSvc = new ChefService();
	String chef_ID = request.getParameter("chef_ID");
	ChefVO chefVO = chefSvc.getOneByChefID(chef_ID);
	pageContext.setAttribute("chefVO",chefVO);
%>

<html>
<head>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>List_All_By_Chef_ID.jsp</title>
<style type="text/css">
table {
	border: 2px solid gray;
	margin: 15px;
}

th, td {
	border: 1px solid;
	width: 100px;
	height: 50px;
	text-align: center;
	padding: 3px;
}
</style>
</head>
<body>
	<div class="card text-center" style="background-color: #D4E6F1">
		<div class="card-body">
			<h5 class="card-title">主廚</h5>
			<p class="card-text">listOneByChefID.jsp</p>
			<a href="index.jsp" class="btn btn-primary">回首頁</a>
		</div>
	</div>

	<%--Error Message--%>
	<% if(chefVO==null){ %>
	<font style="color: red; font-size: 30px;">Error</font>
	<ul>
		<li style="color: red;">查無資料</li>
	</ul>
	<% } %>
	<div class="container justify-content-center">
		<div class="row">
			<div class="col-12">
				<table>
					<tr>
						<th>主廚編號</th>
						<th>主廚服務地區</th>
						<th>主廚頻道</th>
						<th>主廚簡介</th>
						<th>編輯主廚</th>
						<th>刪除主廚</th>
					</tr>
					<tr>
						<td>${chefVO.chef_ID}</td>
						<td>${chefVO.chef_area}</td>
						<td>${chefVO.chef_channel}</td>
						<td>${chefVO.chef_resume}</td>
						<td>
							<form method="post"
								action="<%=request.getContextPath()%>/chef/chef.do">
								<input type="submit" value="編輯"> <input type="hidden"
									name="chef_ID" value="${chefVO.chef_ID}"> <input
									type="hidden" name="action" value="getOneForUpdate">
							</form>
						</td>
						<td>
							<form method="post"
								action="<%=request.getContextPath()%>/chef/chef.do">
								<input type="submit" value="刪除"> <input type="hidden"
									name="chef_ID" value="${chefVO.chef_ID}"> <input
									type="hidden" name="action" value="delete">
							</form>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous">
	</script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous">
	</script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
		integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
		crossorigin="anonymous">
	</script>

</body>
</html>