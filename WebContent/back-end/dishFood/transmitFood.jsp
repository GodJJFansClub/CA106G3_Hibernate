<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dishFood.model.*,com.food.model.*"%>
<jsp:useBean id="dishFoodSvc" scope="page" class="com.dishFood.model.DishFoodService" />
<jsp:useBean id="foodSvc" scope="page" class="com.food.model.FoodService" />
<jsp:useBean id="dishSvc" scope="page" class="com.dish.model.DishService" />


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
</head>
<body bgcolor='white'>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
		<FORM id="dishFoodForm" METHOD="post" ACTION="<%=request.getContextPath()%>/dishFood/dishFood.do" style="margin-bottom: 0px;">
		<%--<tr>
		<td><h3>菜色名稱: <font color=red>${dishSvc.getOneDish(dishVO.dish_ID).dish_name}</font></h3></td>
		</tr>--%>
				
		<h4> ${dishSvc.getOneDish(dishFoodVO.dish_ID).dish_name}</h4>
		<c:forEach var="dishFoodVO" items="${dishFoodList}">
		 <table>
			<tr>
				<td>食材</td>
				<td> ${foodSvc.getOneFood(dishFoodVO.food_ID).food_name}</td>
			</tr>
			<tr>	
				
				<td>食材數量:</td>
				<td><input type="TEXT" name="dish_f_qty" size="42"value="${empty dishFoodVO? "MANAGER" : dishFoodVO.dish_f_qty}" /></td>
			</tr>
			<tr>
				<td>食材單位</td>
				<td>
				<select   size="1"   name="dish_f_unit">
				<c:forEach var = "foodUnit" items="${foodUnitMap}">
				<option value="${foodUnit.key}">${foodUnit.value}
				</c:forEach>
				</select>	
				</td>
			</tr>
			<tr>
				<td><input type="hidden" name="food_ID" value="${dishFoodVO.food_ID}"></td>
			</tr>
		</table>
			
		</c:forEach> 
		
			<input id="dishIdHidd" type="hidden" name="dish_ID" value="${dishVO.dish_ID}">
			<input id="dishAction" type="hidden" name="action" value="insert">
			
			<input  type="submit" value="新增完成">
		
		
	</FORM>
	
	 <table id="cloneInFD" style="display:none">
			<tr>
				<td>食材</td>
				<td></td>
			</tr>
			<tr>	
				
				<td>食材數量:</td>
				<td><input type="TEXT" name="dish_f_qty" size="42" /></td>
			</tr>
			<tr>
				<td>食材重量</td>
				<td><input type="TEXT" name="dish_f_unit" size="42"/></td>
			</tr>
			<tr>
				<td><input type="hidden" name="food_ID" ></td>
			</tr>
	</table>

</body>
</html>