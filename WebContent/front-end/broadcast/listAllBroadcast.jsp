<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.broadcast.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	BroadcastService broadcastSvc = new BroadcastService();
	List<BroadcastVO> list = broadcastSvc.getAll();
	;
	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
<head>
<title>所有推播資料 - listAllBroadcast.jsp</title>

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
	width: 500px;
	background-color: white;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 10px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有推播資料 - listAllBroadcast.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/front-end/broadcast/select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

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
		<%@ include file="page1.file"%>
		<c:forEach var="broadcastVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${broadcastVO.broadcast_ID}</td>
				
				<td>${broadcastVO.broadcast_con}</td>
				<td>${broadcastVO.broadcast_start}</td>
				
				<td>${broadcastVO.broadcast_status}</td>
				<td>${broadcastVO.cust_ID}</td>
				
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/broadcast/broadcast.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="broadcast_ID" value="${broadcastVO.broadcast_ID}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/broadcast/broadcast.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="broadcast_ID" value="${broadcastVO.broadcast_ID}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>