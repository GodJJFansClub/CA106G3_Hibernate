<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.broadcast.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="custVO" class="com.cust.model.CustVO"/>
<% custVO.setCust_ID("C00001"); %>

<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Custom CSS -->
    <link href="<%=request.getContextPath()%>/back-endTemplate/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="<%=request.getContextPath()%>/back-endTemplate/dist/css/style.min.css" rel="stylesheet">
</head>
<body >
		<jsp:useBean id="broadcastSvc" scope="page"
			class="com.broadcast.model.BroadcastService" />

	
	
	<div class="toast" role="alert" aria-live="assertive" aria-atomic="true">
  <div class="toast-header">
    <img src="..." class="rounded mr-2" alt="...">
    <strong class="mr-auto" id="broadcastRcver">Bootstrap</strong>
    <small>11 mins ago</small>
    <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
      <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="toast-body">
    Hello, world! This is a toast message.
  </div>
</div>
	
	<div id="statusOutput">
		
	</div>
	<ul>
		
		

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/broadcast/broadcast.do">
				<b>選擇推播編號:</b> <select size="1" name="broadcast_ID">
					<c:forEach var="broadcastVO" items="${broadcastSvc.all}">
						<option value="${broadcastVO.broadcast_ID}">${broadcastVO.broadcast_con}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
			
			</FORM>
		</li>

	</ul>
	
</body>
	<script>
		var MyPoint = "/notifications";
		var host = window.location.host;
		var webCtx = '<%=request.getContextPath()%>';
		var userID = '<%=custVO.getCust_ID()%>';
		var endPointURL = "ws://" + host + webCtx + "/" + MyPoint + "/" + userID; 
		
		var statusOutput = document.getElementById("statusOutput");
		var webSocket;
		
		// 觸發connect()時註冊方法, 並建立WebSocket物件
		function connect() {
			//	建立 websocket 物件
			webSocket = new WebSocket(endPointURL);
			
			webSocket.onopen = function(event) {
				updateStatus("WebSocket 成功連線");
			};
			
			//	隨然我是在連線建立好時傳送訊息(ServerWebSocket), 依舊會觸發這個onmessage
			
			webSocket.onmessage = function(event) {
				var broadcastRcver = document.getElementById("broadcastRcver");
				broadcastRcver.innerText = broadcastRcver.innerText + event.data;
				$('#element').toast('show');
			};

			webSocket.onclose = function(event) {
				
			};
		}
		
		
		
		function sendMessage() {
		    
		}

		
		function disconnect () {
			webSocket.close();
		}

		
		function updateStatus(newStatus) {
			statusOutput.innerHTML = newStatus;
		}
		
	</script>
</html>