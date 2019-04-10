<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.auth.model.*"%>

<%
	AuthVO authVO = (AuthVO) request.getAttribute("authVO"); //AuthServlet.java (Concroller) 存入req的authVO物件 (包括幫忙取出的authVO, 也包括輸入資料錯誤時的authVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>權限資料修改</title>


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
						href="<%=request.getContextPath()%>/back-end/auth/select_page.jsp">回首頁</a>
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

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/auth/auth.do"
		name="form1">
		<table>
			<tr>
				<td>員工:<font color=red><b>*</b></font></td>
				<td><%=authVO.getEmp_ID()%></td>
			</tr>
			
			<tr>
				<td>權限名稱:</td>
				<td><input type="TEXT" name="fun_ID" size="45"
					value="<%=authVO.getFun_ID()%>" /></td>
			</tr>

			
		</table>
		
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="emp_ID" value="<%=authVO.getEmp_ID()%>">
		<input type="submit" value="送出修改">
	</FORM>








			</div>
		</div>
	</div>
</body>
</html>