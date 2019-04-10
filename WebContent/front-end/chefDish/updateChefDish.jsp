<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chefDish.model.*"%>

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

<title>updateChefDish.jsp</title>
</head>
<body>
	<div class="card text-center" style="background-color: #D4E6F1">
		<div class="card-body">
			<h5 class="card-title">修改擅長菜色</h5>
			<p class="card-text">updateChefDish.jsp</p>
			<a href="index.jsp" class="btn btn-primary">回首頁</a>
		</div>
	</div>

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
				<form method="post" action="chefDish.do">
					<jsp:useBean id="chefDish" scope="page"
						class="com.chefDish.model.ChefDishService" />

					<div class="form-group">
						<label>主廚編號</label> <input type="text" readonly
							class="form-control" name="chef_ID" value="${chefDishVO.chef_ID}" />
					</div>

					<div class="form-group">
						<label>菜色編號</label> <input type="text" readonly
							class="form-control" name="dish_ID" value="${chefDishVO.dish_ID}" />
					</div>

					<%	
							String status[] = new String [3];
							status[0]="0";
							status[1]="1";
							status[2]="2";
							request.setAttribute("mystatus", status);
						%>

					<div class="form-group">
						<label>審核狀態</label> <select size="1" name="chef_dish_status"
							class="form-control">
							<c:forEach var="mydata" items="${mystatus}">
								<option value="${mydata}"
									${(chefDishVO.chef_dish_status==mydata)? 'selected':'' }>${mydata}
							</c:forEach>
						</select>
					</div>
					<input type="hidden" name="action" value="update"> <input
						type="submit" class="btn btn-success btn-lg btn-block" value="修改">
				</form>
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