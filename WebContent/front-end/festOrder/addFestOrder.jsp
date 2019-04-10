<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.festOrder.model.*"%>
<%@ page import="com.festOrderDetail.model.*"%>

<%
	FestOrderVO festOrderVO = (FestOrderVO) request.getAttribute("festOrderVO");
%>

<%
	FestOrderDetailVO festOrderDetailVO = (FestOrderDetailVO) request.getAttribute("festOrderDetailVO");
%>
<%
	
	   java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String formatDate = df.format(new java.util.Date());
	   out.println(formatDate); 
%>

<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<!-- Title -->
<title>節慶主題料理訂單新增 - addFestOrder.jsp</title>

<!-- Favicon -->
<link rel="icon"
	href="<%=request.getContextPath()%>/froTempl/temp/img/core-img/favicon.ico">

<!-- Stylesheet -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/froTempl/temp/style.css">
	
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

<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />


	<!-- ##### Hero Area Start ##### -->
	<table id="table-1">
		<tr>
			<td>
				<h3>節慶主題料理訂單新增 - addFestOrder.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="<%=request.getContextPath()%>/front-end/festOrder/select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/festOrder/festOrder.do" name="form1">
		<table>
			<tr>
				<td>訂單狀態:</td>
				<td><input type="TEXT" name="fest_or_status" size="45" value="<%=(festOrderVO == null) ? "3" : festOrderVO.getFest_or_status()%>" /></td>
			</tr>

			<tr>
				<td>價格:</td>
				<td><input type="TEXT" name="fest_or_price" size="45" value="<%=(festOrderVO == null) ? "2000" : festOrderVO.getFest_or_price()%>" /></td>
			</tr>

			<tr>
				<td>訂單成立日期:</td>
				<td><input type="date" name="fest_or_start" value="<%=(festOrderVO == null) ? "2000" : festOrderVO.getFest_or_start()%>" /></td>
			</tr>

			<tr>
				<td>出貨日期:</td>
				<td><input type="date" name="fest_or_send" value="<%=(festOrderVO == null) ? "2000" : festOrderVO.getFest_or_send()%>" /></td>
			</tr>

			<tr>
				<td>訂單結束日期:</td>
				<td><input type="date" name="fest_or_end" value="<%=(festOrderVO == null) ? "2000" : festOrderVO.getFest_or_end()%>" /></td>
			</tr>

			<tr>
				<td>折扣:</td>
				<td><input type="TEXT" name="fest_or_disc" size="45" value="<%=(festOrderVO == null) ? "0.8" : festOrderVO.getFest_or_disc()%>" /></td>
			</tr>

			<tr>
				<td>會員編號:</td>
				<td><input type="TEXT" name="cust_ID" size="45" value="<%=(festOrderVO == null) ? "C00001" : festOrderVO.getCust_ID()%>" /></td>
			</tr>
		</table>

		<table>

			<tr>
				<td>節慶料理編號:</td>
				<td><input type="TEXT" name="fest_m_ID" size="45" value="<%=(festOrderDetailVO == null) ? "FM0002" : festOrderDetailVO.getFest_m_ID()%>" /></td>
			</tr>

			<tr>
				<td>訂單數量:</td>
				<td><input type="TEXT" name="fest_or_qty" value="<%=(festOrderDetailVO == null) ? "45" : festOrderDetailVO.getFest_or_qty()%>" /></td>
			</tr>


		</table> <br> <br> 
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出新增">
	</FORM>

	<!-- ##### Hero Area End ##### -->
	<jsp:include page="/froTempl/footer.jsp" flush="true" />
	<script>
	    var MyPoint = "/WebSocketForServlet";
		var host = window.location.host;
		var webCtx = '<%=request.getContextPath()%>';
		var endPointURL = "ws://" + host + webCtx + "/" + MyPoint;

		var adWebSocket;
		$(document).ready(function() {
			adWSconnect();
		});
		// 觸發connect()時註冊方法, 並建立WebSocket物件

		function adWSconnect() {
			//	建立 websocket 物件
			adWebSocket = new WebSocket(endPointURL);

			adWebSocket.onopen = function(event) {
			
			};

			//	隨然我是在連線建立好時傳送訊息(ServerWebSocket), 依舊會觸發這個onmessage

			adWebSocket.onmessage = function(event) {
				console.log(event);
				let adWallVar = $("#adWallhidden").clone();
				let adWallmsgs = JSON.parse(event.data);
				console.log(adWallmsgs);
				let urlStr = "url(<%=request.getContextPath()%>/ad/ad.do?ad_ID=" +adWallmsgs[0].ad_ID+ ")";
				console.log(urlStr);
				
				adWallVar.find("#imgPos").css('background-image',urlStr);
				adWallVar.find("h2").text(adWallmsgs[0].ad_ID);
				adWallVar.find("#imgPos").removeAttr("id");
				adWallVar.removeAttr("style");
				adWallVar.removeAttr("id");
				
				$("#adWall").append(adWallVar);
			};

		}
		function adWSsendMessage() {

		}
	</script>
	
</body>

</html>