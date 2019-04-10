<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.broadcast.model.*"%>

<%
	BroadcastVO broadcastVO = (BroadcastVO) request.getAttribute("broadcastVO"); //BroadcastServlet.java (Concroller) 存入req的broadcastVO物件 (包括幫忙取出的broadcastVO, 也包括輸入資料錯誤時的broadcastVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>推播資料修改 - update_broadcast_input.jsp</title>
<link href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" rel="stylesheet">
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
		<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true"/>
		<aside class="left-sidebar" data-sidebarbg="skin5">
<%--==============<jsp:include page="/back-end/XXXX/sidebar.jsp" flush="true" />=================================--%>
		
		</aside>
		<div class="page-wrapper">
			<div class="page-breadcrumb">
<%--=================================工作區================================================--%>

	<table id="table-1">
		<tr>
			<td>
				<h3>推播資料修改 - update_broadcast_input.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/front-end/broadcast/select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/broadcast/broadcast.do"
		name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>推播編號:<font color=red><b>*</b></font></td>
				<td><%=broadcastVO.getBroadcast_ID()%></td>
			</tr>
			
			<tr>
				<td>推播內文:</td>
				<td><input type="TEXT" name="broadcast_con" size="45"
					value="<%=broadcastVO.getBroadcast_con()%>" /></td>
			</tr>
			
			

			<tr>
				<td>推播地址:</td>
				<td><input type="TEXT" name="cust_ID" size="45"
					value="<%=broadcastVO.getCust_ID()%>" /></td>
			</tr>

			


		</table>
		<div id="localImag">
			<img id="preview" width=-1 height=-1 style="diplay: none" />
		</div>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="broadcast_ID" value="<%=broadcastVO.getBroadcast_ID()%>">
		<input type="submit" value="送出修改">
	</FORM>

			</div>
		</div>
	</div>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
</body>



</html>