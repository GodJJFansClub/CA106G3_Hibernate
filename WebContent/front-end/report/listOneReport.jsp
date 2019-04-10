<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.report.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
   ReportVO reportVO = (ReportVO) request.getAttribute("reportVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>新增檢舉文章資料 - listOneReport.jsp</title>

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
		 <h3>新增檢舉文章資料 - ListOneReport.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>檢舉編號</th>
		<th>檢舉標題</th>
		<th>檢舉分類</th>
		<th>檢舉時間</th>
		<th>檢舉狀態</th>
		<th>檢舉內容</th>
		<th>會員編號</th>
		<th>文章編號</th>
	</tr>
	<tr>
		<td><%=reportVO.getReport_ID()%></td>
		<td><%=reportVO.getReport_title()%></td>
		<td><%=reportVO.getReport_sort()%></td>
		<td><%=reportVO.getReport_start()%></td>
		<td><%=reportVO.getReport_status()%></td>
		<td><%=reportVO.getReport_con()%></td>
		<td><%=reportVO.getCust_ID()%></td>
		<td><%=reportVO.getForum_art_ID()%></td>
	</tr>
</table>

</body>
</html>