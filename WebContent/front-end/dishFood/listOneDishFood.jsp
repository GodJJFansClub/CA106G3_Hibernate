<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dishFood.model.*"%>
<jsp:useBean id="foodSvc" scope="page" class="com.food.model.FoodService"/>
<jsp:useBean id="dishSvc" scope="page" class="com.dish.model.DishService"/>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  DishFoodVO dishFoodVO = (DishFoodVO) request.getAttribute("dishFoodVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>菜色食材資料 </title>

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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>菜色食材資料 </h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/dish/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>菜色編號</th>
		<th>食材編號</th>
		<th>食材數量</th>
		<th>食材單位</th>
		
	</tr>
	<tr>
		<td>${dishSvc.getOneDish(dishFoodVO.dish_ID).dish_name}</td>
		<td>${foodSvc.getOneFood(dishFoodVO.food_ID).food_name}</td>
		<td><%=dishFoodVO.getDish_f_qty()%></td>
		<td><%=dishFoodVO.getDish_f_unit()%></td>
		
		
	</tr>
</table>

</body>
</html>