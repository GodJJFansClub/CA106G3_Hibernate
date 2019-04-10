<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.festOrderDetail.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	FestOrderDetailVO festOrderDetailVO = (FestOrderDetailVO) request.getAttribute("festOrderDetailVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>新增節慶主題料理訂單明細 - listOneFestOrderDetail.jsp</title>

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
				<h3>節慶主題料理訂單資料 - ListOneFestOrderDetail.jsp</h3>
				<h4>
					<a href="select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>節慶主題料理訂單編號</th>
			<th>節慶料理編號</th>
			<th>訂單評價</th>
			<th>訂單評價留言</th>
			<th>訂單數量</th>
			<th>訂單小計</th>
		</tr>
		<tr>
			<td><%=festOrderDetailVO.getFest_or_ID()%></td>
			<td><%=festOrderDetailVO.getFest_m_ID()%></td>
			<td><%=festOrderDetailVO.getFest_or_rate()%></td>
			<td><%=festOrderDetailVO.getFest_or_msg()%></td>
			<td><%=festOrderDetailVO.getFest_or_qty()%></td>
			<td><%=festOrderDetailVO.getFest_or_stotal()%></td>

		</tr>
	</table>

</body>
</html>