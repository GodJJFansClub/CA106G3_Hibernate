<%@page import="com.foodOrDetail.model.FoodOrDetailVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="foodOrderSvc" class="com.foodOrder.model.FoodOrderService"/>
<jsp:useBean id="foodSupSvc" class="com.foodSup.model.FoodSupService"/>
<jsp:useBean id="foodSvc" class="com.food.model.FoodService"/>
<jsp:useBean id="foodMallSvc" class="com.foodMall.model.FoodMallService"/>
<jsp:useBean id="foodODVOset" scope="request" type="java.util.Set<com.foodOrDetail.model.FoodOrDetailVO>"/>
<html>
<head>
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/publibrary/jQueryRate/jquery.rateyo.min.css">
</head>
<body>
	<script src="<%=request.getContextPath()%>/publibrary/jQueryRate/jquery.rateyo.min.js"></script>
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
			<th></th>
		</tr>
		
		<c:forEach var="foodOrDetailVO" items="${foodODVOset}" varStatus="x">
			
				<tr>
					<td>${foodMallSvc.getOneFoodMall(foodOrDetailVO.food_sup_ID, foodOrDetailVO.food_ID).food_m_name}</td>
					<td>${foodSupSvc.getOneFoodSup(foodOrDetailVO.food_sup_ID).food_sup_name}</td>
					<td>${foodSvc.getOneFood(foodOrDetailVO.food_ID).food_name}</td>
					<td>${foodOrDetailVO.food_od_qty}</td>
					<td>${foodOrDetailVO.food_od_stotal}</td>
					<td>
						<div class="container">
							<div class="row">
							<c:choose>
								<c:when test="${foodOrderVO.food_or_status == 'o4'}">
								<form action="<%=request.getContextPath()%>/foodOrDetail/foodOrDetail.do" method="POST">
									<div class="col-sm" id="odRate${x.index}"></div>
									<button class="col-sm btnUpStaClass" id="btnUpSta${x.index}" type="button">送出評價</button>
									<input type="hidden" name="food_od_rate" value="${foodOrDetailVO.food_od_status}">
									<input type="hidden" name="food_or_ID" value="${foodOrDetailVO.food_or_ID}">
									<input type="hidden" name="food_ID" value="${foodOrDetailVO.food_ID}">
									<input type="hidden" name="food_sup_ID" value="${foodOrDetailVO.food_sup_ID}">
									<input type="hidden" name="action" value="custUpODRateS">	
								</form>
								</c:when>
								<c:otherwise>
									訂單尚未完成無法評價
								</c:otherwise>
							</c:choose>
							</div>
						</div>
					</td>
				</tr>
			
		</c:forEach>
		
	</table>
	<script >
		$(document).ready(function(){
			<%	int count = 0;
				for(FoodOrDetailVO foodODVO:foodODVOset){%>
				$("#odRate<%=count%>").rateYo({
					
					rating:<%=foodODVO.getFood_od_rate()%>,
					fullStar: true,
					
				});
				<%count++;%>
			<% } %>
			
			$(".btnUpStaClass").click(function(event){
				$(this).next().val($(this).prev().rateYo("rating"));
				console.log($(this).next().val());
				$(this).parent("form").submit();
			});
		});
	</script>
</body>
</html>