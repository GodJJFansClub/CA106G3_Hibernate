<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.favFdSup.model.*"%>

<%	
	FavFdSupService favFdSupSvc = new FavFdSupService();
	String chef_ID = request.getParameter("chef_ID");
	List<FavFdSupVO> listAll = favFdSupSvc.getAllByChefID(chef_ID);
	pageContext.setAttribute("listAll", listAll);
%>

<html>
<head>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

<title>List_All_By_Chef_ID.jsp</title>
<style type="text/css">
	table{
		border: 2px solid gray;
		margin: 15px;
	}
	th,td{
		border:1px solid;
		width: 100px;
		height: 50px;
		text-align: center;
		padding:3px;
	}
</style>
</head>
<body>
	<div class="card text-center" style="background-color: #D4E6F1">	  
	  <div class="card-body" >
	    <h5 class="card-title">主廚喜愛食材供應商</h5>
	    <p class="card-text">listAllByChefID.jsp</p>
	    <a href="index.jsp" class="btn btn-primary">回首頁</a>
	  </div>
	</div>

<%--Error Message--%>
<c:if test = "${not empty errorMsgs} }">
	<font style="color:red;font-size:30px;">Error</font>
	<ul>
		<c:forEach var="errMsgs" items="${errorMsgs}">
			<li style="color:red;">${errMsgs}</li>
		</c:forEach>
	</ul>
</c:if>
	<div class="container justify-content-center">
  		<div class="row">
  			<div class="col-12">  			
				<table>
					<tr>
						<th>主廚編號</th>
						<th>食材供應商編號</th>
						<th>食材供應商備註</th>
						<th>編輯喜愛食材供應商</th>
						<th>刪除喜愛食材供應商</th>
					</tr>
					<%@ include file="page1.file" %>
					<c:forEach var="favFdSupVO" items="${listAll}" begin="<%=pageIndex %>" end="<%=pageIndex+rowsPerPage-1 %>">
						<tr>
							<td>${favFdSupVO.chef_ID}</td>
							<td>${favFdSupVO.food_sup_ID}</td>
							<td>${favFdSupVO.fav_fd_sup_note}</td>
							<td>
								<form method="post" action="<%=request.getContextPath()%>/favFdSup/favFdSup.do">
									<input type="submit" value="編輯">
									<input type="hidden" name="chef_ID"  value="${favFdSupVO.chef_ID}">
									<input type="hidden" name="food_sup_ID"  value="${favFdSupVO.food_sup_ID}">
									<input type="hidden" name="action"	value="getOneForUpdate">
								</form>
							</td>
							<td>
								<form method="post" action="<%=request.getContextPath()%>/favFdSup/favFdSup.do">
									<input type="submit" value="刪除">
									<input type="hidden" name="chef_ID"  value="${favFdSupVO.chef_ID}">
									<input type="hidden" name="food_sup_ID"  value="${favFdSupVO.food_sup_ID}">
									<input type="hidden" name="action"	value="delete">
								</form>
							</td>
						</tr>	
					</c:forEach>
				</table>
				<%@ include file="page2.file" %>
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