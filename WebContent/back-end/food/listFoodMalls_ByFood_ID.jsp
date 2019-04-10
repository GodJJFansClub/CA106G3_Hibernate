<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.foodMall.model.*" %>

<jsp:useBean id="listFoodMalls_ByFood_ID" scope="request" type="java.util.Set<FoodMallVO>"/>
<jsp:useBean id="foodSvc" class="com.food.model.FoodService"/>
<jsp:useBean id="foodSupSvc" class="com.foodSup.model.FoodSupService"/>
<html>
<head>
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
			<th>食材供應商</th>
			<th>食材名稱</th>
			<th>標題</th>
			<th>狀態</th>
			<th>價格</th>
			<th>單位</th>
			<th>產地</th>
			<th>圖片</th>
			<th>介紹</th>
		</tr>

		<c:forEach var="foodMallVO" items="${listFoodMalls_ByFood_ID}">
			<tr 
				${(	foodMallVO.food_sup_ID == param.food_sup_ID
					&& foodMallVO.food_ID == param.food_ID) ? 'bgcolor=#CCCCFF':''}>
				<!--將修改的那一筆加入對比色-->
				<td>${foodSupSvc.getOneFoodSup(foodMallVO.food_sup_ID).food_sup_name}</td>
				<td>${foodSvc.getOneFood(foodMallVO.food_ID).food_name}</td>
				<td>${foodMallVO.food_m_name}</td>
				<td>${foodMallVO.food_m_status}</td>
				<td>${foodMallVO.food_m_price}</td>
				<td>${foodMallVO.food_m_unit}</td>
				<td>${foodMallVO.food_m_place}</td>
				<td>
					<img src="<%=request.getContextPath()%>/foodMall/foodMall.do?food_sup_ID=${foodMallVO.food_sup_ID}&food_ID=${foodMallVO.food_ID}">
				</td>
				<td>
					<%--<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/foodMall/foodMall.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改">
						<input type="hidden" name="food_sup_ID" value="${foodMallVO.food_sup_ID}">
						<input type="hidden" name="food_ID" value="${foodMallVO.food_ID}">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<!--送出本網頁的路徑給Controller-->
						<!-- 目前尚未用到  -->
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM> --%>
				</td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>