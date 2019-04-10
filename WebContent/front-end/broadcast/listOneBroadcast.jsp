<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.broadcast.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	BroadcastVO broadcastVO = (BroadcastVO) request.getAttribute("broadcastVO"); //SustServlet.java(Concroller), 存入req的broadcastVO物件
%>

<html>
<head>
<title>推播資料 - listOneBroadcast.jsp</title>

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
	<%
		if (broadcastVO != null) {
	%>

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>推播資料 - ListOneBroadcast.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/front-end/broadcast/select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>推播編號</th>
			<th>推播標題</th>
			<th>推播內文</th>
			<th>推播上架日期</th>
			<th>推播下架日期</th>
			<th>推播狀態</th>
			<th>推播類別</th>
			<th>食材供應商</th>
			
		</tr>
		<tr>
			<td><%=broadcastVO.getBroadcast_ID()%></td>
			<td><%=broadcastVO.getBroadcast_con()%></td>
			<td><%=broadcastVO.getBroadcast_start()%></td>
			<td><%=broadcastVO.getBroadcast_status()%></td>
			<td><%=broadcastVO.getCust_ID()%></td>
		

		</tr>
	</table>
	<%
		}
	%>
</body>
</html>