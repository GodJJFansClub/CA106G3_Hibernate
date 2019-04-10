<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.fun.model.*"%>

<%
	FunVO funVO = (FunVO) request.getAttribute("funVO"); //FunServlet.java (Concroller) 存入req的funVO物件 (包括幫忙取出的funVO, 也包括輸入資料錯誤時的funVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>權限資料修改 - update_fun_input.jsp</title>


</head>
<body>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true"/>
		<jsp:include page="/back-end/sideBar/empMana.jsp" flush="true" />
		<div class="page-wrapper">
			<div class="page-breadcrumb">
<%--=================================工作區================================================--%>




	<table id="table-1">
		<tr>
			<td>
				<h3>權限資料修改</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back-end/fun/select_page.jsp">回首頁</a>
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

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/fun/fun.do"
		name="form1">
		<table>
			<tr>
				<td>權限編號:<font color=red><b>*</b></font></td>
				<td><%=funVO.getFun_ID()%></td>
			</tr>
			
			<tr>
				<td>權限名稱:</td>
				<td><input type="TEXT" name="fun_name" size="45"
					value="<%=funVO.getFun_name()%>" /></td>
			</tr>

			


		</table>
		<div id="localImag">
			<img id="preview" width=-1 height=-1 style="diplay: none" />
		</div>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="fun_ID" value="<%=funVO.getFun_ID()%>">
		<input type="submit" value="送出修改">
	</FORM>
<%--=================================工作區================================================--%>			


<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>





			</div>
		</div>
	</div>
</body>
</html>