<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dishFood.model.*"%>
<jsp:useBean id="foodSvc" scope="page" class="com.food.model.FoodService" />
<jsp:useBean id="dishSvc" scope="page" class="com.dish.model.DishService" />

<%
  DishFoodVO dishFoodVO = (DishFoodVO) request.getAttribute("dishFoodVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>菜色食材修改 </title>

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
	<tr><td>
		 <h3>食材內容修改</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/dish/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>文章修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<tr>
		<td>文章編號:<font color=red><b>*</b></font></td>
		<td><%=dishFoodVO.getDish_ID()%></td>
</tr>
<br>
<table>
	
	<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/dishFood/dishFood.do">
		<b>選擇菜色:</b> 
			<select size="1" name="food_ID">
				<c:forEach var="dishFoodVO" items="${foodSvc.all}">
					<option value="${dishFoodVO.food_ID}">${foodSvc.getOneFood(dishFoodVO.food_ID).food_name}
				</c:forEach>
			</select>
			
		
		
	<tr>
		<td>食材數量:</td>
		<td><input type="TEXT" name="dish_f_qty" size="45"  value="<%=dishFoodVO.getDish_f_qty()%>" /></td>
	</tr>
	<tr>
		<td>食材單位:</td>
		<td><input type="TEXT" name="dish_f_unit" size="45"	value="<%=dishFoodVO.getDish_f_unit()%>" /></td>
	</tr>
	 
</table>

<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="dish_ID" value="<%=dishFoodVO.getDish_ID()%>">
<input type="submit" value="送出修改"></From>
</body>
</html>