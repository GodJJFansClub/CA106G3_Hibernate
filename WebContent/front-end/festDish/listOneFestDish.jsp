<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.festDish.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	FestDishVO festDishVO = (FestDishVO) request.getAttribute("festDishVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>新增節慶主題料理菜色資料 - listOneFestOrder.jsp</title>

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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>節慶主題料理菜色資料 - ListOneFestDish.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>菜色編號 </th>
		<th>節慶料理編號</th>
	</tr>
	<tr>
		<td><%=festDishVO.getDish_ID()%></td>
		<td><%=festDishVO.getFest_m_ID()%></td>
	</tr>
</table>

</body>
</html>