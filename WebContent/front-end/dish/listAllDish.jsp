<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.dish.model.*"%>
<jsp:useBean id="dishSvc" scope="page" class="com.dish.model.DishService"/>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%

    List<DishVO> list = dishSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有菜色資料 - listAllDish.jsp</title>

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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有菜色資料 - listAllDish.jsp</h3>
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
		<th>菜色名稱:</th>
		<th>菜色照片:</th>
		<th>菜色介紹:</th>
		<th>菜色狀態</th>
		<th>菜色價格</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="dishVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr ${(dishVO.dish_ID==param.dish_ID) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
		<tr>
			<td>${dishVO.dish_ID}</td>
			<td>${dishVO.dish_name}</td>
			<td><img src ="<%=request.getContextPath()%>/back-end/dish/dish.do?dish_ID=${dishVO.dish_ID}"  width="300" height="200"></td>
			<td >${dishVO.dish_resume}</td>
			<td>${dishVO.dish_status}</td> 
			<td>${dishVO.dish_price}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dish/dish.do" style="margin-bottom: 0px;">
			     <input type="submit" value="審核">
			     <input type="hidden" name="dish_ID"  value="${dishVO.dish_ID}"> 
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dish/dish.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="dish_ID"  value="${dishVO.dish_ID}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>