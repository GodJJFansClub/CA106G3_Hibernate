<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dishFood.model.*,com.food.model.*"%>


<jsp:useBean id="dishFoodSvc" scope="page" class="com.dishFood.model.DishFoodService" />
<jsp:useBean id="foodSvc" scope="page" class="com.food.model.FoodService" />
<jsp:useBean id="dishSvc" scope="page" class="com.dish.model.DishService" />


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>文章新增 - addForumArt.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>菜色食材新增 </h3>
			</td>
			<td>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back-end/dish/select_page.jsp"><img
						src="images/tomcat.png" width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dishFood/dishFood.do" style="margin-bottom: 0px;">
		
		<c:forEach var="dishFoodVO" items="${dishFoodList}">
		 <table>
		 	<tr>
				<td>菜色</td>
				<td> ${dishSvc.getOneDish(dishFoodVO.dish_ID).dish_name}</td>
			</tr>
			<tr>
				<td>食材</td>
				<td> ${foodSvc.getOneFood(dishFoodVO.food_ID).food_name}</td>
			</tr>
			<tr>	
				
				<td>食材數量:</td>
				<td><input type="TEXT" name="dish_f_qty" size="42"value="${empty dishFoodVO? "MANAGER" : dishFoodVO.dish_f_qty}" /></td>
			</tr>
			<tr>
				<td>食材重量</td>
				<td><input type="TEXT" name="dish_f_unit" size="42"value="${empty dishFoodVO? "MANAGER" : dishFoodVO.dish_f_unit}" /></td>
			</tr>
			</table>
		</c:forEach> 
		
			<input type="hidden" name="dish_ID" value="${dishFoodVO.dish_ID}">
			<input type="hidden" name="food_ID" value="${dishFoodVO.food_ID}">
			<input type="hidden" name="action" value="insert">
			<input type="submit" value="送出新增">
		
		
	</FORM>
	
	

</body>
</html>