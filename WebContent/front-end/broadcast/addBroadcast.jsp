<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.broadcast.model.*"%>

<%
	BroadcastVO broadcastVO = (BroadcastVO) request.getAttribute("broadcastVO");
%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>推播資料新增 - addBroadcast.jsp</title>
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
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/broadcast/broadcast.do"
		name="form1" enctype="multipart/form-data">

		<table>

			<tr>
				<td>推播標題:</td>
				<td><input type="TEXT" name="broadcast_title" size="45"
					value="<%=(broadcastVO == null) ? "teddy" : broadcastVO.getBroadcast_con()%>" /></td>
			</tr>

			<tr>
				<td>推播:</td>
				<td><input type="TEXT" name="broadcast_con" size="45"
					value="<%=(broadcastVO == null) ? "a987654321" : broadcastVO.getCust_ID()%>" /></td>
			</tr>



		</table>
		
		<script src="ckEditor/ckeditor.js"></script>
			<form id="form1" runat="server">
				<div style="margin: 0 auto; width: 700px">
					<textarea id="editor"></textarea>
				</div>
			</form>
			
			<script>
   				 ClassicEditor.create(document.querySelector('#editor')).then(editor => {console.log(editor);})
			</script>
		<div id="localImag">
			<img id="preview" width=200px height=300px style="diplay: on" />
		</div>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">

	</FORM>
	

			</div>
		</div>
	</div>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
</body>








</html>