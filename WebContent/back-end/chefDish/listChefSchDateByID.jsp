<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chefSch.model.*"%>

<html>
<head>
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
	<%--Error Message--%>
	<c:if test="${not empty errorMsgs} }">
		<font style="color: red; font-size: 30px;">Error</font>
		<ul>
			<c:forEach var="errMsgs" items="${errorMsgs}">
				<li style="color: red;">${errMsgs}</li>
			</c:forEach>
		</ul>
	</c:if>
	<c:if test="${listChefSchByChefID!=null}">
		<div class=chef_sch_date_title>
			<p>請選擇日期</p>
		</div>
		<div class="container justify-content-center">
			<div class="row">
				<c:forEach var="chefSchVO" items="${listChefSchByChefID}">
					<c:if test="${chefSchVO.chef_sch_status=='c0'}">
						<div class="col-2">
							<form method="post"
								action="<%=request.getContextPath()%>/chefSch/chefSch.do">
								<input type="hidden" name="action" value="setOrderDate">
								<input type="hidden" name="order_chef_sch_date" value="${chefSchVO.chef_sch_date}"> 
								<input type="submit" class="chef_sch_date btn btn-outline-secondary btn-block" value="${chefSchVO.chef_sch_date}">
							</form>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</c:if>
</body>
</html>