<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chefDish.model.*"%>

<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

<title>新增主廚喜愛食材供應商</title>
</head>
<body>
	<div class="card text-center" style="background-color: #D4E6F1">	  
	  <div class="card-body" >
	    <h5 class="card-title">新增主廚喜愛食材供應商</h5>
	    <p class="card-text">addFavFdSup.jsp</p>
	    <a href="index.jsp" class="btn btn-primary">回首頁</a>
	  </div>
	</div>
	
	<%--Error Message --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red;font-size:20px;">Error:</font>
		<ul>
		    <c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div class="container justify-content-center">
  		<div class="row">
  			<div class="col-12">
				<form method = "post" action = "<%=request.getContextPath()%>/favFdSup/favFdSup.do">	
				  	<div class="form-group">
				 		<jsp:useBean id="chefSvc" scope="page" class="com.chef.model.ChefService" />
				    	<label>主廚編號</label>
				    	<select size="1" name="chef_ID" class="form-control">
				     		<c:forEach var="chefVO" items="${chefSvc.all}">
								<option value="${chefVO.chef_ID}" >${chefVO.chef_ID}
							</c:forEach>
				    	</select>
				  	</div>
				  	<div class="form-group">
				  		<jsp:useBean id="foodSupSvc" scope="page" class="com.foodSup.model.FoodSupService" />		
					   	<label>食材供應商編號</label>
					   	<select size="1" name="food_sup_ID" class="form-control">
					   		<c:forEach var="foodSupVO" items="${foodSupSvc.all}">
								<option value="${foodSupVO.food_sup_ID}" >${foodSupVO.food_sup_ID}
							</c:forEach>
					   	</select>
					</div>
					<input type="hidden" name="action" value="insert">
					<input type="submit" class="btn btn-success btn-lg btn-block" value="新增主廚喜愛食材供應商">		
				</form>
			</div>
  		</div>
  	</div>
	
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous">
	</script>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"	crossorigin="anonymous">
	</script>
	<script	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous">
	</script>
</body>
</html>