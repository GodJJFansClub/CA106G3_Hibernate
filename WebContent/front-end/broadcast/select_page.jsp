<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.broadcast.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Broadcast: Home</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td><h3>IBM Broadcast: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page forBroadcast: Home</p>

	<h3>資料查詢:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a
			href='<%=request.getContextPath()%>/front-end/broadcast/listAllBroadcast.jsp'>List</a>
			all Broadcast. <br>
		<br></li>


		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/broadcast/broadcast.do">
				<b>輸入推播編號:</b> <input type="text" name="broadcast_ID"> <input
					type="hidden" name="action" value="getOne_For_Display"> <input
					type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="broadcastSvc" scope="page"
			class="com.broadcast.model.BroadcastService" />

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/broadcast/broadcast.do">
				<b>選擇推播編號:</b> <select size="1" name="broadcast_ID">
					<c:forEach var="broadcastVO" items="${broadcastSvc.all}">
						<option value="${broadcastVO.broadcast_ID}">${broadcastVO.broadcast_ID}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/broadcast/broadcast.do">
				<b>選擇推播姓名:</b> <select size="1" name="broadcast_ID">
					<c:forEach var="broadcastVO" items="${broadcastSvc.all}">
						<option value="${broadcastVO.broadcast_ID}">${broadcastVO.broadcast_ID}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>


	<h3>推播管理</h3>

	<ul>
		<li><a href='addBroadcast.jsp'>Broadcastd</a> a new Broadcast.</li>
	</ul>

</body>
</html>