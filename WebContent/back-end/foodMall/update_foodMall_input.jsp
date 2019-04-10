<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.foodMall.model.*" %>
<jsp:useBean id="foodMallVO" scope="request" type="com.foodMall.model.FoodMallVO"/>
<jsp:useBean id="foodSvc" scope="page" class="com.food.model.FoodService" />
<html>
<head>
<title>更改狀態</title>

</head>
<body>
            <%-- 錯誤列表 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
				<form id="upStatusForm" method="post" action="<%=request.getContextPath()%>/foodMall/foodMall.do" name="form1">
				<table>
					<tr>
						<td>標題:</td>
						<td>${foodMallVO.food_m_name}</td>
					</tr>
					<tr>
				<td>商品狀態:</td>
				<td>
					<c:forEach var="mallStatus" items="${mallStatusMap}">
						<input name="food_m_status" type="radio" value="${mallStatus.key}" ${(foodMallVO.food_m_status == mallStatus.key)?'checked':''} >${mallStatus.value}
					</c:forEach>
				</td>
			
			</tr>
			<tr>
				<td>商品價格:</td>
				<td>${foodMallVO.food_m_price}</td>
			</tr>
			<tr>
				<td>單位:</td>
				<td>${foodUnitMap[foodMallVO.food_m_unit]}</td>
			</tr>
			<tr>
				<td>產地:</td>
				<td>${foodMallVO.food_m_place}</td>
			</tr>
			
				
			<tr>
				<td>食材名稱:</td>
				<td>
					<h3>${foodSvc.getOneFood(foodMallVO.food_ID).food_name}</h3>
				</td>
			</tr>
			<tr>
				<td>商品照片:</td>
				<td><img src="<%=request.getContextPath()%>/foodMall/foodMall.do?food_sup_ID=${foodMallVO.food_sup_ID}&food_ID=${foodMallVO.food_ID}" width="150px" height="200px"></td>
			</tr>
			<tr>
				<td>介紹:</td>
				<td>
					${empty foodMallVO.food_m_resume ? "請介紹" : foodMallVO.food_m_resume}
				</td>
			</tr>
		</table>
		<input type="hidden" name="pre_status" value="${foodMallVO.food_m_status}">
		<input type="hidden" name="food_ID" value="${foodMallVO.food_ID}">
		<input type="hidden" name="food_sup_ID" value="${foodMallVO.food_sup_ID}">
		<input type="hidden" name="action" value="backUpStatus">
	</form>
</body>
</html>