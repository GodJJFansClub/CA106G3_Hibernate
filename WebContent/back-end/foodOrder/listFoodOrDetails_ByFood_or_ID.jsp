<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.foodOrDetail.model.*" %>

<jsp:useBean id="foodOrderSvc" class="com.foodOrder.model.FoodOrderService"/>
<jsp:useBean id="foodSupSvc" class="com.foodSup.model.FoodSupService"/>
<jsp:useBean id="foodSvc" class="com.food.model.FoodService"/>
<html>
<head>
<title>食材商城訂單明細</title>
</head>
<body>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<table>
		<tr>
			<th>食材供應商名稱</th>
			<th>食材名稱</th>
			<th>數量</th>
			<th>小計</th>
			<th>狀態</th>
		</tr>

		<c:forEach var="foodOrDetailVO" items="${foodODVOset}" varStatus="foodODIn">
			<tr>
				<td>${foodSupSvc.getOneFoodSup(foodOrDetailVO.food_sup_ID).food_sup_name}</td>
				<td>${foodSvc.getOneFood(foodOrDetailVO.food_ID).food_name}</td>
				<td>${foodOrDetailVO.food_od_qty}</td>
				<td>${foodOrDetailVO.food_od_stotal}</td>
				<td>
					<form action="<%=request.getContextPath()%>/foodOrDetail/foodOrDetail.do" method="post">
			      		<input type="hidden" name="food_or_ID" value="${foodOrDetailVO.food_or_ID}">
			      		<input type="hidden" name="food_sup_ID" value="${foodOrDetailVO.food_sup_ID}">
			      		<input type="hidden" name="food_ID" value="${foodOrDetailVO.food_ID}">
			      		<input type="hidden" name="food_od_status" value="${(foodOrDetailVO.food_od_status eq 'd1')?'d2':''}">
			      		<input type="hidden" name="action" value="backUpFODStatus">
			      		<button type="button" id="checkSBtn${foodODIn.index}" class="checkToSend"
			      			${(foodOrDetailVO.food_od_status eq 'd1')?'':'disabled'}>
			      				<c:choose>
				      				<c:when test="${foodOrDetailVO.food_od_status eq 'd0'}">
				      					 供應商尚未出貨
				      				</c:when>
				      				<c:when test="${foodOrDetailVO.food_od_status eq 'd1'}">
				      					 供應商已出貨
				      				</c:when>
				      				<c:otherwise>
				      					${foodODStatusMap[foodOrDetailVO.food_od_status]}
				      				</c:otherwise>
				      			</c:choose>
			      		</button>
	      			</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>