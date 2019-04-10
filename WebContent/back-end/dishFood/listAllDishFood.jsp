<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.dishFood.model.*"%>
<jsp:useBean id="foodSvc" scope="page" class="com.food.model.FoodService"/>
<jsp:useBean id="dishSvc" scope="page" class="com.dish.model.DishService"/>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    DishFoodService dishFoodSvc = new DishFoodService();
    List<DishFoodVO> list = dishFoodSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
</head>
<body>
<div class="alert alert-secondary text-center" role="alert" ><font style="font-weight:bold;font-size:26px;">菜色食材管理</font></div>
<hr class="border:0;height: 1px;background-image: linear-gradient(to right, rgba(0,0,0,0), rgba(0,0,0,0.75), rgba(0,0,0,0));"/>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
		 
		
	<tr>
		<th>菜色編號:</th>
		<th>食材名稱:</th>
		<th>食材數量:</th>
		<th>數量單位:</th>
		<th>修改</th>
	</tr>
	
	
	<c:forEach var="dishFoodVO" items="${list}">
		
		<tr>
		
			<td>${dishSvc.getOneDish(dishFoodVO.dish_ID).dish_name}</td>
			<td>${foodSvc.getOneFood(dishFoodVO.food_ID).food_name}</td>
			<td>${dishFoodVO.dish_f_qty}</td>
			<td>${foodUnitMap[dishFoodVO.dish_f_unit]}</td>
			
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dishFood/dishFood.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="dish_ID"  value="${dishFoodVO.dish_ID}">
			     <input type="hidden" name="food_ID"  value="${dishFoodVO.food_ID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		
		</tr>
	</c:forEach>
</table>


</body>
</html>