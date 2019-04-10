<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.fun.model.*"%>

<%
	FunVO funVO = (FunVO) request.getAttribute("funVO");
	Integer i = (Integer)request.getAttribute("aaa");
%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>權限資料新增</title>





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
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/fun/fun.do"
		name="form1" >

		<table>
		
			<tr>
				<td>功能名稱:</td>
				<td><input type="TEXT" name="fun_name" size="45"
					value="<%=(funVO == null) ? "請輸入功能名稱" : funVO.getFun_name()%>" /></td>
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