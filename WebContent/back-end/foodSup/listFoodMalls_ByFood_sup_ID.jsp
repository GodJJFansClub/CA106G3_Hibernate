<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.foodMall.model.*" %>

<jsp:useBean id="listFoodMalls_ByFood_sup_ID" scope="request" type="java.util.Set<FoodMallVO>"/>
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

	<table class="table">
		<tr class="table-active">
			<th scope="col">食材供應商</th>
			<th scope="col">食材名稱</th>
			<th scope="col">標題</th>
			<th scope="col">狀態</th>
			<th scope="col">價格</th>
			<th scope="col">單位</th>
			<th scope="col">產地</th>
			<th scope="col">圖片</th>
			<th scope="col">更改狀態</th>
		</tr>

		<c:forEach var="foodMallVO" items="${listFoodMalls_ByFood_sup_ID}" varStatus="tabIn">
			<tr <c:if test="${tabIn.index%2 == 0}">class="table-success"</c:if>
				<c:if test="${tabIn.index%2 == 1}">class="table-info"</c:if>>
				<!--將修改的那一筆加入對比色-->
				<td class="row">${foodSupSvc.getOneFoodSup(foodMallVO.food_sup_ID).food_sup_name}</td>
				<td>${foodSvc.getOneFood(foodMallVO.food_ID).food_name}</td>
				<td>${foodMallVO.food_m_name}</td>
				<td>${mallStatusMap[foodMallVO.food_m_status]}</td>
				<td>${foodMallVO.food_m_price}</td>
				<td>${foodUnitMap[foodMallVO.food_m_unit]}</td>
				<td>
					<div class="p-2">
                        <img src="<%=request.getContextPath()%>/foodMall/foodMall.do?food_sup_ID=${foodMallVO.food_sup_ID}&food_ID=${foodMallVO.food_ID}" alt="user" width="100" class="rounded-circle">
                    </div>
                </td>
				<td>${foodMallVO.food_m_place}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/foodMall/foodMall.do"
						style="margin-bottom: 0px;">
						<input type="submit" class="btn btn-secondary" value="更改狀態">
						<input type="hidden" name="food_sup_ID" value="${foodMallVO.food_sup_ID}">
						<input type="hidden" name="food_ID" value="${foodMallVO.food_ID}">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<!--送出本網頁的路徑給Controller-->
						<!-- 目前尚未用到  -->
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>