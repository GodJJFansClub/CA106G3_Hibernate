<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.menuDish.model.*"%>
<jsp:useBean id="menuSvc" scope="page"
	class="com.menu.model.MenuService" />
<jsp:useBean id="dishSvc" scope="page"
	class="com.dish.model.DishService" />

<%
  MenuDishVO menuDishVO = (MenuDishVO) request.getAttribute("menuDishVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>套餐菜色修改</title>

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
				<h3>套餐菜色修改 - update_menuDish_input.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back-end/dish/select_page.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>菜色修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="menuDish.do" name="form1">
		<table>

			<tr>
				<td>套餐編號:<font color=red><b>*</b></font></td>
				<td><%=menuDishVO.getMenu_ID()%>${menuSvc.getOneMenu(menuDishVO.menu_ID).menu_name}</td>
			</tr>
			<tr>
				<td>菜色編號:</td>
				<td><input type="TEXT" name="dish_ID" size="45"
					value="<%= menuDishVO.getDish_ID()%>" /></td>
		</table>

		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="menu_ID" value="<%=menuDishVO.getMenu_ID()%>">
		<input type="submit" value="送出修改">
	</FORM>
</body>
</html>