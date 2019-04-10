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
<title>所有食材資料</title>

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
	width: 1500px;
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
		 <h3>所有菜色食材</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/dish/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
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
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="dishFoodVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${dishSvc.getOneDish(dishFoodVO.dish_ID).dish_name}</td>
			<td>${foodSvc.getOneFood(dishFoodVO.food_ID).food_name}</td>
			<td>${dishFoodVO.dish_f_qty}</td>
			<td>${dishFoodVO.dish_f_unit}</td>
			
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dishFood/dishFood.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="dish_ID"  value="${dishFoodVO.dish_ID}">
			     <input type="hidden" name="food_ID"  value="${dishFoodVO.food_ID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dishFood/dishFood.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="dish_ID"  value="${dishFoodVO.dish_ID}">
			     <input type="hidden" name="food_ID"  value="${dishFoodVO.food_ID}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>