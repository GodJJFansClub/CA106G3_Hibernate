<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.auth.model.*"%>

<%
	AuthVO authVO = (AuthVO) request.getAttribute("authVO");
	
%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>權限資料新增</title>



<style>
table {
	width: 450px;
	
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
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true"/>
		<jsp:include page="/back-end/sideBar/empMana.jsp" flush="true" />
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
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/auth/auth.do"
		name="form1" >

		<table>
		
			<tr>
<jsp:useBean id="empSvc" scope="session" class="com.emp.model.EmpService" />
<jsp:useBean id="funSvc" scope="session" class="com.fun.model.FunService" />
				<b>選擇員工編號:</b> <select size="1" name="emp_ID">
					<c:forEach var="empVO" items="${empSvc.all}">
						<option value="${empVO.emp_ID}">${empVO.emp_name}
					</c:forEach>
</select> 
			
			</tr>
			
			<tr>
				<b>選擇權限編號:</b> <select size="1" name="fun_ID">
					<c:forEach var="funVO" items="${funSvc.all}">
						<option value="${funVO.fun_ID}">${funVO.fun_name}
					</c:forEach>
					</select> 
			</tr>
			


		</table>
		
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">

	</FORM>




			</div>
		</div>
	</div>
	
</body>
</html>