<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="foodOrderSvc" class="com.foodOrder.model.FoodOrderService"/>
<jsp:useBean id="foodSupSvc" class="com.foodSup.model.FoodSupService"/>
<jsp:useBean id="foodSvc" class="com.food.model.FoodService"/>
<jsp:useBean id="foodMallSvc" class="com.foodMall.model.FoodMallService"/>
<html>
<head>
<title></title>
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
			<th>標題</th>
			<th>食材供應商名稱</th>
			<th>食材名稱</th>
			<th>數量</th>
			<th>小計</th>
			<th>評價</th>
			<th>留言</th>
			<th>狀態</th>
		</tr>

		<c:forEach var="foodOrDetailVO" items="${foodODVOset}">
			<tr>
				<td>${foodMallSvc.getOneFoodMall(foodOrDetailVO.food_sup_ID, foodOrDetailVO.food_ID).food_m_name}</td>
				<td>${foodSupSvc.getOneFoodSup(foodOrDetailVO.food_sup_ID).food_sup_name}</td>
				<td>${foodSvc.getOneFood(foodOrDetailVO.food_ID).food_name}</td>
				<td>${foodOrDetailVO.food_od_qty}</td>
				<td>${foodOrDetailVO.food_od_stotal}</td>
				<td>${foodOrDetailVO.food_od_rate}</td>
				<td>${foodOrDetailVO.food_od_msg}</td>
				<td>
					<c:choose>
						<c:when test="${(foodOrderVO.food_or_status eq 'g0') or (foodOrderVO.food_or_status eq 'g1')}">
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
					     </c:when>
					     <c:otherwise>
					     	${mallOrStatusMap[foodOrderVO.food_or_status]}
					     </c:otherwise>
				     </c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>