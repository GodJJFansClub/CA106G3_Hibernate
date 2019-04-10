<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.menuDish.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
   MenuDishVO menuDishVO = (MenuDishVO) request.getAttribute("menuDishVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>留言資料</title>

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
		<tr>
			<td>
				<h3>員工資料</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>套餐編號</th>
			<th>菜色編號</th>

		</tr>
		<tr>
			<td><%=menuDishVO.getMenu_ID()%></td>
			<td><%=menuDishVO.getDish_ID()%></td>


		</tr>
	</table>

</body>
</html>